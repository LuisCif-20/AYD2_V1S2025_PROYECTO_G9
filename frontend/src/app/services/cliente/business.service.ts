import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { catchError, map, Observable, switchMap, throwError } from 'rxjs';
import { Business } from '../../models/business.inteface';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {

  private readonly BASE_URL: string = `${environment.IMPORCOMGUA}/business`;

  private httpClient = inject(HttpClient);

  private _business = signal<Business[]>([]);

  public business = computed(() => this._business());

  constructor() { }

  getAllBusiness(): Observable<void> {
    return this.httpClient.get<Business[]>(this.BASE_URL).pipe(
      map((business) => this._business.set(business)),
      catchError((error: HttpErrorResponse) => throwError(() => error))
    );
  }

  createBusiness(name: string): Observable<void> {
    return this.httpClient.post<Business>(this.BASE_URL, { name }).pipe(
      switchMap(() => this.getAllBusiness()),
      catchError((error: HttpErrorResponse) => throwError(() => error))
    );
  }

  updateBusiness(id: string, data: Business): Observable<void> {
    const url: string = `${this.BASE_URL}/${id}`;
    return this.httpClient.patch<Business>(url, data).pipe(
      switchMap(() => this.getAllBusiness()),
      catchError((error: HttpErrorResponse) => throwError(() => error))
    );
  }

  deleteBusiness(id: string): Observable<void> {
    const url: string = `${this.BASE_URL}/${id}`;
    return this.httpClient.delete<void>(url).pipe(
      switchMap(() => this.getAllBusiness()),
      catchError((error: HttpErrorResponse) => throwError(() => error))
    );
  }

}
