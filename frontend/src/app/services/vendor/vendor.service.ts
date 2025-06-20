import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Vendor } from '../../models/models';

const baseUrl = environment.IMPORCOMGUA + '/vendor';

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
            return this.http.put<Vendor>(`${baseUrl}/update/${vendor.code}`, vendor);
        }
        return this.http.post<any>(`${baseUrl}/create`, vendor);
    }

    deleteSoftVendor(code: string): Observable<boolean> {
        return this.http.delete<boolean>(`${baseUrl}/delete/${code}`);
    }

}