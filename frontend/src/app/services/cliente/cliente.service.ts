import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente} from "../../../app/models/models";


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

   private apiUrl = 'http://localhost:3000/api/v1.0/clients'; // Ajusta esta URL según tu backend

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
        return this.http.patch<Cliente>(`${this.apiUrl}/${cliente.id}`, cliente);
    }

    deleteCliente(id: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
