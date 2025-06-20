import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { catchError, map, Observable, switchMap, throwError } from 'rxjs';
import { Presentation, Product, ProductDTO } from '../../models/product.interface';

@Injectable()
export class ProductService {

    private readonly BASE_URL: string = `${environment.IMPORCOMGUA}/products`;
    private readonly BASE_URL_P: string = `${environment.IMPORCOMGUA}/presentations`;

    private httpClient = inject(HttpClient);

    getProduct(code: string): Observable<Product> {
        return this.httpClient.get<Product>(`${this.BASE_URL}/${code}`).pipe(
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

    getAllPresentations(): Observable<Presentation[]> {
        return this.httpClient.get<Presentation[]>(this.BASE_URL_P).pipe(
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

    getAllProducts(): Observable<Product[]> {
        return this.httpClient.get<Product[]>(this.BASE_URL).pipe(
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

    saveProduct(product: ProductDTO): Observable<void> {
        return this.getProduct(product.code).pipe(
            switchMap(() => {
                // Si el producto existe, actualizar
                return this.httpClient.put<void>(`${this.BASE_URL}/${product.code}`, product);
            }),
            catchError((error: HttpErrorResponse) => {
                if (error.status === 404) {
                    // Si no existe, crear
                    return this.httpClient.post<void>(`${this.BASE_URL}`, product);
                }
                // Otros errores se propagan
                return throwError(() => error);
            })
        );
    }


    deleteProduct(code: string): Observable<void> {
        return this.httpClient.delete<void>(`${this.BASE_URL}/${code}`).pipe(
            catchError((error: HttpErrorResponse) => throwError(() => error))
        );
    }

}
