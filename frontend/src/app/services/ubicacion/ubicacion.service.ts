import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departamento, Municipio } from '../../models/models';


@Injectable({
    providedIn: 'root'
})
export class UbicacionService {
    private baseUrl = 'http://localhost:3000/api/v1.0';

    constructor(private http: HttpClient) {}

    getDepartamentos(): Observable<Departamento[]> {
        return this.http.get<Departamento[]>(`${this.baseUrl}/departments`);
    }

    getMunicipiosByDepartamento(departmentCode: string): Observable<Municipio[]> {
        return this.http.get<Municipio[]>(`${this.baseUrl}/municipalities?department=${departmentCode}`);
    }
}
