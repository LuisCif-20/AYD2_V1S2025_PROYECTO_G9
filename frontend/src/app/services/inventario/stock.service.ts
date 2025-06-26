import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockProducto } from '../../models/models';
import { environment } from '../../../environments/environment';

const baseUrl = environment.IMPORCOMGUA + '/inventory';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  constructor(private http: HttpClient) {}

  getInventario(): Observable<StockProducto[]> {
    return this.http.get<StockProducto[]>(baseUrl);
  }
}
