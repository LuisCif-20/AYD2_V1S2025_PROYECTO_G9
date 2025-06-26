import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {MessageService} from "primeng/api";

const baseUrl = environment.IMPORCOMGUA + '/salesmen';

@Injectable({
    providedIn: 'root'
})
export class UtilsService {

    constructor(private messageService: MessageService,) {
    }

    error(message: string) {
        this.messageService.add({severity: 'error', summary: 'Error', detail: message, life: 5000});

    }

    success(message: string) {
        this.messageService.add({severity: 'success', summary: 'Success', detail: message, life: 5000});

    }
}
