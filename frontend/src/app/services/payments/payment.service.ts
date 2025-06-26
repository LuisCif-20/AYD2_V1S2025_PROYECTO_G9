import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Banco, Pago} from '../../models/models';
import { environment } from '../../../environments/environment';

const baseUrl = environment.IMPORCOMGUA;

@Injectable({
  providedIn: 'root',
})
export class PaymentService {


  constructor(private http: HttpClient) {}

  obtenerBancos(): Observable<Banco[]> {
    return this.http.get<Banco[]>(`${baseUrl}/banks`);
  }

  registrarPago(payload: Pago): Observable<void> {
    return this.http.post<void>(`${baseUrl}/payments`, payload);
  }

  obtenerVentasPendientes(): Observable<any[]> {
    const url = `${baseUrl}/sales?paymentStatus=PENDIENTE&paymentStatus=PARCIAL`;
    return this.http.get<any[]>(url);
  }
}
