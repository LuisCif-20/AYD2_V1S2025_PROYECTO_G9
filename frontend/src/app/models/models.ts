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
