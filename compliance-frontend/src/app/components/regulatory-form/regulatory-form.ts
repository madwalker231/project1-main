import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { RegulatoryRequirement } from '../../services/regulatory-requirement';
import { RegulatoryRequirements } from '../../models/Regulatory-req.model';

@Component({
  selector: 'app-regulatory-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './regulatory-form.html',
  styleUrl: './regulatory-form.css',
})
export class RegulatoryForm implements OnInit{
  reqForm!: FormGroup;
  isEditMode = false;
  reqId?: number;
  
  constructor(
    private fb: FormBuilder,
    private reqService: RegulatoryRequirement,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    this.initForm();
    const id = this.route.snapshot.paramMap.get('id');
    if(id) {
      this.isEditMode = true;
      this.reqId = +id;
      this.loadRequirements(+id);
    }
  }

  initForm(): void {
    this.reqForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      governingBody: ['', Validators.required],
      requirementType: ['', Validators.required],
      effectiveDate: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  loadRequirements(id: number): void {
    this.reqService.getById(id).subscribe({
      next: (data: RegulatoryRequirements) => {
        if (data.effectiveDate) {
          data.effectiveDate = new Date(data.effectiveDate).toISOString().split('T')[0];
        }
        this.reqForm.patchValue(data);
      },
      error: (err) => console.error('Load Failed', err)
    });
  }

  onSubmit(): void {
    if(this.reqForm.valid) {
      const action = this.isEditMode
        ? this.reqService.updateRegulatory(this.reqId!, this.reqForm.value)
        : this.reqService.createRegulatory(this.reqForm.value);
      action.subscribe({
        next: () => this.router.navigate(['/regulations']),
        error:(err) => console.error('Save Failed', err)
      });
    }
  }
}
