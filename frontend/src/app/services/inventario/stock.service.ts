import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockProducto } from '../../models/models';



@Injectable({
  providedIn: 'root'
})
export class StockService {
  private apiUrl = 'http://localhost:3000/api/v1.0/inventory';

  constructor(private http: HttpClient) {}

  getInventario(): Observable<StockProducto[]> {
    return this.http.get<StockProducto[]>(this.apiUrl);
  }
}
