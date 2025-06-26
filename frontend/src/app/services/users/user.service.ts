import { Injectable } from "@angular/core"
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"
import {Client, ItemProduct, Rol, Sale, SaleForm, Salesman, User, UserForm} from "../../models/models";
import {MessageService} from "primeng/api";

@Injectable({
    providedIn: "root",
})
export class UserService {
    private apiUrl = "http://localhost:3000/api/v1.0"

    constructor(private http: HttpClient) {}

    getUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.apiUrl}/users`)
    }

    createUser(userForm: UserForm): Observable<Sale> {
        return this.http.post<Sale>(`${this.apiUrl}/users`, userForm)
    }

    updateUser(userForm: UserForm): Observable<Sale> {
        return this.http.patch<Sale>(`${this.apiUrl}/users/${userForm.id}`, userForm)
    }

    deleteUser(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/users/${id}`)
    }

    getRoles(): Observable<Rol[]> {
        return this.http.get<Rol[]>(`${this.apiUrl}/roles`)
    }

}
