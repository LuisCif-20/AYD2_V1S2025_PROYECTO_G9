import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departamento, Municipio } from '../../models/models';


@Injectable({
    providedIn: 'root'
})
export class UbicacionService {
    private baseUrl = 'http://localhost:8080/api';

    constructor(private http: HttpClient) {}

    getDepartamentos(): Observable<Departamento[]> {
        return this.http.get<Departamento[]>(`${this.baseUrl}/departamentos`);
    }

    getMunicipiosByDepartamento(departmentCode: string): Observable<Municipio[]> {
        return this.http.get<Municipio[]>(`${this.baseUrl}/municipios/departamento/${departmentCode}`);
    }
}
