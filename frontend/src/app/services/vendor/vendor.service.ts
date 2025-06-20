import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Vendor } from '../../models/models';

const baseUrl = environment.IMPORCOMGUA + '/salesmans';

@Injectable({
    providedIn: 'root'
})
export class VendorService {

    constructor(
        private http: HttpClient
    ) { }

    getAll(): Observable<Vendor[]> {
        return this.http.get<Vendor[]>(`${baseUrl}`)
    }

    save(vendor: Vendor): Observable<Vendor> {
        if (vendor.code) {
            return this.http.patch<Vendor>(`${baseUrl}/${vendor.code}`, vendor);
        }
        return this.http.post<any>(`${baseUrl}`, vendor);
    }

    deleteSoftVendor(code: string): Observable<boolean> {
        return this.http.delete<boolean>(`${baseUrl}/${code}`).pipe(
            map(() => true),
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

}