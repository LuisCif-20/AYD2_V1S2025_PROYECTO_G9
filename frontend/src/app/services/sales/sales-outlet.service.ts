import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { SalesDataDto, Vendor } from '../../models/models';

const baseUrl = environment.IMPORCOMGUA + '/sales';

@Injectable({
    providedIn: 'root'
})
export class SalesOutletService {

    constructor(
        private http: HttpClient
    ) { }

    findSalesByQuery(params: Map<string, any>): Observable<SalesDataDto[]> {
        let httpParams = new HttpParams();
        params.forEach((value, key, map) => {
            if (value && key) httpParams = httpParams.set(key, value);
        });
        return this.http.get<SalesDataDto[]>(`${baseUrl}`, { params: httpParams });
    }

}