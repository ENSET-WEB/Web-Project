import { Component, OnInit } from '@angular/core';
import { IProduct } from '../../../Core/Interface/iproduct';
import { ProductService } from '../../../Core/Services/product.service';
import { catchError, finalize } from 'rxjs/operators';
import { of } from 'rxjs';
import { ProductCardComponent } from '../product-card/product-card.component';
import { CommonModule } from '@angular/common';
import { ProductsFilterComponent } from '../products-filter/products-filter.component';
import { IFilterOptions } from '../../../Core/Interface/ifilter-options';
import { FormsModule } from '@angular/forms';
import { ICategory } from '../../../Core/Interface/icategory';
import { CategoryService } from '../../../Core/Services/category.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    ProductCardComponent,
    CommonModule,
    ProductsFilterComponent,
    FormsModule,
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',
})
export class ProductsComponent implements OnInit {
  productList: IProduct[] = [];
  filteredProducts: IProduct[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  searchQuery: string = '';
  categoryList: ICategory[] = [];
  filterOptions: IFilterOptions = {};
  rangePrice: { min: number; max: number } = { min: 0, max: 0 };

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.loadStartup();
  }
  applyFilterOptions(): void {
    this.filteredProducts = this.productList.filter((product) => {
      const matchSearch =
        !this.searchQuery ||
        product.name.toLowerCase().includes(this.searchQuery.toLowerCase());

      const matchCategory =
        !this.filterOptions.category ||
        product.categoryName === this.filterOptions.category.name;

      const matchPrice =
        (!this.filterOptions.maxPrice ||
          product.price < this.filterOptions.maxPrice) &&
        (!this.filterOptions.minPrice ||
          product.price > this.filterOptions.minPrice);

      return matchSearch && matchCategory && matchPrice;
    });
  }
  retrieveFilterOptions(filterOptions: IFilterOptions): void {
    this.filterOptions = filterOptions;
    console.log(this.filterOptions);
    this.applyFilterOptions();
  }
  loadStartup(): void {
    this.loadCategories();
    this.loadProducts();
  }
  loadCategories(): void {
    this.isLoading = true;
    this.categoryService
      .getCategoryList()
      .pipe(
        catchError((error) => {
          this.errorMessage =
            'Failed to load categories. Please try again later.';
          console.error('Error loading categories:', error);
          return of([]);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((categoryList) => {
        this.categoryList = categoryList;
      });
  }

  loadProducts(): void {
    this.isLoading = true;
    this.productService
      .fetchProductList()
      .pipe(
        catchError((error) => {
          this.errorMessage =
            'Failed to load products. Please try again later.';
          console.error('Error loading products:', error);
          return of([]);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((productList) => {
        this.productList = productList;
        this.filteredProducts = [...this.productList];
      });
  }
}
