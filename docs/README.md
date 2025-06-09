# Fase 1 - Documentacion Sistema de Automatización IMPORCOMGUA

## 1. Core del Negocio
### Descripcion
IMPORCOMGUA es una empresa guatemalteca dedicada al comercio de importaciones, especializada en la adquisición y distribución de productos provenientes del extranjero para su comercialización en el mercado nacional. Su modelo de negocio se centra en gestionar de forma eficiente el proceso completo de importación, almacenamiento, venta y entrega de mercancías, trabajando directamente con clientes y vendedores en distintas regiones del país.

### Stakeholders
* Gerente general: Recibe reportes clave, toma desiciones estrategicas para el negocio. 
* Ventas: Registran ventas dentro del sistema, consultan comisiones y gestiona clientes.
* Bodega: Registran ingresos y egresos de bodega, gestionan inventario y productos.
* Finanzas: Se encarga de controlar los pagos (completos o abonados) de las ventas realizadas.
* Administracion: Gestion de vendedores, empleados y comisiones.

### Diagrama CDU de alto nivel
![CDU_alto_nivel](./assets/cdu/CDU-ALTO-NIVEL.png)

### Primera descomposición
![CDU_primera_descomposicion](./assets/cdu/CDU-PRIMERA-DESCOMPOSICION.png)

## 2. Casos de uso expandidos
### Diagramas
![CDU_expandido-gestion_clientes](./assets/cdu/CDU-GESTION-CLIENTES.png)

![CDU_expandido-gestion_empleados](./assets/cdu/CDU-GESTION-EMPLEADOS.png)

![CDU_expandido-gestion_ventas](./assets/cdu/CDU-GESTION-VENTAS.png)

![CDU_expandido-gestion_bodega](./assets/cdu/CDU-GESTION-BODEGA.png)

![CDU_expandido-control_pagos](./assets/cdu/CDU-CONTROL-PAGOS.png)

### Descripciones

## 3. Drivers arquitectonicos
### Requerimientos funcionales críticos
* **RF01 Registrar cliente**: El sistema debe permitir registrar un nuevo cliente con sus datos personales.

* **RF02 Editar cliente**: El sistema debe permitir modificar los datos de un cliente existente.

* **RF03 Eliminar cliente**: El sistema debe permitir eliminar un cliente registrado.

* **RF04 Buscar cliente**: El sistema debe permitir buscar clientes por distintos criterios.

* **RF05 Registrar producto**: El sistema debe permitir registrar un nuevo producto en el inventario.

* **RF06 Modificar producto**: El sistema debe permitir modificar los detalles de un producto existente.

* **RF07 Eliminar producto**: El sistema debe permitir eliminar productos del inventario.

* **RF08 Buscar producto**: El sistema debe permitir buscar productos por diferentes criterios.

* **RF09 Registrar venta**: El sistema debe permitir registrar una nueva venta con los datos del cliente y productos vendidos.

* **RF10 Anular venta**: El sistema debe permitir anular ventas realizadas anteriormente.

* **RF11 Buscar venta**: El sistema debe permitir buscar ventas realizadas por diferentes parámetros.

* **RF12 Aplicar descuento**: El sistema debe permitir aplicar descuentos en las ventas según condiciones definidas.

* **RF13 Registrar salida de bodega**: El sistema debe permitir registrar la salida de productos por ventas u otros motivos.

* **RF14 Registrar ingreso a bodega**: El sistema debe permitir registrar el ingreso de productos al inventario.

* **RF15 Modificar inventario**: El sistema debe permitir realizar ajustes manuales al inventario registrado.

* **RF16 Registrar empleado**: El sistema debe permitir registrar nuevos empleados con sus datos.

* **RF17 Editar empleado**: El sistema debe permitir modificar los datos de un empleado existente.

* **RF18 Eliminar empleado**: El sistema debe permitir eliminar empleados registrados.

* **RF19 Buscar empleado**: El sistema debe permitir buscar empleados por diferentes criterios.

### Requerimientos no funcionales

