import { Injectable } from '@angular/core';
import { IProduct } from '../interface/iproduct';
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

  getProductById(id: string): Observable<IProduct> {
    return this.http.get<IProduct>(`${this.productsUrl}/${id}`);
  }

  addProduct(product: IProduct): void {
    this.productList.push(product);
  }

  getMaxPriceProduct(productList: IProduct[]): IProduct {
    return productList.reduce((max, current) => {
      return current.price > max.price ? current : max;
    }, productList[0]);
  }

  getMinPriceProduct(productList: IProduct[]): IProduct {
    return productList.reduce((min, current) => {
      return current.price < min.price ? current : min;
    }, productList[0]);
  }

  getProductByPriceRange(
    productList: IProduct[],
    minPrice: number,
    maxPrice: number
  ): IProduct[] {
    return productList.filter((product) => {
      return product.price >= minPrice && product.price <= maxPrice;
    });
  }
}
