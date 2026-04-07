export interface BusinessEntity {
  id: number;
  name: string;
  entityType: string;
  jurisdiction: string;
  status: string;
  obligations?: any[]; 
}