import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ThemeService } from '../../Core/services/theme.service';
import { AuthService } from '../../Core/services/auth.service';
import { CartService } from '../../Core/services/cart.service';
import { catchError, of, Subscription } from 'rxjs';
import { IAppUser } from '../../Core/interface/iapp-user';
import { IAppRole } from '../../Core/interface/iapp-role';

@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  cartSize: number = 0;
  private cartSizeSubscription?: Subscription;
  // private isAdmin: boolean = false;
  // private userInfo: IAppUser | null = null

  constructor(
    public themeService: ThemeService,
    public authService: AuthService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.cartSizeSubscription = this.cartService.cartSize$.subscribe(
      (size) => (this.cartSize = size)
    );
    this.getCartSize();


   
  }

  ngOnDestroy(): void {
    if (this.cartSizeSubscription) {
      this.cartSizeSubscription.unsubscribe();
    }
  }

  getCartSize() {
    this.cartService
      .getCartSize()
      .pipe(
        catchError((err) => {
          console.error('Error in retrieving CartSize', err);
          return of(0);
        })
      )
      .subscribe((size) => {
        this.cartSize = size;
      });
  }

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }

  handleLogout(): void {
    this.authService.confirmateLogout(true)
  }

}
