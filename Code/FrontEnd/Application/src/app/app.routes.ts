import { Routes } from '@angular/router';
import { ProductsComponent } from './components/ProductsPage/products/products.component';
import { ProductDetailsComponent } from './components/ProductsPage/product-details/product-details.component';
import { HomeComponent } from './components/HomePage/home/home.component';
import { AppUserComponent } from './components/UserPage/appuser/appuser.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'products',
    component: ProductsComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
  {
    path: 'user',
    component: AppUserComponent,
  },
];
