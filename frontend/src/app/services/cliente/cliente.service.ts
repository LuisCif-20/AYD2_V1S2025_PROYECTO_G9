import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente} from "../../../app/models/models";
import { environment } from '../../../environments/environment';

const baseUrl = environment.IMPORCOMGUA + '/clients';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {


    constructor(private http: HttpClient) { }

    getClientes(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(`${baseUrl}`);
    }

    getCliente(id: string): Observable<Cliente> {
        return this.http.get<Cliente>(`${baseUrl}/${id}`);
    }

    createCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(baseUrl, cliente);
    }

    updateCliente(cliente: Cliente): Observable<Cliente> {
        return this.http.patch<Cliente>(`${baseUrl}/${cliente.id}`, cliente);
    }

    deleteCliente(id: string): Observable<void> {
        return this.http.delete<void>(`${baseUrl}/${id}`);
    }

    updateClienteParcial(id: number, data: any): Observable<any> {
  return this.http.patch(`${baseUrl}/${id}`, data);
}

}
