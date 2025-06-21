INSERT INTO client (
    code, 
    contact_name, 
    business_name, 
    municipality_code, 
    address, 
    nit, 
    warehouse_manager, 
    phone, 
    sale_type, 
    notes, 
    is_active
) VALUES
-- Cliente 1: Tienda en Guatemala (CONTADO)
('GT1', 'Juan Pérez', 'Tienda El Ahorro', '0101', 
 '6a Avenida 8-25 Zona 1', '123456789', 'María González', '1234-5678', 
 'CONTADO', 'Cliente frecuente', true),

-- Cliente 2: Distribuidora en Antigua (CREDITO)
('SC2', 'Carlos López', 'Distribuidora Colonial', '0301', 
 'Calle del Arco 15', '987654321', 'Pedro Hernández', '9876-5432', 
 'CREDITO', '30 días de crédito', true),

-- Cliente 3: Mayorista en Quetzaltenango (AMBAS)
('QZ3', 'Ana Martínez', 'Mayorista Occidente', '0901', 
 '12 Avenida 5-30 Zona 3', '456789123', 'Luisa Ramírez', '4567-8912', 
 'AMBAS', 'Descuento por volumen', true),

-- Cliente 4: Pequeño negocio en San Mateo (CONTADO)
('QZ4', 'Mario Sánchez', 'Mini Super San Mateo', '0910', 
 'Calle Central s/n', '789123456', 'Sofía Morales', '7891-2345', 
 'CONTADO', 'Pago en efectivo', true),

-- Cliente 5: Productor en Huehuetenango (CREDITO)
('HU5', 'Teresa Jiménez', 'Productos del Campo', '1301', 
 '3a Calle 4-56 Zona 2', '321654987', 'José Ortiz', '3216-5498', 
 'CREDITO', 'Entrega quincenal', true);