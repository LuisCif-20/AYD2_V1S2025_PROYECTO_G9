import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { SalesDataDto, SalesOutlet, Vendor } from '../../models/models';

const baseUrl = environment.IMPORCOMGUA + '/warehouse-outputs';

@Injectable({
    providedIn: 'root'
})
export class SalesOutletService {

    constructor(
        private http: HttpClient
    ) { }

    registerSalesOutlet(request: SalesOutlet): Observable<boolean> {
        return this.http.put<boolean>(`${baseUrl}`, request);
    }

}