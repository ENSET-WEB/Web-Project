import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../../../Core/services/product.service';
import { IProduct } from '../../../Core/interface/iproduct';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-manage-products',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.css']
})
export class ManageProductsComponent implements OnInit {
  products: IProduct[] = [];
  isLoading = false;
  errorMessage = '';
  successMessage = '';
  showAddModal = false;
  showEditModal = false;
  selectedProduct: IProduct | null = null;
  productForm: FormGroup;

  constructor(
    private productService: ProductService,
    private fb: FormBuilder
  ) {
    this.productForm = this.fb.group({
      name:"",
      description:"",
      price:"",
      category:"",
      imageUrl:"",
    });
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.isLoading = true;
    this.productService.fetchProductList().pipe(
      catchError(error => {
        this.errorMessage = 'Error loading products';
        console.error('Error loading products:', error);
        return of([]);
      })
    ).subscribe(products => {
      this.products = products;
      this.isLoading = false;
    });
  }

  openAddModal() {
    this.showAddModal = true;
    this.productForm.reset();
  }

  openEditModal(product: IProduct) {
    this.selectedProduct = product;
    this.productForm.patchValue(product);
    this.showEditModal = true;
  }

  closeModal() {
    this.showAddModal = false;
    this.showEditModal = false;
    this.selectedProduct = null;
    this.productForm.reset();
  }

  // onSubmit() {
  //   if (this.productForm.valid) {
  //     this.isLoading = true;
  //     const productData = this.productForm.value;

  //     if (this.showEditModal && this.selectedProduct) {
  //       // Update existing product
  //       this.productService.updateProduct(this.selectedProduct.id, productData).pipe(
  //         catchError(error => {
  //           this.errorMessage = 'Error updating product';
  //           console.error('Error updating product:', error);
  //           return of(null);
  //         })
  //       ).subscribe(response => {
  //         if (response) {
  //           this.successMessage = 'Product updated successfully';
  //           this.loadProducts();
  //           this.closeModal();
  //         }
  //         this.isLoading = false;
  //       });
  //     } else {
  //       // Add new product
  //       this.productService.addProduct(productData).pipe(
  //         catchError(error => {
  //           this.errorMessage = 'Error adding product';
  //           console.error('Error adding product:', error);
  //           return of(null);
  //         })
  //       ).subscribe(response => {
  //         if (response) {
  //           this.successMessage = 'Product added successfully';
  //           this.loadProducts();
  //           this.closeModal();
  //         }
  //         this.isLoading = false;
  //       });
  //     }
  //   }
  // }

  deleteProduct(productId: string) {
    if (confirm('Are you sure you want to delete this product?')) {
      this.isLoading = true;
      this.productService.deleteProduct(productId).pipe(
        catchError(error => {
          this.errorMessage = 'Error deleting product';
          console.error('Error deleting product:', error);
          return of(null);
        })
      ).subscribe(response => {
        if (response) {
          this.successMessage = 'Product deleted successfully';
          this.loadProducts();
        }
        this.isLoading = false;
      });
    }
  }

  clearMessages() {
    this.errorMessage = '';
    this.successMessage = '';
  }
}
