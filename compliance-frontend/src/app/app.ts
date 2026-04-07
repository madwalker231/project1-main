import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BusinessEntityList } from './components/business-entity-list/business-entity-list';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BusinessEntityList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('compliance-frontend');
}
