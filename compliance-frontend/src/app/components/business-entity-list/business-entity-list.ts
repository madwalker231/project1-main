import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { BusinessEntity } from '../../models/business-entity.model';
import { BusinessEntityService } from '../../services/business-entity-service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-business-entity-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
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
  deleteEntity(id: number): void {
    if(confirm('Are you sure you want to delete this entity? This will also remove associated obligations.')) {
      this.entityService.deleteEntity(id).subscribe({
        next: () => {
          this.entities = this.entities.filter(e => e.id !== id);
        },
        error: (err) => console.error('Delete failed: ', err)
      });
    }
  }
}
