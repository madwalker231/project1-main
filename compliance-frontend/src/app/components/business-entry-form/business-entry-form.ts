import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BusinessEntityService } from '../../services/business-entity-service';

@Component({
  selector: 'app-business-entry-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './business-entry-form.html',
  styleUrl: './business-entry-form.css',
})
export class BusinessEntryForm implements OnInit {
  entityForm!: FormGroup;
  isEditMode = false;
  entityId?: number;
  constructor(
    private fb: FormBuilder,
    private entityService: BusinessEntityService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initForm();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.entityId = +id;
      this.loadEntityData(this.entityId);
    }
  }

  initForm(): void {
    this.entityForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      entityType: ['', Validators.required],
      jurisdiction: ['', Validators.required],
      status: ['Active', Validators.required]
    })
  }

  loadEntityData(id: number): void {
    this.entityService.getEntityById(id).subscribe({
      next: (entity) => this.entityForm.patchValue(entity),
      error: (err) => console.error('Could not load entity', err)
    });
  }

  onSubmit(): void {
    if (this.entityForm.valid) {
      const action = this.isEditMode
        ? this.entityService.updateEntity(this.entityId!, this.entityForm.value)
        : this.entityService.createEntity(this.entityForm.value);

      action.subscribe({
        next: () => this.router.navigate(['/entities']),
        error: (err) => console.error('Save failed', err)
      });
    }
  }
}
