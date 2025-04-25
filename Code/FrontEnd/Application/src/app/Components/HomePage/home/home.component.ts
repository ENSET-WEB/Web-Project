import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService } from '../../../Core/Services/product.service';
import { IProduct } from '../../../Core/Interface/iproduct';
import { ProductCardComponent } from '../../ProductsPage/product-card/product-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, ProductCardComponent],
  template: `
    <div class="home-container">
      <!-- Hero Section -->
      <section class="hero">
        <div class="hero-content">
          <h1>Discover Amazing Products</h1>
          <p>Find the best deals on quality items</p>
          <div class="cta-buttons">
            <a routerLink="/products" class="cta-primary">Shop Now</a>
            <a routerLink="/categories" class="cta-secondary"
              >Browse Categories</a
            >
          </div>
        </div>
      </section>

      <!-- Featured Products Section -->
      <section class="featured-products" *ngIf="featuredProducts.length > 0">
        <h2>Featured Products</h2>
        <div class="products-grid">
          <app-product-card
            *ngFor="let product of featuredProducts"
            [product]="product"
          ></app-product-card>
        </div>
      </section>

      <!-- Categories Preview -->
      <section class="categories-preview">
        <h2>Shop by Category</h2>
        <div class="category-cards">
          <div class="category-card" *ngFor="let category of categories">
            <div class="category-icon">
              <i [class]="category.icon"></i>
            </div>
            <h3>{{ category.name }}</h3>
            <a
              [routerLink]="['/products']"
              [queryParams]="{ category: category.id }"
            >
              Browse {{ category.name }}
            </a>
          </div>
        </div>
      </section>
    </div>
  `,
  styles: [
    `
      .home-container {
        width: 100%;
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 1rem;
      }

      .hero {
        background: linear-gradient(
          135deg,
          var(--primary-color) 0%,
          var(--accent-color, #2c3e50) 100%
        );
        border-radius: 1rem;
        padding: 4rem 2rem;
        margin: 2rem 0;
        text-align: center;
        color: white;
        position: relative;
        overflow: hidden;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
      }

      .hero::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: url('/assets/hero-pattern.svg');
        opacity: 0.15;
        z-index: 1;
      }

      .hero-content {
        position: relative;
        z-index: 2;
        backdrop-filter: blur(4px);
        padding: 2rem;
        border-radius: 1rem;
        background: rgba(255, 255, 255, 0.1);
      }

      .hero h1 {
        font-size: 3rem;
        font-weight: 700;
        margin-bottom: 1rem;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
      }

      .hero p {
        font-size: 1.25rem;
        margin-bottom: 2rem;
        opacity: 0.95;
      }

      .cta-buttons {
        display: flex;
        gap: 1rem;
        justify-content: center;
      }

      .cta-primary,
      .cta-secondary {
        padding: 0.75rem 1.5rem;
        border-radius: 0.5rem;
        font-weight: 600;
        transition: all 0.3s ease;
        text-decoration: none;
      }

      .cta-primary {
        background: white;
        color: var(--primary-color);
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }

      .cta-secondary {
        background: rgba(255, 255, 255, 0.15);
        color: white;
        border: 2px solid rgba(255, 255, 255, 0.8);
        backdrop-filter: blur(4px);
      }

      .cta-primary:hover {
        transform: translateY(-2px);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
      }

      .cta-secondary:hover {
        transform: translateY(-2px);
        background: rgba(255, 255, 255, 0.2);
      }

      .featured-products {
        padding: 3rem 0;
      }

      .featured-products h2 {
        font-size: 2rem;
        margin-bottom: 2rem;
        text-align: center;
        color: var(--text-color, #2c3e50);
      }

      .products-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 2rem;
        padding: 1rem;
      }

      .categories-preview {
        padding: 3rem 0;
      }

      .categories-preview h2 {
        font-size: 2rem;
        margin-bottom: 2rem;
        text-align: center;
        color: var(--text-color, #2c3e50);
      }

      .category-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 2rem;
        padding: 1rem;
      }

      .category-card {
        background: var(--card-bg, rgba(255, 255, 255, 0.8));
        border-radius: 1rem;
        padding: 2rem;
        text-align: center;
        transition: all 0.3s ease;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        backdrop-filter: blur(10px);
        border: 1px solid rgba(var(--border-color, 0, 0, 0), 0.1);
      }

      :host-context(.dark-mode) .category-card {
        background: var(--card-bg-dark, rgba(44, 62, 80, 0.8));
        border-color: rgba(255, 255, 255, 0.1);
      }

      .category-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
      }

      .category-icon {
        font-size: 2.5rem;
        color: var(--primary-color);
        margin-bottom: 1rem;
        transition: transform 0.3s ease;
      }

      .category-card:hover .category-icon {
        transform: scale(1.1);
      }

      .category-card h3 {
        font-size: 1.25rem;
        margin-bottom: 1rem;
        color: var(--text-color, #2c3e50);
      }

      :host-context(.dark-mode) .category-card h3 {
        color: var(--text-color-dark, #ffffff);
      }

      .category-card a {
        color: var(--primary-color);
        text-decoration: none;
        font-weight: 600;
        position: relative;
        padding-bottom: 2px;
      }

      .category-card a::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 0;
        height: 2px;
        background: var(--primary-color);
        transition: width 0.3s ease;
      }

      .category-card a:hover::after {
        width: 100%;
      }

      @media (max-width: 768px) {
        .hero {
          padding: 3rem 1.5rem;
        }

        .hero h1 {
          font-size: 2.25rem;
        }

        .hero p {
          font-size: 1.1rem;
        }

        .cta-buttons {
          flex-direction: column;
        }

        .category-cards {
          grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
        }
      }

      @media (prefers-color-scheme: dark) {
        .category-card {
          background: var(--card-bg-dark, rgba(44, 62, 80, 0.8));
          border-color: rgba(255, 255, 255, 0.1);
        }

        .category-card h3 {
          color: var(--text-color-dark, #ffffff);
        }
      }
    `,
  ],
})
export class HomeComponent implements OnInit {
  featuredProducts: IProduct[] = [];
  categories = [
    { id: 1, name: 'Electronics', icon: 'fas fa-laptop' },
    { id: 2, name: 'Clothing', icon: 'fas fa-tshirt' },
    { id: 3, name: 'Home & Garden', icon: 'fas fa-home' },
    { id: 4, name: 'Sports', icon: 'fas fa-futbol' },
  ];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadFeaturedProducts();
  }

  private loadFeaturedProducts(): void {
    this.productService.fetchProductList().subscribe((products) => {
      this.featuredProducts = products.slice(0, 4);
    });
  }
}
