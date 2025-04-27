import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { ICartItem } from '../interface/icart-item';
import { environment } from '../../../environments/environment';
import { ICart } from '../interface/icart';
import { IAppUser } from '../interface/iapp-user';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  apiUrl: string = environment.apiUrl;
  cartUrl: string = `${this.apiUrl}/cart`;
  appUser: IAppUser | null = null;
  private cartSizeSubject = new BehaviorSubject<number>(0);
  cartSize$ = this.cartSizeSubject.asObservable();

  constructor(private http: HttpClient, private authService: AuthService) {
    this.getUserInfo();
    if (this.appUser) {
      this.updateCartSize();
    }
  }

  getUserInfo() {
    this.appUser = this.authService.getUserInfo();
  }

  private updateCartSize() {
    this.getCartSize().subscribe((size) => this.cartSizeSubject.next(size));
  }

  addProductToUserCart(productId: string): Observable<ICart> {
    const params = new HttpParams()
      .set('appUserId', this.appUser!.id)
      .set('productId', productId);
    return this.http.post<ICart>(`${this.cartUrl}/addCartItem`, params).pipe(
      tap(() => {
        this.updateCartSize();
      })
    );
  }

  getCartByAppUserId(): Observable<ICart> {
    return this.http.get<ICart>(`${this.cartUrl}/${this.appUser!.id}`);
  }

  getCartSize(): Observable<number> {
    return this.http.get<number>(`${this.cartUrl}/${this.appUser!.id}/size`);
  }

  updateQuantity(id: string, newQuantity: number) {
    throw new Error('Method not implemented.');
  }
  clearCart() {
    throw new Error('Method not implemented.');
  }

  removeCartItemFromCart(cartItemId: string): Observable<ICartItem> {
    const params = new HttpParams().set('cartItemId', cartItemId);
    return this.http
      .delete<ICartItem>(`${this.cartUrl}/deleteCartItem`, {
        params,
      })
      .pipe(
        tap(() => {
          this.updateCartSize();
        })
      );
  }
}
