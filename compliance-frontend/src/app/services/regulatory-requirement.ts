import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegulatoryRequirements } from '../models/Regulatory-req.model';

@Injectable({
  providedIn: 'root',
})
export class RegulatoryRequirement {
  private apiUrl = 'http://localhost:8080/api/v1/requirements';

  constructor(private http: HttpClient) {}

  getAll(): Observable<RegulatoryRequirements[]> {
    return this.http.get<RegulatoryRequirements[]>(this.apiUrl);
  }

  getById(id: number): Observable<RegulatoryRequirements> {
    return this.http.get<RegulatoryRequirements>(`${this.apiUrl}/${id}`);
  }

  createRegulatory(req: RegulatoryRequirement): Observable<RegulatoryRequirement> {
    return this.http.post<RegulatoryRequirement>(this.apiUrl, req);
  }

  updateRegulatory(id: number, req: RegulatoryRequirement): Observable<RegulatoryRequirement> {
    return this.http.put<RegulatoryRequirement>(`${this.apiUrl}/${id}`, req);
  }

  deleteRegulatory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
