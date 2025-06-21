import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Banco, PagoRequest } from '../../models/models';



@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private baseUrl = 'http://localhost:3000/api/v1.0';

  constructor(private http: HttpClient) {}

  obtenerBancos(): Observable<Banco[]> {
    return this.http.get<Banco[]>(`${this.baseUrl}/banks`);
  }

  registrarPago(payload: PagoRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/payments`, payload);
  }

  obtenerVentasPendientes(): Observable<any[]> {
    const url = `${this.baseUrl}/sales?paymentStatus=PENDIENTE&paymentStatus=PARCIAL`;
    return this.http.get<any[]>(url);
  }
}
