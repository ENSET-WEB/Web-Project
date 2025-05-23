import { Component, OnInit, ɵRuntimeError } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../../../Core/services/product.service';
import { IProduct } from '../../../Core/interface/iproduct';
import { catchError, finalize } from 'rxjs/operators';
import { of } from 'rxjs';
import { CartService } from '../../../Core/services/cart.service';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css',
})
export class ProductDetailsComponent implements OnInit {
  product: IProduct | null = null;
  isLoading = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProduct(productId);
    }
  }

  handleAddProductToAppUserCart() {
    console.log('Adding...');

    if (!this.product) throw console.log('Product inexistent');

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

  private loadProduct(productId: string): void {
    this.productService
      .getProductById(productId)
      .pipe(
        catchError((error) => {
          this.error = 'Failed to load product details';
          this.isLoading = false;
          console.error('Error loading product:', error);
          return of(null);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((product) => {
        this.product = product;
      });
  }
}
