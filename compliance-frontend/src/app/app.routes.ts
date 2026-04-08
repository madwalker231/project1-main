import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { BusinessEntityList } from './components/business-entity-list/business-entity-list';

export const routes: Routes = [
    { path: 'dashboard', component: Dashboard},
    { path: 'entities', component: BusinessEntityList},
    { path: '', redirectTo: '/dashboard', pathMatch: 'full'}
];
