import { Routes } from '@angular/router';
import { MainComponent } from './Components/main/main.component';
export const routes: Routes = [
  {
    path: 'main',
    component: MainComponent,
  },
  {
    path: '',
    redirectTo: 'main',
    pathMatch: 'full',
  },
];
