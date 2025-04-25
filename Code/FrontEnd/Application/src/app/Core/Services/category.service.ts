import { Injectable } from '@angular/core';
import { ICategory } from '../Interface/icategory';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private apiUrl = environment.apiUrl;
  private categoriesUrl = `${this.apiUrl}/category`;

  constructor(private http: HttpClient) {}

  getCategoryList(): Observable<ICategory[]> {
    return this.http.get<ICategory[]>(this.categoriesUrl);
  }
}
