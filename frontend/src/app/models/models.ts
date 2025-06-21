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

export interface Cliente {
  id?: number;
  code?: string;
  contactName?: string;
  businessName?: string;
  municipalityCode?: string;
    municipality?: {
    code?:number,
    name?: string
  };
  address?: string;
  nit?: string;
  warehouseManager?: string;
  phone?: string;
  saleType?: "CREDITO" | "CONTADO" | "AMBAS";
  notes?: string;
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

export interface StockProducto {
  id: number;
  product: {
    code: string;
    name: string;
    presentation: {
      id: number;
      name: string;
    };
    unitsPerPresentation: number;
    pricePerPresentation: number;
    isActive: boolean;
  };
  lastUpdated: string;
  totalQuantity: number;
  availableQuantity: number;
  reservedQuantity: number;
}

export interface Column {
  field: string;
  header: string;
}

export interface Venta {
  id: string;
  shipmentNumber: string;
  client: {
    contactName: string;
  };
  remainingBalance: number;
}

export interface Pago {
  saleId: string;
  bankId: string;
  accountNumber: string;
  transactionNumber: string;
  amount: number;
}

export interface Banco {
  id: string;
  name: string;
}

export interface PagoRequest {
  saleId: string;
  bankId: string;
  accountNumber: string;
  transactionNumber: string;
  amount: number;
}