import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { BusinessEntityList } from './components/business-entity-list/business-entity-list';
import { BusinessEntryForm } from './components/business-entry-form/business-entry-form';

export const routes: Routes = [
    { path: 'dashboard', component: Dashboard},
    { path: 'entities', component: BusinessEntityList},
    { path: 'entities/new', component: BusinessEntryForm},
    { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    { path: '**', redirectTo: '/dashboard'}
];
