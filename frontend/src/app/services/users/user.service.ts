import { Injectable } from "@angular/core"
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"
import { Rol, Sale, User} from "../../models/models";


@Injectable({
    providedIn: "root",
})
export class UserService {
    private apiUrl = "http://localhost:3000/api/v1.0"

    constructor(private http: HttpClient) {}

    getUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.apiUrl}/user-accounts`)
    }

    createUser(userForm: User): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/user-accounts`, userForm)
    }

    updateUser(userForm: User, id: string): Observable<any> {
        return this.http.patch<any>(`${this.apiUrl}/user-accounts/${id}`, userForm)
    }

    deleteUser(id: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/user-accounts/${id}`)
    }

    getRoles(): Observable<Rol[]> {
        return this.http.get<Rol[]>(`${this.apiUrl}/roles`)
    }
}

