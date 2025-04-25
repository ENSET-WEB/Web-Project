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
          var(--accent-color) 100%
        );
        border-radius: 1rem;
        padding: 4rem 2rem;
        margin: 2rem 0;
        text-align: center;
        color: white;
        position: relative;
        overflow: hidden;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }

      .hero::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: url('/assets/hero-pattern.svg');
        opacity: 0.1;
        z-index: 1;
      }

      .hero-content {
        position: relative;
        z-index: 2;
      }

      .hero h1 {
        font-size: 3rem;
        font-weight: 700;
        margin-bottom: 1rem;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
      }

      .hero p {
        font-size: 1.25rem;
        margin-bottom: 2rem;
        opacity: 0.9;
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
        transition: transform 0.2s ease;
        text-decoration: none;
      }

      .cta-primary {
        background: white;
        color: var(--primary-color);
      }

      .cta-secondary {
        background: rgba(255, 255, 255, 0.2);
        color: white;
        border: 2px solid white;
      }

      .cta-primary:hover,
      .cta-secondary:hover {
        transform: translateY(-2px);
      }

      .featured-products {
        padding: 3rem 0;
      }

      .featured-products h2 {
        font-size: 2rem;
        margin-bottom: 2rem;
        text-align: center;
        color: var(--text-color);
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
        color: var(--text-color);
      }

      .category-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 2rem;
        padding: 1rem;
      }

      .category-card {
        background: white;
        border-radius: 1rem;
        padding: 2rem;
        text-align: center;
        transition: transform 0.2s ease;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
      }

      .category-card:hover {
        transform: translateY(-5px);
      }

      .category-icon {
        font-size: 2.5rem;
        color: var(--primary-color);
        margin-bottom: 1rem;
      }

      .category-card h3 {
        font-size: 1.25rem;
        margin-bottom: 1rem;
        color: var(--text-color);
      }

      .category-card a {
        color: var(--primary-color);
        text-decoration: none;
        font-weight: 600;
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
      // Get first 4 products as featured (you might want to modify this logic)
      this.featuredProducts = products.slice(0, 4);
    });
  }
}
