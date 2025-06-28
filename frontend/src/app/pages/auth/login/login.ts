import {Component, signal} from '@angular/core';
import {
    AbstractControl,
    FormGroup,
    FormsModule,
    NonNullableFormBuilder,
    ReactiveFormsModule,
    Validators
} from '@angular/forms';
import {Router, RouterModule} from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { RippleModule } from 'primeng/ripple';
import {AuthService} from "../../../services/auth/auth.service";
import {UtilsService} from "../../../services/utils/utils.service";
import {Toast, ToastModule} from "primeng/toast";
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [ButtonModule, CheckboxModule, InputTextModule, PasswordModule, FormsModule, RouterModule, RippleModule, ToastModule, NgIf, ReactiveFormsModule],
    templateUrl: 'login.html',
})
export class Login {

    email: string = '';
    password: string = '';
    checked: boolean = false;

    isLoading = signal<boolean>(false);

    loginForm!: FormGroup;

    constructor(private router: Router,
                private authService: AuthService,
                private utilsService: UtilsService,
                private formBuilder: NonNullableFormBuilder) {}

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    get emailControl(): AbstractControl {
        return this.loginForm.get('email')!;
    }

    get passwordControl(): AbstractControl {
        return this.loginForm.get('password')!;
    }

    async login() {
        if (this.loginForm.invalid) {
            this.loginForm.markAllAsTouched();
            this.utilsService.error('Formulario invalido, porfavor llenalo correctamente.');
            return;
        }

        this.isLoading.set(true);
        this.authService.login(this.loginForm.getRawValue()).subscribe({
            next: () => {
                this.utilsService.success('Bienvenido al sistema');
                setTimeout(async () => {
                    await this.router.navigateByUrl('/');
                }, 500)
            },
            error: () => this.utilsService.error('Credenciales Incorrectas.')
        });
        this.isLoading.set(false);
    }
}
