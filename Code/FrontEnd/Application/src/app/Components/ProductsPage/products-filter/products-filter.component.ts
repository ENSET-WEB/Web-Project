import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IFilterOptions } from '../../../Core/Interface/ifilter-options';
import { ICategory } from '../../../Core/Interface/icategory';

@Component({
  selector: 'app-products-filter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products-filter.component.html',
  styleUrls: ['./products-filter.component.css'],
})
export class ProductsFilterComponent {
  @Input() categoryList!: ICategory[];
  @Output() filterChange = new EventEmitter<IFilterOptions>();
  rangePrice: { min: number; max: number } = { min: 0, max: 1000 };

  isCategoryExpanded = true;
  isPriceExpanded = true;

  selectedCategories: ICategory[] = [];
  filterOptions: IFilterOptions = {
    category: undefined,
    minPrice: this.rangePrice.min,
    maxPrice: this.rangePrice.max,
  };

  toggleCategorySection() {
    this.isCategoryExpanded = !this.isCategoryExpanded;
  }

  togglePriceSection() {
    this.isPriceExpanded = !this.isPriceExpanded;
  }

  emitFilterChange() {
    this.filterChange.emit(this.filterOptions);
  }

  onFilterChange() {
    this.emitFilterChange();
  }

  onCategoryChange(category: ICategory) {
    this.filterOptions.category = category;
    this.onFilterChange();
  }

  resetCategory() {
    this.filterOptions.category = undefined;
    this.onFilterChange();
  }

  resetFilters() {
    this.filterOptions.category = undefined;
    this.filterOptions.minPrice = this.rangePrice.min;
    this.filterOptions.maxPrice = this.rangePrice.max;
    this.onFilterChange();
  }
}
