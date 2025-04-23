import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../Core/Services/product-service.service';
import { IProduct } from '../../Core/Interface/iproduct';

@Component({
  selector: 'app-main',
  imports: [],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css',
})
export class MainComponent implements OnInit {
  productList: IProduct[] = [];
  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.fetchProductList().subscribe((products) => {
      console.log(products);
    });
  }
}
