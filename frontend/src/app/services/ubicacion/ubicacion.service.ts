import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departamento, Municipio } from '../../models/models';
import {environment} from "../../../environments/environment";


@Injectable({
    providedIn: 'root'
})
export class UbicacionService {
    private readonly baseUrl = environment.IMPORCOMGUA;

    constructor(private http: HttpClient) {}

    getDepartamentos(): Observable<Departamento[]> {
        return this.http.get<Departamento[]>(`${this.baseUrl}/departments`);
    }

    getMunicipiosByDepartamento(departmentCode: string): Observable<Municipio[]> {
        return this.http.get<Municipio[]>(`${this.baseUrl}/municipalities?department=${departmentCode}`);
    }
}
