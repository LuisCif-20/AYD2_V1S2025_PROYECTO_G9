import { Injectable } from "@angular/core"
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"
import {Client, ItemProduct, Sale, SaleForm, Salesman} from "../../pages/sales/sales";

@Injectable({
    providedIn: "root",
})
export class SalesService {
    private apiUrl = "your-api-base-url"

    constructor(private http: HttpClient) {}

    getSales(): Observable<Sale[]> {
        return this.http.get<Sale[]>(`${this.apiUrl}/sales`)
    }

    createSale(saleForm: SaleForm): Observable<Sale> {
        return this.http.post<Sale>(`${this.apiUrl}/sales`, saleForm)
    }

    updateSale(id: string, saleForm: SaleForm): Observable<Sale> {
        return this.http.put<Sale>(`${this.apiUrl}/sales/${id}`, saleForm)
    }

    deleteSale(id: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/sales/${id}`)
    }

    getClients(): Observable<Client[]> {
        return this.http.get<Client[]>(`${this.apiUrl}/clients`)
    }

    getSalesmen(): Observable<Salesman[]> {
        return this.http.get<Salesman[]>(`${this.apiUrl}/salesmen`)
    }

    getProducts(): Observable<ItemProduct[]> {
        return this.http.get<ItemProduct[]>(`${this.apiUrl}/products`)
    }
}
