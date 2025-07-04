import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {DrawerModule} from 'primeng/drawer';
import {Popover, PopoverModule} from 'primeng/popover';
import {ConfirmPopupModule} from 'primeng/confirmpopup';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule} from '@angular/forms';
import {TooltipModule} from 'primeng/tooltip';
import {TableModule} from 'primeng/table';
import {Product, ProductService} from '../../services/product/product.service';

@Component({
    selector: 'app-overlay-demo',
    standalone: true,
    imports: [ToastModule, DialogModule, ButtonModule, DrawerModule, PopoverModule, ConfirmPopupModule, InputTextModule, FormsModule, TooltipModule, TableModule, ToastModule],
    templateUrl: 'overlaydemo.html',
    providers: [ConfirmationService, MessageService, ProductService]
})
export class OverlayDemo implements OnInit {
    images: any[] = [];

    display: boolean = false;

    products: Product[] = [];

    visibleLeft: boolean = false;

    visibleRight: boolean = false;

    visibleTop: boolean = false;

    visibleBottom: boolean = false;

    visibleFull: boolean = false;

    displayConfirmation: boolean = false;

    selectedProduct!: Product;

    constructor(
        private productService: ProductService,
        private confirmationService: ConfirmationService,
        private messageService: MessageService
    ) {
    }

    ngOnInit() {
        this.productService.getProductsSmall().then((products) => (this.products = products));

        this.images = [];
        this.images.push({
            source: 'assets/demo/images/sopranos/sopranos1.jpg',
            thumbnail: 'assets/demo/images/sopranos/sopranos1_small.jpg',
            title: 'Sopranos 1'
        });
        this.images.push({
            source: 'assets/demo/images/sopranos/sopranos2.jpg',
            thumbnail: 'assets/demo/images/sopranos/sopranos2_small.jpg',
            title: 'Sopranos 2'
        });
        this.images.push({
            source: 'assets/demo/images/sopranos/sopranos3.jpg',
            thumbnail: 'assets/demo/images/sopranos/sopranos3_small.jpg',
            title: 'Sopranos 3'
        });
        this.images.push({
            source: 'assets/demo/images/sopranos/sopranos4.jpg',
            thumbnail: 'assets/demo/images/sopranos/sopranos4_small.jpg',
            title: 'Sopranos 4'
        });
    }

    confirm(event: Event) {
        this.confirmationService.confirm({
            key: 'confirm2',
            target: event.target || new EventTarget(),
            message: 'Are you sure that you want to proceed?',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.messageService.add({severity: 'info', summary: 'Confirmed', detail: 'You have accepted'});
            },
            reject: () => {
                this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'You have rejected'});
            }
        });
    }

    open() {
        this.display = true;
    }

    close() {
        this.display = false;
    }

    toggleDataTable(op: Popover, event: any) {
        op.toggle(event);
    }

    onProductSelect(op: Popover, event: any) {
        op.hide();
        this.messageService.add({severity: 'info', summary: 'Product Selected', detail: event?.data.name, life: 3000});
    }

    openConfirmation() {
        this.displayConfirmation = true;
    }

    closeConfirmation() {
        this.displayConfirmation = false;
    }
}
