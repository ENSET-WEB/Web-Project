import { Routes } from '@angular/router';
import { ProductsComponent } from './components/ProductsPage/products/products.component';
import { ProductDetailsComponent } from './components/ProductsPage/product-details/product-details.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './Core/guards/auth.guard';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { AuthenticatedGuard } from './Core/guards/authenticated.guard';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ManageProductsComponent } from './components/admin/manage-products/manage-products.component';
import { ManageUsersComponent } from './components/admin/manage-users/manage-users.component';
import { AdminGuard } from './Core/guards/admin.guard';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthenticatedGuard],
  },
  {
    path: 'register',
    component: RegistrationComponent,
    canActivate: [AuthenticatedGuard],
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent,
    canActivate: [AuthGuard],
  },
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard],
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
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'checkout',
    component: CheckoutComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/manageProducts',
    component: ManageProductsComponent,
    canActivate:[AdminGuard]
  },
  {
    path: 'admin/manageUsers',
    component: ManageUsersComponent,
    canActivate:[AdminGuard]

  }
];
