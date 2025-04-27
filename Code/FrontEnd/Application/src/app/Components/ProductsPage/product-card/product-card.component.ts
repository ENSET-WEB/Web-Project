import { Component, Input } from '@angular/core';
import { IProduct } from '../../../Core/interface/iproduct';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CartService } from '../../../Core/services/cart.service';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  @Input() product!: IProduct;
  constructor(private cartService: CartService) {}
  onclic = () => console.log('hello');
  handleAddProductToAppUserCart() {
    console.log('Adding...');

    this.cartService
      .addProductToUserCart(this.product.id)
      .pipe(
        catchError((err) => {
          console.error('Error adding product to cart: ', err, this.product);
          return of();
        })
      )
      .subscribe((cart) => {
        console.log('Product added to cart successfully', cart);
      });
  }
}
