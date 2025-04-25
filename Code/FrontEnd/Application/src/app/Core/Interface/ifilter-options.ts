import { ICategory } from './icategory';

export interface IFilterOptions {
  category?: ICategory;
  minPrice?: number;
  maxPrice?: number;
}
