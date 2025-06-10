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

### Diagrama CDU de Alto Nivel
![CDU_alto_nivel](./assets/cdu/CDU-ALTO-NIVEL.png)

### Primera Descomposición
![CDU_primera_descomposicion](./assets/cdu/CDU-PRIMERA-DESCOMPOSICION.png)

## 2. Casos de Uso Expandidos
### Diagramas
![CDU_expandido-gestion_clientes](./assets/cdu/CDU-GESTION-CLIENTES.png)

![CDU_expandido-gestion_empleados](./assets/cdu/CDU-GESTION-EMPLEADOS.png)

![CDU_expandido-gestion_ventas](./assets/cdu/CDU-GESTION-VENTAS.png)

![CDU_expandido-gestion_bodega](./assets/cdu/CDU-GESTION-BODEGA.png)

![CDU_expandido-control_pagos](./assets/cdu/CDU-CONTROL-PAGOS.png)

![CDU_expandido-generacion_reportes](./assets/cdu/CDU-GENERACION-REPORTES.png)

### Listado
- **CDU01 - Registrar Cliente**
- **CDU02 - Actualizar Información del Cliente** 
- **CDU03 - Dar de Baja a Cliente**
- **CDU04 - Efectuar Venta**
- **CDU05 - Aplicar Descuento**
- **CDU06 - Modificar Cantidades en Inventario**
- **CDU07 - Anular Venta**
- **CDU08 - Encontrar Venta**
- **CDU09 - Realizar Pago**
- **CDU10 - Registrar Empleado**
- **CDU11 - Actualizar Información del Empleado**
- **CDU12 - Dar de Baja a Empleado** 
- **CDU13 - Registrar Producto**  
- **CDU14 - Actualizar Información del Producto**
- **CDU15 - Dar de Baja a Producto**
- **CDU16 - Registrar Ingreso a Bodega**
- **CDU17 - Registrar Salida de Bodega**
- **CDU18 - Generar Reporte de Clientes**
- **CDU19 - Generar Reporte de Ventas**
- **CDU20 - Generar Reporte de Empleados**
- **CDU21 - Generar Reporte de Bodega**
- **CDU22 - Generar Reporte de Pagos**

### Descripciones
| **ID**                      | CU01                    |
|-----------------------------|-------------------------|
| **Nombre**                  | Caso de uso             |
| **Actores**                 | Actor 1                 |
| **Propósito**               | Proposito               |
| **Resumen**                 | Resumen del caso de uso |
| **Curso Normal de eventos** | 1. Paso <br> 2. Paso 2 <br> 3. Paso 3 |
| **Cursos alternos**         | 1. Flujo alterno <br> 2. Flujo alterno 2           |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | Listado de mejoras      |
| **Otras secciones**         |                         |
| **Seccion**                 | Titulo de la seccion    |
|                             | 1. paso <br> 2. Paso    |

| **ID**                      | CDU04                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Efectuar venta             |
| **Actores**                 | Vendedor                 |
| **Propósito**               | Proposito               |
| **Resumen**                 | El caso de uso se inicia cuando el vendedor concreta una negociación con el cliente y procede a registrar la venta. Se verifica la disponibilidad del producto, se determinan las condiciones de pago y se confirma el acuerdo. El proceso finaliza con el registro de la venta. |
| **Curso Normal de eventos** | 1. El vendedor recibe una lista de los productos que desea adquirir el cliente <br> 2. El vendedor verifica la disponibilidad del inventario o existencia del producto con el bodeguero. <br> 3. El vendedor acuerda con el cliente el precio, condiciones de pago y forma de entrega. <br> 4. Se formaliza la venta, dejando constancia del metodo de pago y los datos del cliente. <br> 5. Se informa al cliente el detalle final de la venta y condiciones acordadas.|
| **Cursos alternos**         | 	2a. Si el producto no está disponible, se informa al cliente y se ofrecen alternativas. <br> 3a. Si no se llega a un acuerdo comercial, la venta se suspende.           |
| **Prioridad**               | Alta                    |
| **Mejoras**                 |  1. Incorporar catálogo actualizado de productos para mejorar la precisión en las ofertas.      |
| **Otras secciones**         |                         |
| **Seccion**                 | Venta con oferta    |
|                             | 1. El vendedor consulta si existen ofertas vigentes para los productos seleccionados. <br> 2. Aplica el descuento correspondiente al precio de venta.   |

| **ID**                      | CDU07                    |
|-----------------------------|-------------------------|
| **Nombre**                  | Anular Venta             |
| **Actores**                 | Vendedor                 |
| **Propósito**               | Permitir al vendedor anular una venta para corregir errores o atender solicitudes de clientes antes de su entrega o facturación definitiva.               |
| **Resumen**                 | El caso de uso inicia cuando el vendedor detecta la necesidad de anular una venta registrada. Para ello, primero localiza la venta. Una vez encontrada, el vendedor valida la condición para la anulación y procede a registrar la anulación. El caso finaliza cuando la venta queda anulada y se actualizan los registros correspondientes. |
| **Curso Normal de eventos** | 1. El vendedor identifica la necesidad de anular una venta. <br> 2. El vendedor busca la venta por número de envío o nombre del cliente. <br> 3. Se localiza la venta y se verifica que cumple los criterios para ser anulada (no entregada, sin pagos aplicados). <br> 4. El vendedor solicita la anulación de la venta. <br> 5. Se registra la anulación <br> 6. Se le notifica al vendedor que la venta fue anulada. |
| **Cursos alternos**         | 3a. Si la venta no existe, se notifica al vendedor y se solicita revisar los datos. <br> 3b. Si la venta no cumple los criterios para anulación, se informa al vendedor y se cancela el proceso. |
| **Prioridad**               | Media-Alta                    |
| **Mejoras**                 | 1. Registrar el motivo de anulación. <br> 2. Notificar a finanzas y logística sobre la anulación.      |
| **Otras secciones**         |  No aplica   |


| **ID**                      | CDU08                    |
|-----------------------------|-------------------------|
| **Nombre**                  | Encontrar venta             |
| **Actores**                 | Vendedor, Encargado de Bodega, Cajero |
| **Propósito**               | Localizar información de una venta para validación, seguimiento o atención al cliente. |
| **Resumen**                 | El caso de uso se inicia cuando el vendedor, cajero o encargado de bodega necesita consultar los datos de una venta. El proceso permite localizar la venta con base en distintos criterios de búsqueda. El caso de uso finaliza cuando se le notifica al actor el resultado de la busqueda. |
| **Curso Normal de eventos** | 1. El vendedor, cajero o encargado de bodega detecta la necesidad de consultar una venta, ya sea para validar una entrega, resolver una duda del cliente o realizar una gestión operativa. <br> 2. El actor solicita acceso al registro de ventas disponibles en la empresa. <br> 3. El actor busca la venta utilizando como referencia el número de envío proporcionado por el cliente y/o el nombre del cliente. <br> 4. El responsable del registro de ventas verifica la existencia de la venta en los archivos de respaldo. <br> 5. Se extrae la venta. <br> 6. Se le notifica al actor el resultado de la busqueda.|
| **Cursos alternos**         | 4a. Si el número de envío o el nombre del cliente no coinciden con ningún registro, el responsable informa al actor que no se encontró la venta solicitada.|           |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Designar responsables por área para atender búsquedas urgentes de ventas. <br> 2. Registrar intentos de búsqueda fallidos para mejorar la calidad de los datos.|
| **Otras secciones**         | No aplica                         |

| **ID**                      | CDU10                                |
|-----------------------------|--------------------------------------|
| **Nombre**                  | Registrar empleado                   |
| **Actores**                 | Administración                      |
| **Propósito**               | Iniciar formalmente la incorporación de un nuevo empleado a la organización. |
| **Resumen**                 | Este caso de uso inicia cuando se requiere registrar un nuevo empleado. Involucra recopilar sus datos personales y laborales para su integración en la empresa. El caso finaliza cuando el empleado queda formalmente registrado para desempeñar sus funciones. |
| **Curso Normal de eventos** | 1. Administración identifica la necesidad de registrar un nuevo empleado. <br> 2. Recolecta los datos básicos: nombres, apellidos, dirección, teléfono y comisión (si aplica). <br> 3. Verifica que la información esté completa y correcta. <br> 4. Valida que no exista duplicidad. <br> 5. Formaliza el ingreso del empleado en la organización. |
| **Cursos alternos**         | 1. Si la información es incompleta, se solicita corrección. <br> 2. Si ya existe un registro similar, se detiene el proceso y se revisa con recursos humanos. |
| **Prioridad**               | Alta                                 |
| **Mejoras**                 | Incorporar verificación automática de duplicidad <br> Integrar validaciones con área de RRHH |
| **Otras secciones**         |                                      |
| **Seccion**                 | Gestión de empleados             |
|                             | 1. Validación de datos básicos <br> 2. Aprobación por responsable administrativo |

| **ID**                      | CDU11                                |
|-----------------------------|--------------------------------------|
| **Nombre**                  | Actualizar información del empleado  |
| **Actores**                 | Administración                      |
| **Propósito**               | Mantener actualizada la información del personal dentro de la organización. |
| **Resumen**                 | Este caso de uso se activa cuando hay necesidad de modificar datos de un empleado por cambios personales, errores previos o ajustes contractuales. Finaliza cuando los datos actualizados están verificados y aceptados en el expediente del empleado. |
| **Curso Normal de eventos** | 1. Administración detecta o recibe solicitud de cambio. <br> 2. Verifica si el cambio es válido y autorizado. <br> 3. Accede al expediente del empleado. <br> 4. Realiza los ajustes necesarios. <br> 5. Confirma y documenta el cambio. |
| **Cursos alternos**         | 1. Si el dato a modificar requiere aprobación de RRHH, se deriva para revisión. <br> 2. Si el dato es sensible, se solicita validación adicional. |
| **Prioridad**               | Media                                |
| **Mejoras**                 | Agregar control de cambios y motivo de edición |
| **Otras secciones**         |                                      |
| **Seccion**                 | Gestión de empleados   |
|                             | 1. Cambios validados por supervisor <br> 2. Documentación de fecha y responsable |

| **ID**                      | CDU12                                |
|-----------------------------|--------------------------------------|
| **Nombre**                  | Dar de baja a empleado               |
| **Actores**                 | Administración                      |
| **Propósito**               | Formalizar el retiro de un empleado de la organización. |
| **Resumen**                 | Este caso de uso inicia cuando un empleado deja de trabajar en la empresa, ya sea por renuncia, despido o retiro programado. Se realiza una baja formal en la organización, asegurando que el historial se mantenga pero su actividad operativa se detenga. |
| **Curso Normal de eventos** | 1. Administración recibe la notificación de retiro. <br> 2. Revisa que el empleado no tenga pendientes activos. <br> 3. Confirma la salida. <br> 4. Registra la baja y su justificación. <br> 5. El empleado queda fuera del sistema operativo, pero su información permanece en el archivo histórico. |
| **Cursos alternos**         | 1. Si aún hay procesos abiertos asociados al empleado, se solicita resolverlos antes de dar la baja. |
| **Prioridad**               | Alta                                 |
| **Mejoras**                 | Implementar bitácora de bajas <br> Automatizar alertas de pendientes asociados |
| **Otras secciones**         |                                      |
| **Seccion**                 | Gestión de empleados                    |
|                             | 1. Validación de egreso administrativo <br> 2. Registro del motivo y fecha |


| **ID**                      | CDU16                                |
|-----------------------------|--------------------------------------|
| **Nombre**                  | Registrar ingreso a bodega  |
| **Actores**                 | Encargado de bodega                      |
| **Propósito**               | Asegurar que los productos importados sean registrados al momento de ingresar fisicamente a la bodega. |
| **Resumen**                 | Este caso de uso inicia cuando se recibe una carga de productos importados en las instalaciones de IMPORCOMGUA. El encargado de bodega realiza la verificación física del cargamento con base en la documentación de importación y procede a registrar el ingreso. Finaliza cuando los productos han sido contabilizados y ubicados correctamente dentro del almacén. |
| **Curso Normal de eventos** | 1. El Encargado de Bodega recibe el cargamento y la documentación de importación. <br> 2. Se realiza la inspección física de los productos recibidos. <br> 3. Se verifica la coincidencia entre la carga física y los documentos. <br> 4. Registra el ingreso en el libro de control. <br> 5. Modifica las cantidades en inventario según lo recibido. <br> 6. Almacena los productos en las ubicaciones asignadas dentro de la bodega. |
| **Cursos alternos**         | 1. Si existen diferencias entre la carga y la documentación, se notifica al área de Administracion para revisión. <br> 2. Si la mercancía presenta daños, se documenta y se genera un reporte para el área de compras o administracion. |
| **Prioridad**               | Alta                                |
| **Mejoras**                 | Implementar código de barras para agilizar el registro y reducir errores manuales. |
| **Otras secciones**         |                                      |
| **Seccion**                 | Gestión de inventarios   |
|                             | 1. Registro validado por encargado de bodega. <br> 2. Notificación automática al departamento de ventas sobre disponibilidad. |

| **ID**                      | CDU17                                |
|-----------------------------|--------------------------------------|
| **Nombre**                  | Registrar salida de bodega  |
| **Actores**                 | Encargado de bodega                      |
| **Propósito**               | Garantizar que la salida de productos desde la bodega esté debidamente registrada, verificada y controlada, manteniendo actualizado el inventario. |
| **Resumen**                 | Este caso de uso inicia cuando se autoriza la salida de productos desde la bodega, ya sea por entrega a clientes o traslado interno. El Encargado de Bodega verifica la solicitud, prepara los productos, actualiza el inventario y documenta la salida. Finaliza cuando los productos han salido físicamente y su registro ha sido completado. |
| **Curso Normal de eventos** | 1. El Encargado de Bodega recibe la solicitud de salida de productos. <br> 2. Verifica que la solicitud esté autorizada y que los productos estén disponibles. <br> 3. Se prepara los productos para entrega o traslado. <br> 4. Se registra la salida en el libro de control. <br> 5. Modifica las cantidades en inventario para reflejar la salida. <br> 6. Entrega la mercancía al destinatario autorizado, con firma de recepción. |
| **Cursos alternos**         | 1. Si los productos solicitados no están disponibles, se notifica a administracion o ventas. <br> 2. Si la solicitud no está autorizada, se rechaza y se informa al solicitante. |
| **Prioridad**               | Alta                                |
| **Mejoras**                 | Implementar sistema de alertas para productos con baja rotación y control de fechas de vencimiento. |
| **Otras secciones**         |                                      |
| **Seccion**                 | Gestión de inventarios   |
|                             | 1. Guía de remisión, factura, registro de venta. <br> 2. Notificación al departamento de ventas sobre niveles de inventario críticos. |


## 3. Drivers Arquitectónicos
### Requerimientos Funcionales Críticos (RF)

#### Gestión de Clientes
* **RF01 - Registro de Cliente**: El sistema debe permitir registrar un nuevo cliente con sus datos personales.
* **RF02 - Edición de Cliente**: El sistema debe permitir modificar los datos de un cliente existente.
* **RF03 - Eliminación de Cliente**: El sistema debe permitir eliminar un cliente registrado.
* **RF04 - Busqueda de Cliente**: El sistema debe permitir buscar clientes por distintos criterios.

#### Gestión de Bodega
* **RF05 - Registro de Producto**: El sistema debe permitir registrar un nuevo producto en el inventario.
* **RF06 - Edición de Producto**: El sistema debe permitir modificar los detalles de un producto existente.
* **RF07 - Eliminación de Producto**: El sistema debe permitir eliminar productos del inventario.
* **RF08 - Busqueda de Producto**: El sistema debe permitir buscar productos por diferentes criterios.
* **RF09 - Registro de Salida de Inventario**: El sistema debe permitir registrar la salida de productos por ventas u otros motivos.
* **RF10 - Registro de Ingreso a Inventario**: El sistema debe permitir registrar el ingreso de productos al inventario.
* **RF11 - Modificacion de Stock en Inventario**: El sistema debe permitir realizar ajustes manuales al inventario registrado.

#### Gestión de Ventas
* **RF12 - Registro de Venta**: El sistema debe permitir registrar una nueva venta con los datos del cliente y productos vendidos.
* **RF13 - Anulación de Venta**: El sistema debe permitir anular ventas realizadas anteriormente.
* **RF14 - Busqueda de Ventas**: El sistema debe permitir buscar ventas realizadas por diferentes parámetros.
* **RF15 - Aplicación de Descuento**: El sistema debe permitir aplicar descuentos en las ventas según condiciones definidas.

#### Gestión de Empleados
* **RF16 - Registro de Empleado**: El sistema debe permitir registrar nuevos empleados con sus datos.
* **RF17 - Edición de Empleado**: El sistema debe permitir modificar los datos de un empleado existente.
* **RF18 - Eliminación de Empleado**: El sistema debe permitir eliminar empleados registrados.
* **RF19 - Busqueda de Empleado**: El sistema debe permitir buscar empleados por diferentes criterios.

### Requisitos No Funcionales (RNF)

#### Seguridad
- **RNF01 - Autenticación**: El sistema requiere autenticación para acceder a funcionalidades de administración como la gestión de clientes, productos, ventas e inventario.
- **RNF02 - Seguridad en Contraseñas**: Las contraseñas de los usuarios se almacenarán cifradas utilizando el algoritmo AES.
- **RNF03 - Duración de Sesiones**: Las sesiones de usuario tendrán una duración máxima de 24 horas, después de lo cual se cerrarán automáticamente.
- **RNF04 - Autorización**: Solo los usuarios autenticados y autorizados podrán registrar ventas, cobros, salidas de bodega o realizar pagos.

#### Eficiencia
- **RNF05 - Operaciones Rapidas**: Las operaciones críticas como registro de ventas, pagos y búsquedas de envíos deberán completarse en menos de 3 segundos.
- **RNF06 - Soporte a Varias Transacciones**: El sistema debe soportar al menos 10,000 transacciones simultáneas sin degradar el rendimiento, especialmente en los módulos de inventario y ventas.

#### Usabilidad
- **RNF07 - UI para Escritorio**: La interfaz adaptarse correctamente a dispositivos de escritorios.
- **RNF08 - UI Especifica por Rol**: Las opciones del menú deben estar claramente categorizadas y organizadas según los roles de usuario (administrador, supervisor, vendedor).
- **RNF09 - UI Intuitiva**: El usuario debe poder realizar búsquedas de clientes, ventas o productos de forma intuitiva desde cualquier vista relevante.

#### Disponibilidad
- **RNF10 - Alta Disponibilidad**: El sistema deberá garantizar una disponibilidad del 99.99% anual.
- **RNF11 - CI/CD**: La implementación de nuevas funcionalidades no debe requerir la interrupción del servicio activo para los usuarios.

#### Escalabilidad
- **RNF12 - Arquitectura Escalable**: La arquitectura del sistema debe ser modular y permitir la integración de nuevos módulos sin afectar el rendimiento existente.

#### Mantenibilidad
- **RNF13 - Clara Documentación**: El código fuente deberá documentarse adecuadamente, siguiendo estándares de nomenclatura y comentarios estructurados.

#### Portabilidad
- **RNF14 - Portabilidad de la Aplicación**: El sistema debe poder ejecutarse en distintos navegadores web modernos (Chrome, Firefox, Edge).
- **RNF15 - Entorno de Despliegue**: La solución debe estar preparada para ser desplegada en ambientes Linux.

## 4. Matrices de Trazabilidad
### Stakeholders vs Requerimientos

### Stakeholders vs CDU

### Requerimientos vs CDU

