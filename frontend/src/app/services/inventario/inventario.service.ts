import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductEntryForm, Producto } from '../../models/models';


@Injectable({
  providedIn: 'root'
})
export class InventarioService {
  private baseUrl = 'http://localhost:3000/api/v1.0';

  constructor(private http: HttpClient) {}

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.baseUrl}/products`);
  }

  guardarEntrada(dto: ProductEntryForm): Observable<any> {
    return this.http.post(`${this.baseUrl}/warehouse-entries`, dto);
  }
}
