import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Cliente {
    id?: string;
    numero?: number;
    codigo?: string;
    nombreContacto?: string;
    nombreNegocio?: string;
    departamento?: string;
    municipio?: string;
    direccion?: string;
    nit?: number;
    encargadoBodega?: string;
    telefono?: string;
    tipoVentaAutorizado?: 'Contado' | 'Crédito' | 'Ambas';
    observaciones?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

   private apiUrl = 'http://localhost:8080/api/clientes'; // Ajusta esta URL según tu backend

    constructor(private http: HttpClient) { }

    getClientes(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.apiUrl);
    }

    getCliente(id: string): Observable<Cliente> {
        return this.http.get<Cliente>(`${this.apiUrl}/${id}`);
    }

    createCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(this.apiUrl, cliente);
    }

    updateCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.put<Cliente>(`${this.apiUrl}/${cliente.id}`, cliente);
    }

    deleteCliente(id: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
