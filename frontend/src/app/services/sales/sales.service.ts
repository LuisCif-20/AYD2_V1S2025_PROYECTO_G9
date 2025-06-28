import { Injectable } from "@angular/core"
import {HttpClient, HttpParams} from "@angular/common/http"
import { Observable } from "rxjs"
import {Client, ItemProduct, Sale, SaleForm, SalesDataDto, Salesman, SalesOutlet} from "../../models/models";
import {MessageService} from "primeng/api";
import {environment} from "../../../environments/environment";

@Injectable({
    providedIn: "root",
})
export class SalesService {

    private readonly BASE_URL = `${environment.IMPORCOMGUA}`;

    constructor(private http: HttpClient) {}

    getSales(): Observable<Sale[]> {
        return this.http.get<Sale[]>(`${this.BASE_URL}/sales`)
    }

    createSale(saleForm: SaleForm): Observable<Sale> {
        if(!saleForm.invoiceNit)  saleForm['invoiceNit'] = undefined;
        if(!saleForm.dteInvoiceNumber)  saleForm['dteInvoiceNumber']  = undefined;
        if(!saleForm.invoiceName)  saleForm['invoiceName']  = undefined;
        if(!saleForm.notes)  saleForm['notes']  = undefined;
        return this.http.post<Sale>(`${this.BASE_URL}/sales`, saleForm)
    }

    deleteSale(id: string): Observable<void> {
        return this.http.delete<void>(`${this.BASE_URL}/sales/${id}`)
    }

    getClients(): Observable<Client[]> {
        return this.http.get<Client[]>(`${this.BASE_URL}/clients?active=true`)
    }

    getSalesmen(): Observable<Salesman[]> {
        return this.http.get<Salesman[]>(`${this.BASE_URL}/salesmen?active=true`)
    }

    getProducts(): Observable<ItemProduct[]> {
        return this.http.get<ItemProduct[]>(`${this.BASE_URL}/products?isActive=true`)
    }


    printSale(sale: Sale) {
        if (!sale) {
            return {
                status: "error",
                message: "No se puede imprimir: venta no válida"
            };
        }

        // Crear el contenido HTML para imprimir
        const printContent = this.generatePrintContent(sale)

        // Abrir nueva ventana para imprimir
        const printWindow = window.open("", "_blank", "width=800,height=600")

        if (printWindow) {
            printWindow.document.write(printContent)
            printWindow.document.close()

            // Esperar a que se cargue el contenido y luego imprimir
            printWindow.onload = () => {
                setTimeout(() => {
                    printWindow.print()
                    // Opcional: cerrar la ventana después de imprimir
                    // printWindow.close()
                }, 250)
            }

            return {
                status: "success",
                message: "Documento de venta preparado para imprimir"
            };
        } else {
            return {
                status: "error",
                message: "No se pudo abrir la ventana de impresión. Verifique que no esté bloqueada por el navegador."
            };
        }
    }

    // Generar el contenido HTML para imprimir
    private generatePrintContent(sale: Sale): string {
        const currentDate = new Date().toLocaleDateString("es-GT")
        const currentTime = new Date().toLocaleTimeString("es-GT")

        return `
      <!DOCTYPE html>
      <html lang="es">
      <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Venta ${sale.id}</title>
          <style>
              * {
                  margin: 0;
                  padding: 0;
                  box-sizing: border-box;
              }
              
              body {
                  font-family: 'Arial', sans-serif;
                  font-size: 12px;
                  line-height: 1.4;
                  color: #333;
                  background: white;
                  padding: 20px;
              }
              
              .invoice-container {
                  max-width: 800px;
                  margin: 0 auto;
                  background: white;
                  border: 2px solid #ddd;
                  border-radius: 8px;
                  overflow: hidden;
              }
              
              .invoice-header {
                  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                  color: white;
                  padding: 20px;
                  text-align: center;
              }
              
              .company-name {
                  font-size: 24px;
                  font-weight: bold;
                  margin-bottom: 5px;
              }
              
              .invoice-title {
                  font-size: 18px;
                  margin-bottom: 10px;
              }
              
              .invoice-info {
                  display: flex;
                  justify-content: space-between;
                  font-size: 14px;
              }
              
              .invoice-body {
                  padding: 20px;
              }
              
              .section {
                  margin-bottom: 20px;
                  border: 1px solid #eee;
                  border-radius: 6px;
                  overflow: hidden;
              }
              
              .section-header {
                  background: #f8f9fa;
                  padding: 10px 15px;
                  font-weight: bold;
                  color: #495057;
                  border-bottom: 1px solid #eee;
              }
              
              .section-content {
                  padding: 15px;
              }
              
              .info-grid {
                  display: grid;
                  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                  gap: 15px;
              }
              
              .info-item {
                  display: flex;
                  flex-direction: column;
              }
              
              .info-label {
                  font-weight: bold;
                  color: #666;
                  font-size: 11px;
                  text-transform: uppercase;
                  margin-bottom: 3px;
              }
              
              .info-value {
                  color: #333;
                  font-size: 13px;
              }
              
              .products-table {
                  width: 100%;
                  border-collapse: collapse;
                  margin-top: 10px;
              }
              
              .products-table th,
              .products-table td {
                  border: 1px solid #ddd;
                  padding: 8px;
                  text-align: left;
              }
              
              .products-table th {
                  background: #f8f9fa;
                  font-weight: bold;
                  color: #495057;
                  font-size: 11px;
                  text-transform: uppercase;
              }
              
              .products-table td {
                  font-size: 12px;
              }
              
              .text-right {
                  text-align: right;
              }
              
              .text-center {
                  text-align: center;
              }
              
              .total-section {
                  background: #f8f9fa;
                  border: 2px solid #dee2e6;
                  border-radius: 6px;
                  padding: 15px;
                  margin-top: 20px;
              }
              
              .total-amount {
                  font-size: 20px;
                  font-weight: bold;
                  color: #28a745;
                  text-align: right;
              }
              
              .status-badge {
                  display: inline-block;
                  padding: 4px 8px;
                  border-radius: 4px;
                  font-size: 10px;
                  font-weight: bold;
                  text-transform: uppercase;
              }
              
              .status-success { background: #d4edda; color: #155724; }
              .status-warning { background: #fff3cd; color: #856404; }
              .status-danger { background: #f8d7da; color: #721c24; }
              .status-info { background: #d1ecf1; color: #0c5460; }
              
              .notes-section {
                  background: #fff3cd;
                  border: 1px solid #ffeaa7;
                  border-radius: 6px;
                  padding: 15px;
                  margin-top: 15px;
              }
              
              .print-info {
                  text-align: center;
                  color: #666;
                  font-size: 10px;
                  margin-top: 20px;
                  padding-top: 15px;
                  border-top: 1px solid #eee;
              }
              
              @media print {
                  body { padding: 0; }
                  .invoice-container { border: none; box-shadow: none; }
                  .print-info { display: none; }
              }
          </style>
      </head>
      <body>
          <div class="invoice-container">
              <div class="invoice-header">
                  <div class="company-name">IMPORCOMGUA</div>
                  <div class="invoice-title">COMPROBANTE DE VENTA</div>
                  <div class="invoice-info">
                      <span>Venta #${sale.id}</span>
                      <span>Fecha: ${this.formatDateForPrint(sale.saleDate)}</span>
                  </div>
              </div>
              
              <div class="invoice-body">
                  <!-- Información del Cliente -->
                  <div class="section">
                      <div class="section-header">📋 INFORMACIÓN DEL CLIENTE</div>
                      <div class="section-content">
                          <div class="info-grid">
                              <div class="info-item">
                                  <div class="info-label">Empresa</div>
                                  <div class="info-value">${sale.client.businessName}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Contacto</div>
                                  <div class="info-value">${sale.client.contactName}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">NIT</div>
                                  <div class="info-value">${sale.client.nit}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Teléfono</div>
                                  <div class="info-value">${sale.client.phone}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Dirección</div>
                                  <div class="info-value">${sale.client.address}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Municipio</div>
                                  <div class="info-value">${sale.client.municipality.name}, ${sale.client.municipality.department.name}</div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <!-- Información del Vendedor -->
                  <div class="section">
                      <div class="section-header">👤 INFORMACIÓN DEL VENDEDOR</div>
                      <div class="section-content">
                          <div class="info-grid">
                              <div class="info-item">
                                  <div class="info-label">Vendedor</div>
                                  <div class="info-value">${sale.salesman.firstName} ${sale.salesman.lastName}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Código</div>
                                  <div class="info-value">${sale.salesman.code}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Teléfono</div>
                                  <div class="info-value">${sale.salesman.phone}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Comisión</div>
                                  <div class="info-value">${(sale.salesman.commissionPercent * 100).toFixed(1)}%</div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <!-- Información de la Venta -->
                  <div class="section">
                      <div class="section-header">💰 INFORMACIÓN DE LA VENTA</div>
                      <div class="section-content">
                          <div class="info-grid">
                              <div class="info-item">
                                  <div class="info-label">Fecha de Venta</div>
                                  <div class="info-value">${this.formatDateForPrint(sale.saleDate)}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Fecha Salida Almacén</div>
                                  <div class="info-value">${this.formatDateForPrint(sale.warehouseExitDate)}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Tipo de Pago</div>
                                  <div class="info-value">
                                      <span class="status-badge ${this.getStatusClass(sale.paymentType, "payment")}">${sale.paymentType}</span>
                                  </div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Estado de Pago</div>
                                  <div class="info-value">
                                      <span class="status-badge ${this.getStatusClass(sale.paymentStatus, "paymentStatus")}">${sale.paymentStatus}</span>
                                  </div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Estado de Venta</div>
                                  <div class="info-value">
                                      <span class="status-badge ${this.getStatusClass(sale.saleStatus, "saleStatus")}">${sale.saleStatus}</span>
                                  </div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Días de Crédito</div>
                                  <div class="info-value">${sale.creditDays} días</div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <!-- Información de Facturación -->
                  <div class="section">
                      <div class="section-header">🧾 INFORMACIÓN DE FACTURACIÓN</div>
                      <div class="section-content">
                          <div class="info-grid">
                              <div class="info-item">
                                  <div class="info-label">Número DTE</div>
                                  <div class="info-value">${sale.dteInvoiceNumber}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Nombre Factura</div>
                                  <div class="info-value">${sale.invoiceName}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">NIT Factura</div>
                                  <div class="info-value">${sale.invoiceNit}</div>
                              </div>
                              <div class="info-item">
                                  <div class="info-label">Número de Envío</div>
                                  <div class="info-value">${sale.shipmentNumber}</div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <!-- Productos -->
                  <div class="section">
                      <div class="section-header">📦 PRODUCTOS VENDIDOS</div>
                      <div class="section-content">
                          <table class="products-table">
                              <thead>
                                  <tr>
                                      <th>Producto</th>
                                      <th class="text-center">Cantidad</th>
                                      <th class="text-center">Unidades</th>
                                      <th class="text-right">Precio Unit.</th>
                                      <th class="text-right">Subtotal</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  ${sale.details
            .map(
                (detail) => `
                                      <tr>
                                          <td><strong>${detail.productName}</strong></td>
                                          <td class="text-center">${detail.quantity}</td>
                                          <td class="text-center">${detail.unitQuantity}</td>
                                          <td class="text-right">$${detail.pricePerPresentation.toFixed(2)}</td>
                                          <td class="text-right"><strong>$${detail.subtotal.toFixed(2)}</strong></td>
                                      </tr>
                                  `,
            )
            .join("")}
                              </tbody>
                          </table>
                          
                          <div class="total-section">
                              <div class="total-amount">
                                  TOTAL: $${sale.total.toFixed(2)}
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  ${
            sale.notes
                ? `
                  <!-- Notas -->
                  <div class="notes-section">
                      <div class="info-label">📝 NOTAS</div>
                      <div class="info-value">${sale.notes}</div>
                  </div>
                  `
                : ""
        }
                  
                  <div class="print-info">
                      Documento generado el ${currentDate} a las ${currentTime}<br>
                      Sistema de Gestión de Ventas
                  </div>
              </div>
          </div>
      </body>
      </html>
    `
    }

    private formatDateForPrint(dateString: string): string {
        if (!dateString) return "N/A"
        const date = new Date(dateString)
        return date.toLocaleDateString("es-GT", {
            year: "numeric",
            month: "long",
            day: "numeric",
        })
    }

    private getStatusClass(status: string, type: string): string {
        switch (type) {
            case "payment":
                return status === "CONTADO" ? "status-success" : "status-warning"
            case "paymentStatus":
                switch (status) {
                    case "PAGADO":
                        return "status-success"
                    case "PENDIENTE":
                        return "status-warning"
                    case "VENCIDO":
                        return "status-danger"
                    default:
                        return "status-info"
                }
            case "saleStatus":
                switch (status) {
                    case "VIGENTE":
                        return "status-success"
                    case "COMPLETADA":
                        return "status-info"
                    case "CANCELADA":
                        return "status-danger"
                    default:
                        return "status-warning"
                }
            default:
                return "status-info"
        }
    }

    findSalesByQuery(params: Map<string, any>): Observable<SalesDataDto[]> {
        let httpParams = new HttpParams();
        params.forEach((value, key, map) => {
            if (value && key) httpParams = httpParams.set(key, value);
        });
        return this.http.get<SalesDataDto[]>(`${this.BASE_URL}/sales`, { params: httpParams });
    }

    registerSalesOutlet(request: SalesOutlet): Observable<boolean> {
        return this.http.put<boolean>(`${this.BASE_URL}/warehouse-outputs`, request);
    }
}
