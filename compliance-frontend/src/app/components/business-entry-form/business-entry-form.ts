import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BusinessEntityService } from '../../services/business-entity-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-business-entry-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './business-entry-form.html',
  styleUrl: './business-entry-form.css',
})
export class BusinessEntryForm implements OnInit {
  entityForm!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private entityService: BusinessEntityService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.entityForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      entityType: ['', Validators.required],
      jurisdiction: ['', Validators.required],
      status: ['Active', Validators.required]
    });
  }
  onSubmit(): void {
    if (this.entityForm.valid) {
      this.entityService.createEntity(this.entityForm.value).subscribe({
        next: () => {
          console.log('Entry created successfully.');
          this.router.navigate(['/entities']);
        },
        error: (err) => console.error('Form submission failed:', err)
      });
    }
  }
}
