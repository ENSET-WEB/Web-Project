import { Injectable } from '@angular/core';
import { IProduct } from '../interface/iproduct';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  productList: IProduct[] = [];
  private apiUrl = environment.apiUrl;
  private productsUrl = `${this.apiUrl}/product`;
  private deleteProductUrl = `${this.apiUrl}/product/deleteProduct`;

  constructor(private http: HttpClient) { }

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

  deleteProduct(productId: string) {
    // const options = {
    //   headers: new HttpHeaders().set(
    //     'Content-Type',
    //     'application/x-www-form-urlencoded'
    //   ),
    // };

    const params = new HttpParams()
      .set('productId', productId)
    return this.http.delete(this.deleteProductUrl, { params })
    // .pipe(
    //   // tap(() => {
    //   //   console.log("Product Deleted Successfully");
    //   // })
    // ).subscribe((resp) => {
    //   console.log(resp);

    // })

  }
}
