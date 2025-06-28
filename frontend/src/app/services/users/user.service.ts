import { Injectable } from "@angular/core"
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"
import { Rol, Sale, User} from "../../models/models";
import {environment} from "../../../environments/environment";


@Injectable({
    providedIn: "root",
})
export class UserService {
    private readonly BASE_URL = `${environment.IMPORCOMGUA}`;

    constructor(private http: HttpClient) {}

    getUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.BASE_URL}/user-accounts`)
    }

    createUser(userForm: User): Observable<any> {
        return this.http.post<any>(`${this.BASE_URL}/user-accounts`, userForm)
    }

    updateUser(userForm: User, id: string): Observable<any> {
        return this.http.patch<any>(`${this.BASE_URL}/user-accounts/${id}`, userForm)
    }

    deleteUser(isActive: boolean, id: string): Observable<void> {
        return this.http.patch<any>(`${this.BASE_URL}/user-accounts/${id}`, { isActive })
    }

    getRoles(): Observable<Rol[]> {
        return this.http.get<Rol[]>(`${this.BASE_URL}/roles`)
    }
}

