export interface Country {
    name?: string;
    code?: string;
}

export interface Representative {
    name?: string;
    image?: string;
}

export interface Customer {
    id?: number;
    name?: string;
    country?: Country;
    company?: string;
    date?: string;
    status?: string;
    activity?: number;
    representative?: Representative;
}

export interface LayoutConfig {
    preset?: string;
    primary?: string;
    surface?: string | undefined | null;
    darkTheme?: boolean;
    menuMode?: string;
}

export interface LayoutState {
    staticMenuDesktopInactive?: boolean;
    overlayMenuActive?: boolean;
    configSidebarVisible?: boolean;
    staticMenuMobileActive?: boolean;
    menuHoverActive?: boolean;
}

export interface MenuChangeEvent {
    key: string;
    routeEvent?: boolean;
}

export interface InventoryStatus {
    label: string;
    value: string;
}

export interface Product {
    id?: string;
    code?: string;
    name?: string;
    description?: string;
    price?: number;
    quantity?: number;
    inventoryStatus?: string;
    category?: string;
    image?: string;
    rating?: number;
}

export interface Departamento {
    code: string;
    name: string;
}

export interface Municipio {
    code: string;
    name: string;
    department_code: string;
}

export class Vendor {
    vendorId!: number;
    code!: string;
    firstName!: string;
    lastName!: string;
    phone!: string;
    commissionPercent!: number;
    address!: string;
}


export interface Producto {
    code: string;
    name: string;
    presentation: {
        id: number;
        name: string;
    };
    unitsPerPresentation?: number;
    pricePerPresentation: number;
}


export interface ProductEntryForm {
    productCode?: string;
    entryDate?: string;
    unitsPerPresentation?: number;
    quantityPresentation?: number;
    containerNumber?: string;
    ducaNumber?: string;
    ducaDate?: string;
    rectifiedDucaNumber?: string;
    rectifiedDucaDate?: string;
    notes?: string;
}

export class SalesSearch {
    clientName!: string;
    shipmentNumber!: string;
    status!: string;
}

export interface SalesDataDto {
  id: string
  saleDate: string
  warehouseExitDate: string
  client: Client
  shipmentNumber: string
  paymentType: string
  creditDays: number
  salesman: Salesman
  dteInvoiceNumber: number
  invoiceName: string
  invoiceNit: string
  total: number
  paymentStatus: string
  saleStatus: string
  paymentDate: string
  notes: string
  details: Detail[]
}

export interface Client {
  id: number
  code: string
  contactName: string
  businessName: string
  municipality: Municipality
  address: string
  nit: string
  warehouseManager: string
  phone: string
  saleType: string
  notes: string
  isActive: boolean
}

export interface Municipality {
  code: string
  name: string
  department: Department
}

export interface Department {
  code: string
  name: string
}

export interface Salesman {
  code: string
  firstName: string
  lastName: string
  phone: string
  address: string
  commissionPercent: number
  isActive: boolean
}

export interface Detail {
  productName: string
  quantity: number
  unitQuantity: number
  pricePerPresentation: number
  subtotal: number
}


export interface Column {
    field: string;
    header: string;
    customExportHeader?: string;
}
