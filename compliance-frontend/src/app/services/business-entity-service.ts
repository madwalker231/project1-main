import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BusinessEntity } from '../models/business-entity.model';

@Injectable({
  providedIn: 'root',
})
export class BusinessEntityService {
  // URL to Business Entities backend
  private apiUrl = 'http://localhost:8080/api/v1/entities';
  constructor(private http: HttpClient) { }

  //GET Business Entities
  getEntities(): Observable<BusinessEntity[]> {
    return this.http.get<BusinessEntity[]>(this.apiUrl);
  }

  //POST new Business Entities
  createEntity(entity: BusinessEntity): Observable<BusinessEntity> {
    return this.http.post<BusinessEntity>(this.apiUrl, entity);
  }

  //DELETE Business Entity
  deleteEntity(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
