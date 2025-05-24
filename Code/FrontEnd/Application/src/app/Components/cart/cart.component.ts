import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CartService } from '../../Core/services/cart.service';
import { ICartItem } from '../../Core/interface/icart-item';
import { FormsModule } from '@angular/forms';
import { ICart } from '../../Core/interface/icart';
import { IAppUser } from '../../Core/interface/iapp-user';
import { AuthService } from '../../Core/services/auth.service';
import { catchError, finalize, of } from 'rxjs';

@Component({
  selector: 'app-cart',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  //   appUser: IAppUser | null = null;
  cart: ICart | null = null;
  cartTotal: number | undefined = 0;
  isLoading: boolean = true;
  error: string | null = null;
  deletedCartItem: ICartItem | null = null;

  constructor(
    private cartService: CartService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // this.appUser = this.authService.getUserInfo();
    // if (!this.appUser) {
    //   console.warn('No user info found');
    //   this.error = 'Please log in to view your cart';
    //   this.isLoading = false;
    //   return;
    // }
    // console.log('User ID:', this.appUser.id);
    this.loadCart();
  }

  loadCart(): void {
    this.isLoading = true;
    this.error = null;

    this.cartService
      .getCartByAppUserId()
      .pipe(
        catchError((error) => {
          console.error('Error: ', error);
          this.error = 'Failed to load cart. Please try again later.';
          return of(null);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((cart) => {
        this.cart = cart;
        this.calculateTotal();
      });
  }

  calculateTotal(): void {
    this.cartTotal = this.cart?.cartItemDTOList.reduce(
      (total, item) => total + item.productDTO.price * item.quantity,
      0
    );
  }

  updateQuantity(item: ICartItem, event: Event): void {
    console.log('Update Item');

    const input = event.target as HTMLInputElement;
    const newQuantity = parseInt(input.value);
    if (!isNaN(newQuantity)) {
      this.cartService.updateQuantity(item.id, newQuantity);
    }
    this.calculateTotal();
  }

  removeCartItemFromCart(cartItemId: string): void {
    console.log('DELETE Item');
    this.cartService
      .removeCartItemFromCart(cartItemId)
      .pipe(
        catchError((error) => {
          console.error('Error DELETING cartItem', error);
          return of();
        })
      )
      .subscribe((cartItem: ICartItem) => {
        if (cartItem) {
          this.deletedCartItem = cartItem;
        }
      });
    this.calculateTotal();
  }

  handleRemoveCartItemFromCart(cartItemId: string): void {
    this.removeCartItemFromCart(cartItemId);
    this.cart!.cartItemDTOList = this.cart!.cartItemDTOList.filter(
      (cartItem) => cartItem.id !== cartItemId
    );
    this.calculateTotal();
  }

  clearCart(): void {
    console.log('CLEAR cart');
    this.cartService.clearCart();
  }
}
