import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { BusinessEntity } from '../../models/business-entity.model';
import { BusinessEntityService } from '../../services/business-entity-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-business-entity-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './business-entity-list.html',
  styleUrl: './business-entity-list.css',
})
export class BusinessEntityList implements OnInit {
  entities: BusinessEntity[] = [];
  constructor(
    private entityService: BusinessEntityService,
    private cdr: ChangeDetectorRef
  ) { }
  ngOnInit(): void {
    this.loadEntities();
  }
  loadEntities(): void{
    this.entityService.getEntities().subscribe({
      next: (data) => {
        console.log('Raw data from database.');
        this.entities = [...data];
        console.log('Data assigned to component variable:', this.entities.length);
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Connection failed:', err)
    });
  }
}
