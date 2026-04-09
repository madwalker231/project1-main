import { CommonModule } from '@angular/common';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RegulatoryRequirements } from '../../models/Regulatory-req.model';
import { RegulatoryRequirement } from '../../services/regulatory-requirement';

@Component({
  selector: 'app-regulatory-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './regulatory-list.html',
  styleUrl: './regulatory-list.css',
})
export class RegulatoryList implements OnInit {
  requirements: RegulatoryRequirements[] = [];

  constructor(
    private reqService: RegulatoryRequirement,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.loadRequirements();
  }

  loadRequirements(): void {
    this.reqService.getAll().subscribe({
      next: (data) => {
        this.requirements = data,
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Failed to load catalog', err)
    });
  }

  deleteReg(id: number): void {
    if (confirm('Delete this requirement? Warning: this may impact compliance score for associated entities')) {
      this.reqService.deleteRegulatory(id).subscribe({
        next: () => this.requirements = this.requirements.filter(r => r.id !== id),
        error: (err) => console.error('Delete failed', err)
      });
    }
  }
}
