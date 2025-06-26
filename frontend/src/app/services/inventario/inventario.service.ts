import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductEntryForm, Producto } from '../../models/models';
import { environment } from '../../../environments/environment';

const baseUrl = environment.IMPORCOMGUA;


@Injectable({
  providedIn: 'root'
})
export class InventarioService {
  

  constructor(private http: HttpClient) {}

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${baseUrl}/products`);
  }

  guardarEntrada(dto: ProductEntryForm): Observable<any> {
    return this.http.post(`${baseUrl}/warehouse-entries`, dto);
  }
}
