import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private darkMode = new BehaviorSubject<boolean>(this.isDarkModePreferred());
  darkMode$ = this.darkMode.asObservable();

  constructor() {
    this.initializeTheme();
  }

  private isDarkModePreferred(): boolean {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
      return savedTheme === 'dark';
    }
    return window.matchMedia('(prefers-color-scheme: dark)').matches;
  }

  private initializeTheme(): void {
    this.darkMode.subscribe((isDark) => {
      document.documentElement.classList.toggle('dark-theme', isDark);
      localStorage.setItem('theme', isDark ? 'dark' : 'light');
    });

    // Listen for system theme changes
    window
      .matchMedia('(prefers-color-scheme: dark)')
      .addEventListener('change', (e) => {
        if (localStorage.getItem('theme') === null) {
          this.darkMode.next(e.matches);
        }
      });
  }

  toggleTheme(): void {
    this.darkMode.next(!this.darkMode.value);
  }

  isDark(): boolean {
    return this.darkMode.value;
  }
}
