import { Injectable } from '@angular/core';
import { IProduct } from '../Interface/iproduct';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  productList: IProduct[] = [];
  private apiUrl = environment.apiUrl;
  private productsUrl = `${this.apiUrl}/product`;
  constructor(private http: HttpClient) {}

  getProductList(): IProduct[] {
    return this.productList;
  }

  fetchProductList(): Observable<IProduct[]> {
    return this.http.get<IProduct[]>(this.productsUrl);
  }

  addProduct(product: IProduct): void {
    this.productList.push(product);
  }
}
