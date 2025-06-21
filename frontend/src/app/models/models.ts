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


export interface Sale {
    id: string
    saleDate: string
    warehouseExitDate: string
    client: {
        id: number
        code: string
        contactName: string
        businessName: string
        municipality: {
            code: string
            name: string
            department: {
                code: string
                name: string
            }
        }
        address: string
        nit: string
        warehouseManager: string
        phone: string
        saleType: string
        notes: string
        active: boolean
    }
    shipmentNumber: string
    paymentType: string
    creditDays: number
    salesman: {
        code: string
        firstName: string
        lastName: string
        phone: string
        address: string
        commissionPercent: number
    }
    dteInvoiceNumber: number
    invoiceName: string
    invoiceNit: string
    total: number
    paymentStatus: string
    saleStatus: string
    paymentDate: string
    notes: string
    details: SaleDetail[]
}

export interface SaleDetail {
    productName: string
    quantity: number
    unitQuantity: number
    pricePerPresentation: number
    subtotal: number
}

export interface SaleForm {
    saleDate: string
    clientId: number | null
    paymentType: string
    creditDays: number
    salesmanCode: string
    dteInvoiceNumber: number
    invoiceName: string
    invoiceNit: string
    notes: string
    details: SaleDetailForm[]
}

export interface SaleDetailForm {
    productCode: string
    quantity: number
    unitQuantity: number
    pricePerPresentation: number
}

export interface Municipio {
    code: string;
    name: string;
}

export interface Client {
    id: number;
    code: string;
    contactName: string;
    businessName: string;
    municipality: Municipio;
    address: string;
    nit: string;
    warehouseManager: string;
    phone: string;
    saleType: 'MAYORISTA' | 'MINORISTA' | 'AMBAS';
    notes: string;
}

export interface Salesman {
    code: string
    firstName: string
    lastName: string
    fullName: string
    phone: string
    address: string
    commissionPercent: number
}

export interface ItemProduct {
    code: string
    name: string
    presentation: {
        id: number
        name: string
    }
    unitsPerPresentation: number
    pricePerPresentation: number
}
