import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductCardComponent } from '../ProductsPage/product-card/product-card.component';
import { ProductService } from '../../Core/services/product.service';
import { IProduct } from '../../Core/interface/iproduct';
import { ICategory } from '../../Core/interface/icategory';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CategoryService } from '../../Core/services/category.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, ProductCardComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  featuredProducts: IProduct[] = [];
  categories: ICategory[] = [];
  error: string | null = null;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.loadFeaturedProducts();
    this.loadCategories();
  }

  loadCategories() {
    this.categoryService
      .getCategoryList()
      .pipe(
        catchError((error: any) => {
          console.error('Error loading categories:', error);
          return of([]);
        })
      )
      .subscribe((categories: ICategory[]) => {
        this.categories = categories;
      });
  }

  private loadFeaturedProducts(): void {
    this.productService.fetchProductList().subscribe((products: IProduct[]) => {
      this.featuredProducts = products.slice(0, 4);
    });
  }
}
