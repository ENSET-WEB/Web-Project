import { Routes } from '@angular/router';
import { ProductsComponent } from './Components/ProductsPage/products/products.component';
import { ProductDetailsComponent } from './Components/ProductsPage/product-details/product-details.component';

export const routes: Routes = [
  {
    path: 'products',
    component: ProductsComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
  {
    path: '',
    redirectTo: 'products',
    pathMatch: 'full',
  },
];
