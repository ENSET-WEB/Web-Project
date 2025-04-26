import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService } from '../../../Core/services/product.service';
import { IProduct } from '../../../Core/Interface/iproduct';
import { ProductCardComponent } from '../../ProductsPage/product-card/product-card.component';
import { CategoryService } from '../../../Core/services/category.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { ICategory } from '../../../Core/Interface/icategory';

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
        catchError((error) => {
          console.error('Error loading categories:', error);
          return of([]);
        })
      )
      .subscribe((categories) => {
        this.categories = categories;
      });
  }

  private loadFeaturedProducts(): void {
    this.productService.fetchProductList().subscribe((products) => {
      this.featuredProducts = products.slice(0, 4);
    });
  }
}
