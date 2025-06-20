CREATE TABLE department (
    code CHAR(2) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE municipality (
    code CHAR(4) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_code CHAR(2) NOT NULL,
    FOREIGN KEY (department_code) REFERENCES department(code)
);

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    contact_name VARCHAR(100) NOT NULL,
    business_name VARCHAR(100),
    municipality_code CHAR(4) NOT NULL,
    address VARCHAR(255),
    nit CHAR(9),
    warehouse_manager VARCHAR(100),
    phone CHAR(9),
    sale_type VARCHAR(7) NOT NULL CHECK (sale_type IN ('CREDITO', 'CONTADO', 'AMBAS')),
    notes TEXT,
    FOREIGN KEY (municipality_code) REFERENCES municipality(code)
);

CREATE TABLE salesman (
    code UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone CHAR(9),
    address VARCHAR(255),
    commission_percent DECIMAL(3,2) NOT NULL
);

CREATE TABLE presentation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE product (
    code CHAR(8) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    presentation_id INT NOT NULL,
    units_per_presentation INT NOT NULL,
    price_per_presentation DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (presentation_id) REFERENCES presentation(id)
);

CREATE TABLE bank (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE sale (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sale_date DATE NOT NULL DEFAULT CURRENT_DATE,
    warehouse_exit_date DATE,
    client_id INT NOT NULL,
    shipment_number UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
    payment_type VARCHAR(7) NOT NULL CHECK (payment_type IN ('CONTADO', 'CREDITO')),
    credit_days INT NOT NULL CHECK (credit_days >= 0),
    salesman_code UUID NOT NULL,
    dte_invoice_number INT,
    invoice_name VARCHAR(100),
    invoice_nit VARCHAR(9),
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    
    payment_status VARCHAR(10) NOT NULL DEFAULT 'PENDIENTE'
        CHECK (payment_status IN ('PAGADO', 'PARCIAL', 'PENDIENTE')),
    
    sale_status VARCHAR(10) NOT NULL DEFAULT 'VIGENTE'
        CHECK (sale_status IN ('VIGENTE', 'ANULADA')),
    payment_date DATE,
    notes TEXT,

    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (salesman_code) REFERENCES salesman(code)
);

CREATE TABLE sale_detail (
    id SERIAL PRIMARY KEY,
    sale_id UUID NOT NULL,
    product_code CHAR(8) NOT NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK (quantity > 0),
    unit_quantity INT NOT NULL CHECK (unit_quantity >= 0),
    price_per_presentation DECIMAL(10,2) NOT NULL CHECK (price_per_presentation >= 0),
    subtotal DECIMAL(10,2) NOT NULL CHECK (subtotal >= 0),

    FOREIGN KEY (sale_id) REFERENCES sale(id) ON DELETE CASCADE,
    FOREIGN KEY (product_code) REFERENCES product(code)
);

CREATE TABLE inventory (
    product_code CHAR(8) PRIMARY KEY,
    last_updated DATE NOT NULL DEFAULT CURRENT_DATE,
    total_quantity INT NOT NULL CHECK (total_quantity >= 0),
    available_quantity INT NOT NULL CHECK (available_quantity >= 0),
    reserved_quantity INT NOT NULL CHECK (reserved_quantity >= 0),

    FOREIGN KEY (product_code) REFERENCES product(code)
);

CREATE TABLE payment (
    receipt_number UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_date DATE NOT NULL DEFAULT CURRENT_DATE,
    sale_id UUID NOT NULL,
    bank_id UUID NOT NULL,
    account_number VARCHAR(15) NOT NULL,
    transaction_number VARCHAR(15) NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),

    FOREIGN KEY (bank_id) REFERENCES bank(id),
    FOREIGN KEY (sale_id) REFERENCES sale(id)
);

CREATE TABLE product_warehouse_entry (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    entry_date DATE NOT NULL DEFAULT CURRENT_DATE,
    product_code CHAR(8) NOT NULL,
    quantity_presentation INT NOT NULL CHECK (quantity_presentation > 0),
    units_per_presentation INT NOT NULL CHECK (units_per_presentation > 0),
    container_number VARCHAR(10) NOT NULL,
    duca_number VARCHAR(10) NOT NULL,
    duca_date DATE NOT NULL,
    rectified_duca_number VARCHAR(10),
    rectified_duca_date DATE,
    notes TEXT,

    FOREIGN KEY (product_code) REFERENCES product(code)
);
