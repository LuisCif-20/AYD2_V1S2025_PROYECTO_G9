import { Component, OnInit, signal } from '@angular/core';
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
import { CommonModule } from '@angular/common';
import { Column, SalesDataDto, SalesSearch } from '../../../models/models';
import { MessageService } from 'primeng/api';
import { SalesOutletDetailComponent } from "../sales-outlet-detail/sales-outlet-detail.component";
import {SalesService} from "../../../services/sales/sales.service";
import {UtilsService} from "../../../services/utils/utils.service";

@Component({
  selector: 'app-sales-outlet',
  standalone: true,
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
    SalesOutletDetailComponent
],
  providers: [SalesService],
  templateUrl: './sales-outlet.component.html'
})
export class SalesOutletComponent implements OnInit {

  salesSearch!: SalesSearch;
  salesDataList = signal<SalesDataDto[]>([]);
  cols!: Column[];
  searchPerformed: boolean = false;


  constructor(
    private utilsService: UtilsService,
    private salesService: SalesService,
  ) { }


  ngOnInit(): void {
    console.log('ff')
    this.searchPerformed = false;
    this.salesSearch = new SalesSearch();
  }

  searchForSales(): void {
    this.searchPerformed = false;
    const { clientName, shipmentNumber } = this.salesSearch;

    if ((clientName?.trim() ?? '') || (shipmentNumber?.trim() ?? '')) {
      let paramsCreated = new Map<string, any>();
      paramsCreated.set("clientName", this.salesSearch.clientName);
      paramsCreated.set("shipmentNumber", this.salesSearch.shipmentNumber);
      paramsCreated.set("status", "VIGENTE");

      this.salesService.findSalesByQuery(paramsCreated).subscribe({
        next: (data) => {
          this.searchPerformed = true;
          if (data) {
            this.utilsService.success('Consulta exitosa');
            this.salesDataList.set(data);
          }
        },
        error: (err) => {
          console.error('Error al cargar ventas:', err);
          const detalle = err?.error?.detail || 'Error al obtener ventas.';
          this.utilsService.error(detalle);
        }
      });
    } else {
      this.utilsService.error('Campos incompletos, debes escribir el nombre del cliente ó el codigo de envio para poder realizar la búsqueda');
    }
  }

}
