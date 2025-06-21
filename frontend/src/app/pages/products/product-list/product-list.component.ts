import { CommonModule } from '@angular/common';
import { Component, OnInit, signal, ViewChild } from '@angular/core';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { SelectModule } from 'primeng/select';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { DialogModule } from 'primeng/dialog';
import { TagModule } from 'primeng/tag';
import { InputIconModule } from 'primeng/inputicon';
import { IconFieldModule } from 'primeng/iconfield';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product } from '../../../models/product.interface';
import { ProductService } from '../../../services/products/product.service';
import { ProductFormComponent } from "../product-form/product-form.component";


interface Column {
  field: string;
  header: string;
  customExportHeader?: string;
}

@Component({
  selector: 'app-product-list',
  providers: [MessageService, ProductService, ConfirmationService],
  imports: [
    CommonModule,
    TableModule,
    FormsModule,
    ButtonModule,
    RippleModule,
    ToastModule,
    ToolbarModule,
    RatingModule,
    InputTextModule,
    TextareaModule,
    SelectModule,
    RadioButtonModule,
    InputNumberModule,
    DialogModule,
    TagModule,
    InputIconModule,
    IconFieldModule,
    ConfirmDialogModule,
    ProductFormComponent
  ],
  templateUrl: './product-list.component.html',
  styles: ``
})
export class ProductListComponent implements OnInit {

  product!: Product;
  products = signal<Product[]>([]);
  selectedProduct!: Product[] | null;
  submitted: boolean = false;

  @ViewChild('dt') dt!: Table;
  cols!: Column[];

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private productService: ProductService,
  ) {
  }

  ngOnInit() {
    this.loadIniData();
  }

  loadIniData() {

    this.productService.getAllProducts()
      .subscribe(
        {
          next: (data) => {
            this.products.set(data);
          },
          error: (err) => {
            console.error('Error al cargar vendedor:', err);
          },
          complete: () => {
            console.log('Carga de proveedores completada');
          }

        });

    this.cols = [
      { field: 'code', header: 'Codigo', customExportHeader: 'Code' },
      { field: 'name', header: 'Nombre' },
      { field: 'presentation.name', header: 'Presentacion' },
      { field: 'unitsPerPresentation', header: 'Unidades por Presentacion' },
      { field: 'pricePerPresentation', header: 'Precio por Presentacion' }
    ];
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  openNew() {
    this.submitted = false;
  }

  hideDialog() {
    this.submitted = false;
  }

  deleteProduct(product: Product) {
    this.confirmationService.confirm({
      message: 'Estas seguro en eliminar el producto ' + product.name + '?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.productService.deleteProduct(product.code!)
          .subscribe(
            {
              next: () => {
                this.messageService.add({
                  severity: 'success',
                  summary: 'Éxito',
                  detail: 'Producto eliminado',
                  life: 3000
                });
                this.productService.getAllProducts()
                  .subscribe(
                    {
                      next: (data) => {
                        this.products.set(data);
                      },
                      error: (err) => {
                        console.error('Error al cargar vendedor:', err);
                      },
                      complete: () => {
                        console.log('Carga de proveedores completada');
                      }

                    });
              },
              error: (err) => {
                this.messageService.add({
                  severity: 'error',
                  summary: 'Error',
                  detail: 'Error al intentar eliminar un producto, por favor, intente mas tarde',
                });
              },
              complete: () => {
                console.log('Carga de proveedores completada');
              }

            });

      }
    });
  }

  findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.products().length; i++) {
      if (this.products()[i].code === id) {
        index = i;
        break;
      }
    }

    return index;
  }

}
