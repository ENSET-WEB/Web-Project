import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { ICartItem } from '../interface/icart-item';
import { environment } from '../../../environments/environment';
import { ICart } from '../interface/icart';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  apiUrl: string = environment.apiUrl;
  cartUrl: string = `${this.apiUrl}/cart`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  // addProductToUserCart(appUserId: string, productId: string) :Observable<ICart>{
  //   const params = new HttpParams()
  //     .set('appUserId', appUserId)
  //     .set('productId', productId);
  //   return this.http.post<ICart>();
  // }

  getCartItemByAppUserId(appUserId: string): Observable<ICart> {
    return this.http.get<ICart>(`${this.cartUrl}/${appUserId}`);
  }

  updateQuantity(id: string, newQuantity: number) {
    throw new Error('Method not implemented.');
  }
  clearCart() {
    throw new Error('Method not implemented.');
  }

  removeFromCart(cartItemId: string) {
    const params = new HttpParams().set('cartItemId', cartItemId);
    this.http.delete<ICartItem>(`${this.cartUrl}/deleteCartItem`, {params});
  }
}
