import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { BusinessEntityList } from './components/business-entity-list/business-entity-list';
import { BusinessEntryForm } from './components/business-entry-form/business-entry-form';
import { RegulatoryList } from './components/regulatory-list/regulatory-list';
import { RegulatoryForm } from './components/regulatory-form/regulatory-form';

export const routes: Routes = [
    { path: 'dashboard', component: Dashboard},
    { path: 'entities', component: BusinessEntityList},
    { path: 'entities/new', component: BusinessEntryForm},
    { path: 'entities/edit/:id', component: BusinessEntryForm},
    { path: 'requirements', component: RegulatoryList},
    { path: 'requirements/edit/:id', component: RegulatoryForm},
    { path: 'requirements/new', component: RegulatoryForm},
    { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    { path: '**', redirectTo: '/dashboard'}
];
