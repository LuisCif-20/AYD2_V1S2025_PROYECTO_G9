INSERT INTO product (code, name, presentation_id, units_per_presentation, price_per_presentation, is_active) VALUES
('PROD1001', 'Laptop Gamer Elite', 1, 1, 1299.99, true),
('PROD1002', 'Teclado Mecánico RGB', 1, 1, 89.99, true),
('PROD1003', 'Paquete de 12 Refrescos', 2, 12, 15.50, true),
('PROD1004', 'Mouse Inalámbrico', 1, 1, 24.99, true),
('PROD1005', 'Caja de 24 Cervezas', 2, 24, 29.99, true),
('PROD1006', 'Monitor 24" Full HD', 1, 1, 199.99, true),
('PROD1007', 'Fardo de Toallas', 3, 50, 120.00, true),
('PROD1008', 'Auriculares Bluetooth', 1, 1, 59.99, true),
('PROD1009', 'Paquete de 6 Jabones', 2, 6, 12.75, true),
('PROD1010', 'Impresora Multifunción', 1, 1, 149.99, true);

-- Insertar registros de inventario basados en unidades de presentación
INSERT INTO inventory (
    product_code,
    last_updated,
    total_quantity,
    available_quantity,
    reserved_quantity
) VALUES
-- Productos unitarios (presentation_id = 1)
('PROD1001', CURRENT_DATE, 50, 50, 0),   -- Laptop Gamer Elite (unidades)
('PROD1002', CURRENT_DATE, 100, 100, 0),  -- Teclado Mecánico RGB (unidades)
('PROD1004', CURRENT_DATE, 200, 200, 0),  -- Mouse Inalámbrico (unidades)
('PROD1006', CURRENT_DATE, 30, 30, 0),    -- Monitor 24" Full HD (unidades)
('PROD1008', CURRENT_DATE, 75, 75, 0),    -- Auriculares Bluetooth (unidades)
('PROD1010', CURRENT_DATE, 40, 40, 0),    -- Impresora Multifunción (unidades)

-- Productos empaquetados (presentation_id = 2 o 3)
('PROD1003', CURRENT_DATE, 20, 20, 0),    -- 20 paquetes de 12 refrescos
('PROD1005', CURRENT_DATE, 15, 15, 0),    -- 15 cajas de 24 cervezas
('PROD1007', CURRENT_DATE, 8, 8, 0),      -- 8 fardos de 50 toallas
('PROD1009', CURRENT_DATE, 25, 25, 0);    -- 25 paquetes de 6 jabones