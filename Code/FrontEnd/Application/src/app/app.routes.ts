import { Routes } from '@angular/router';
import { ProductsComponent } from './Components/ProductsPage/products/products.component';
import { ProductDetailsComponent } from './Components/ProductsPage/product-details/product-details.component';
import { HomeComponent } from './Components/HomePage/home/home.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'products',
    component: ProductsComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
];
