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
- **CDU23 - Búsqueda de Cliente**
- **CDU24 - Búsqueda de Producto**
- **CDU25 - Búsqueda de Empleado**



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

| **ID**                      | CDU01                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Registrar Cliente       |
| **Actores**                 | Empleado de Ventas      |
| **Propósito**               | Incorporar un nuevo cliente al sistema para habilitar su participación en procesos comerciales. |
| **Resumen**                 | El caso de uso inicia cuando un empleado de ventas debe registrar a un nuevo cliente. El sistema solicita los datos básicos del cliente, verifica que no exista previamente en la base de datos, y si todo es válido, se almacena el nuevo registro y se confirma la operación. |
| **Curso Normal de eventos** | 1. El empleado accede al módulo de registro de clientes. <br> 2. El sistema solicita los datos personales y de contacto. <br> 3. El empleado ingresa los datos requeridos. <br> 4. El sistema verifica si el cliente ya está registrado. <br> 5. Si no existe, el sistema almacena el nuevo cliente. <br> 6. Se confirma el registro exitoso. |
| **Cursos alternos**         | 4a. Si el cliente ya existe, el sistema notifica al usuario y cancela el proceso. <br> 3a. Si faltan campos obligatorios, el sistema muestra un mensaje de error y solicita completar la información. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Implementar validación automática de identificación por medio de API de registros públicos. <br> 2. Permitir precarga de datos mediante formularios en línea. |
| **Otras secciones**         |  No aplica   |

| **ID**                      | CDU02                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Actualizar Información del Cliente |
| **Actores**                 | Empleado de Ventas      |
| **Propósito**               | Modificar o corregir los datos previamente registrados de un cliente existente. |
| **Resumen**                 | El caso de uso se activa cuando se detecta un cambio o error en la información de un cliente. El empleado localiza al cliente, modifica los campos necesarios, y el sistema guarda los cambios tras validaciones básicas. Se genera un registro de auditoría para trazabilidad. |
| **Curso Normal de eventos** | 1. El empleado accede al módulo de búsqueda de clientes. <br> 2. El sistema muestra una lista o formulario para localización. <br> 3. El empleado selecciona al cliente y accede a su información. <br> 4. El empleado modifica los datos requeridos. <br> 5. El sistema valida la información ingresada. <br> 6. Se actualiza el registro del cliente. <br> 7. El sistema almacena un registro de auditoría. |
| **Cursos alternos**         | 2a. Si el cliente no se encuentra, el sistema notifica al usuario y finaliza el proceso. <br> 5a. Si los datos modificados no cumplen con las validaciones, se muestra un mensaje de error y se solicita corrección. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Incorporar historial completo de modificaciones por campo. <br> 2. Agregar opción de revertir cambios recientes dentro de una ventana temporal. |
| **Otras secciones**         |  No aplica   |


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

| **ID**                      | CDU05                             |
|-----------------------------|-----------------------------------|
| **Nombre**                  | Aplicar descuento                 |
| **Actores**                 | Vendedor                          |
| **Propósito**               | Ajustar el total de una venta aplicando un descuento global conforme a políticas comerciales. |
| **Resumen**                 | Durante el proceso de facturación, el vendedor evalúa si el cliente cumple condiciones específicas. Si es así, se aplica un único descuento al total de la venta, modificando el monto final antes de generar el comprobante. |
| **Curso Normal de eventos** | 1. Vendedor completa los productos de la venta. <br> 2. Evalúa si aplica un descuento global. <br> 3. Ingresa el porcentaje o monto autorizado. <br> 4. Se recalcula el total de la venta. <br> 5. Se muestra el nuevo total con descuento aplicado. |
| **Cursos alternos**         | 1. Si el descuento excede el límite permitido, se solicita autorización del supervisor. <br> 2. Si el cliente no cumple condiciones, no se permite aplicar descuento. |
| **Prioridad**               | Media                             |
| **Mejoras**                 | Sugerencias automáticas de descuento según historial de cliente <br> Registro histórico para análisis comercial |
| **Otras secciones**         |                                   |
| **Seccion**                 | Gestión de ventas               |
|                             | 1. Evaluación de condiciones <br> 2. Aplicación de descuento validado |

| **ID**                      | CDU06                                   |
|-----------------------------|-----------------------------------------|
| **Nombre**                  | Modificar cantidades en inventario      |
| **Actores**                 | Empleado de Bodega                                  |
| **Propósito**               | Ajustar manualmente cantidades en inventario por errores, mermas o eventos especiales. |
| **Resumen**                 | La bodega puede ajustar las existencias cuando hay errores detectados, roturas, productos vencidos o devoluciones. Este caso de uso registra una entrada o salida ajustada del inventario, asegurando trazabilidad. |
| **Curso Normal de eventos** | 1. Personal de bodega detecta discrepancia. <br> 2. Registra observación y motivo del ajuste. <br> 3. Modifica cantidad correspondiente (ingreso o salida). <br> 4. El sistema actualiza total de unidades y guarda registro del movimiento. |
| **Cursos alternos**         | 1. Si el ajuste es por lote dañado, se notifica a proveedor. <br> 2. Si hay diferencia grave, se remite al área de control interno. |
| **Prioridad**               | Alta                                     |
| **Mejoras**                 | Validación cruzada de inventario con ventas <br> Reporte mensual de ajustes realizados |
| **Otras secciones**         |                                         |
| **Seccion**                 | Gestión de bodega                        |
|                             | No aplica |


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

| **ID**                      | CDU09                              |
|-----------------------------|------------------------------------|
| **Nombre**                  | Realizar pago                      |
| **Actores**                 | Cajero              |
| **Propósito**               | Gestionar el seguimiento y aplicación de pagos realizados por clientes a cuentas por cobrar. |
| **Resumen**                 | Como parte del proceso de recuperación de crédito, el encargado de finanzas recibe, valida y aplica los pagos efectuados por los clientes. Estos pagos pueden ser abonos parciales o liquidaciones totales de ventas a crédito. El proceso garantiza la trazabilidad del cumplimiento de pago, facilita la gestión financiera y contribuye al flujo de caja de la empresa. |
| **Curso Normal de eventos** | 1. Cliente realiza un pago correspondiente a una deuda pendiente. <br> 2. El encargado de finanzas recibe y valida el pago con la documentación de respaldo. <br> 3. Se registra internamente como parte del control de cuentas por cobrar. <br> 4. Se actualiza el estado de la cuenta del cliente para reflejar el nuevo saldo. <br> 5. Se conserva historial de pagos para auditoría y conciliación. |
| **Cursos alternos**         | 1. Si el pago no coincide con el monto esperado, se registra como abono parcial y se notifica al cliente. <br> 2. Si el cliente presenta problemas con el método de pago, se difiere el registro hasta validación. |
| **Prioridad**               | Alta                               |
| **Mejoras**                 | Establecer alertas de vencimiento de pago <br> Automatizar conciliación con bancos |
| **Otras secciones**         |                                    |
| **Seccion**                 | Control de pagos  |
|                             | 1. Recepción del pago <br> 2. Aplicación al saldo <br> 3. Gestión del estado de cuenta |



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


| **ID**                      | CDU13                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Registrar producto      |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Permitir que el empleado de bodega pueda ingresar un producto nuevo y sus características, al inventario de la empresa. |
| **Resumen**                 | El empleado de bodega registra un producto que se ha agregado al inventario recientemente. |
| **Curso Normal de eventos** | 1. El empleado de bodega ingresa al formulario de registro de productos.</br> 2. Llena el formulario ingresando los datos necesarios del producto.</br> 3. Especifica el nombre del producto.</br> 4. Selecciona la unidad de medida (Unidad, Fardo o Paquete).</br> 5. Ingresa la cantidad de unidades por fardo o paquete.</br> 6. Guarda la información del producto en el sistema. |
| **Cursos alternos**         | 1. Si el código del producto ya existe, el sistema informa el error y solicita uno diferente.</br> 2. Si se omite un dato obligatorio, el sistema solicita completarlo antes de guardar. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | Disminución de errores de inventario por productos no registrados o mal clasificados. |
| **Otras secciones**         |                         |
| **Seccion**                 | Gestión de inventario    |
|                             | 1. Registro validado por encargado de bodega |


| **ID**                      | CDU14                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Actualizar información del producto |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Modificar los datos ingresados anteriormente de un producto |
| **Resumen**                 | Cuando hay errores o cambios en la configuración de un producto (por ejemplo, su nombre, unidad de medida o cantidad por paquete), el empleado puede actualizar esa información para mantener el inventario preciso y coherente. |
| **Curso Normal de eventos** | 1. El empleado busca el producto por código o nombre.</br>2. El sistema muestra los datos actuales del producto.</br>3. El empleado modifica uno o varios campos según necesidad.</br>4. Guarda los cambios realizados. |
| **Cursos alternos**         | 1. Si el producto no existe, el sistema informa al usuario. </br> 2. Si se intenta modificar el código del producto a uno que ya está en uso, el sistema lo impide. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | Corrige posibles errores y mantiene alineados los datos con la realidad operativa. |
| **Otras secciones**         | No aplica |


| **ID**                      | CDU15                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Dar de baja a producto  |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Inhabilitar productos que ya no están disponibles o han salido del catálogo comercial. |
| **Resumen**                 | El empleado de bodega puede marcar un producto como inactivo para evitar que se siga utilizando en nuevos registros de ventas o ingresos de inventario, sin eliminar su historial del sistema. |
| **Curso Normal de eventos** | 1. El empleado busca el producto que desea dar de baja. </br> 2. Revisa la información y confirma que el producto no se utiliza activamente. </br> 3. Marca el producto como "Inactivo" o "Dado de baja". </br> 4. El sistema actualiza el estado y restringe su uso en operaciones futuras. |
| **Cursos alternos**         | 1. Si el producto tiene inventario actual disponible, el sistema podría advertir antes de permitir su baja. </br> 2. Si el producto está asociado a una venta en proceso, el sistema impide la baja hasta que finalice. |
| **Prioridad**               | Media                    |
| **Mejoras**                 | Evita errores al utilizar productos obsoletos y mejora la organización del catálogo.      |
| **Otras secciones**         | No aplica                        |


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


| **ID**                 | CDU18                   |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Generar Reporte de Clientes                                                 |
| **Actores**            | Gerente General                               |
| **Propósito**          | Obtener una vista consolidada de los datos registrados de los clientes.     |
| **Resumen**            | Se genera un informe con la información detallada de clientes por filtros.  |
| **Curso normal**       | 1. Ingresar al módulo de reportes.<br>2. Seleccionar "Clientes".<br>3. Aplicar filtros y generar.|
| **Cursos alternos**    | 1. No hay clientes registrados.                                               |
| **Prioridad**          | Media                                                                        |
| **Mejoras**            | Permite análisis comercial y toma de decisiones.                            |
| **Otras secciones**    | No aplica                                                 |


| **ID**                 | CDU19                   |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Generar Reporte de Ventas                                                   |
| **Actores**            | Gerente General                              |
| **Propósito**          | Analizar las ventas realizadas en un período específico.                    |
| **Resumen**            | Muestra ventas con detalles de fecha, cliente, vendedor, estado, y montos.  |
| **Curso normal**       | 1. Acceder al módulo de reportes.<br>2. Elegir "Ventas".<br>3. Seleccionar rango de fechas y generar. |
| **Cursos alternos**    | 1. No hay ventas en el rango de fechas.                                      |
| **Prioridad**          | Alta                                                                         |
| **Mejoras**            | Facilita el control de ingresos y seguimiento de operaciones.               |
| **Otras secciones**    | No aplica                                |


| **ID**                 | CDU20                   |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Generar Reporte de Empleados                                                |
| **Actores**            | Gerente General                                |
| **Propósito**          | Visualizar la información de los vendedores y su desempeño.                 |
| **Resumen**            | Lista los datos de contacto, comisiones y rendimiento de vendedores.        |
| **Curso normal**       | 1. Ingresar al módulo de reportes.<br>2. Seleccionar "Empleados".<br>3. Generar informe. |
| **Cursos alternos**    | 1. No hay empleados registrados.                                              |
| **Prioridad**          | Media                                                                        |
| **Mejoras**            | Mejora la gestión de personal y control de comisiones.                      |
| **Otras secciones**    | No aplica                                  |


| **ID**                 | CDU21                   |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Generar Reporte de Bodega                                                   |
| **Actores**            | Gerente General                                              |
| **Propósito**          | Consultar el estado actual del inventario en bodega.                        |
| **Resumen**            | Muestra existencia de productos, cantidades disponibles y apartadas.        |
| **Curso normal**       | 1. Acceder al módulo de reportes.<br>2. Seleccionar "Bodega".<br>3. Generar.|
| **Cursos alternos**    | 1. No hay productos registrados.                                              |
| **Prioridad**          | Alta                                                                         |
| **Mejoras**            | Mejora el control de stock y planificación de compras.                      |
| **Otras secciones**    | No aplica                    |


| **ID**                 | CDU22                   |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Generar Reporte de Pagos                                                    |
| **Actores**            | Gerente General                                             |
| **Propósito**          | Consultar los pagos realizados por los clientes y estados de cuenta.        |
| **Resumen**            | Lista abonos realizados, fechas, montos, bancos y estado de cobro.          |
| **Curso normal**       | 1. Ingresar al módulo de reportes.<br>2. Seleccionar "Pagos".<br>3. Aplicar filtros y generar.|
| **Cursos alternos**    | 1. No hay pagos registrados.                                                  |
| **Prioridad**          | Alta                                                                         |
| **Mejoras**            | Ayuda al seguimiento efectivo de cuentas por cobrar.                        |
| **Otras secciones**    | No aplica                                  |


## 3. Drivers arquitectónicos
### Requerimientos funcionales críticos (RF)

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

#### Generación de Reportes
* **RF20 - Reportes de Empleados**: El sistema debe permitir la generacion de reportes estrategicos sobre empleados.
* **RF21 - Reportes de Clientes**: El sistema debe permitir la generacion de reportes estrategicos sobre clientes.
* **RF22 - Reportes de Pagos**: El sistema debe permitir la generacion de reportes estrategicos relacionado a los pagos.
* **RF23 - Reportes de Ventas**: El sistema debe permitir la generacion de reportes estrategicos sobre ventas.
* **RF24 - Reportes de Bodega**: El sistema debe permitir la generacion de reportes estrategicos relacionados con bodega.

#### Control de Pagos
* **RF25 - Registro de Pago**: El sistema debe permitir el registro de abonos y/o pagos completos para una venta.

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
|Stakeholders\Requerimientos|RF01 Registro de Cliente|RF02 Edicion de Cliente|RF03 Eliminacion de Cliente|RF04 Busqueda de Cliente|RF05 Registro de Producto|RF06 Edicion de Producto|RF07 Eliminacion de Producto|RF08 Busqueda de Producto|RF09 Registro de Salida de Inventario|RF10 Registro de Ingreso a Inventario|RF11 Modificacion de Stock en Inventario|RF12 Registro de Venta|RF13 Anulacion de Venta|RF14 Busqueda de Venta|RF15 Aplicacion de Descuento|RF16 Registro de Empleado|RF17 Edicion de Empleado|RF18 Eliminacion de Empleado|RF19 Busqueda de Empleado|RF20 Reportes de Empleados|RF21 Reportes de Clientes|RF22 Reporte de Pagos|RF23 Reporte de Ventas|RF24 Reportes de Bodega|Registro de Pagos|
|--|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|Gerente General| | | | | | | | | | | | | | | | | | | |X|X|X|X|X| |
|Ventas|X|X| |X| | | | | | | |X|X|X|X| | | | | | | | | | |
|Bodega| | | | |X|X|X|X|X|X|X| | | | | | | | | | | | | | |
|Finanzas| | | | | | | | | | | | | | | | | | | | | | | | |X|
|Administracion| | |X|X| | | | | | | | | | | |X|X|X|X| | | | | | | |

### Stakeholders vs CDU
|Stakeholders\CDU|CDU01 Registrar Cliente|CDU02 Actualizar Informacion del Cliente|CDU03 Dar de Baja a Cliente|CDU04 Efectuar Venta|CDU05 Aplicar Descuento|CDU06 Modificar Cantidades en Inventario|CDU07 Anular Venta|CDU08 Encontrar Venta|CDU09 Realizar Pago|CDU10 Registrar Empleado|CDU11 Actualizar Información del Empleado|CDU12 Dar de Baja a Empleado|CDU13 Registrar Producto|CDU14 Actualizar Información del Producto|CDU15 Dar de Baja a Producto|CDU16 Registrar Ingreso a Bodega|CDU17 Registrar Salida de Bodega|CDU18 Generar Reporte de Clientes|CDU19 Generar Reporte de Ventas|CDU20 Generar Reporte de Empleados|CDU21 Generar Reporte de Bodega|CDU22 Generar Reporte de Pagos|
|--|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|Gerente General| | | | | | | | | | | | | | | | | |X|X|X|X|X|
|Ventas|X|X| |X|X| |X|X| | | | | | | | | | | | | | |
|Bodega| | | | | |X| | | | | | |X|X|X|X|X| | | | | |
|Finanzas| | | | | | | |X|X| | | | | | | | | | | | | |
|Administracion| | |X| | | | | | |X|X|X| | | | | | | | | | |

### Requerimientos vs CDU
|Requerimientos\CDU|CDU01 Registrar Cliente|CDU02 Actualizar Informacion del Cliente|CDU03 Dar de Baja a Cliente|CDU04 Efectuar Venta|CDU05 Aplicar Descuento|CDU06 Modificar Cantidades en Inventario|CDU07 Anular Venta|CDU08 Encontrar Venta|CDU09 Realizar Pago|CDU10 Registrar Empleado|CDU11 Actualizar Información del Empleado|CDU12 Dar de Baja a Empleado|CDU13 Registrar Producto|CDU14 Actualizar Información del Producto|CDU15 Dar de Baja a Producto|CDU16 Registrar Ingreso a Bodega|CDU17 Registrar Salida de Bodega|CDU18 Generar Reporte de Clientes|CDU19 Generar Reporte de Ventas|CDU20 Generar Reporte de Empleados|CDU21 Generar Reporte de Bodega|CDU22 Generar Reporte de Pagos|
|--|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|RF01<br>Registro de Cliente|X| | | | | | | | | | | | | | | | | | | | | |
|RF02<br>Edición de Cliente| |X| | | | | | | | | | | | | | | | | | | | |
|RF03<br>Eliminación de Cliente| | |X| | | | | | | | | | | | | | | | | | | |
|RF04<br>Busqueda de Cliente|X|X|X|X| | | | | | | | | | | | | | | | | | |
|RF05<br>Registro de Producto| | | | | | | | | | | | |X| | | | | | | | | |
|RF06<br>Edición de Producto| | | | | | | | | | | | | |X| | | | | | | | |
|RF07<br>Eliminación de Producto| | | | | | | | | | | | | | |X| | | | | | | |
|RF08<br>Busqueda de Producto| | | | | | | | | | | | |X|X|X| | | | | | | |
|RF09<br>Registro de Salida de Inventario| | | | | | | |X| | | | | | | | |X| | | | | |
|RF10<br>Registro de Ingreso a Inventario| | | | | | | | | | | | | | | |X| | | | | | |
|RF11<br>Modificacion de Stock en Inventario| | | | | |X| | | | | | | | | | | | | | | | |
|RF12<br>Registro de Venta| | | |X| | | | | | | | | | | | | | | | | | |
|RF13<br>Anulación de Venta| | | | | | |X|X| | | | | | | | | | | | | | |
|RF14<br>Busqueda de Ventas| | | | | | |X|X|X| | | | | | | |X| | | | | |
|RF15<br>Aplicación de Descuento| | | |X| | | | | | | | | | | | | | | | | | |
|RF16<br>Registro de Empleado| | | | | | | | | |X| | | | | | | | | | | | |
|RF17<br>Edición de Empleado| | | | | | | | | | |X| | | | | | | | | | | |
|RF18<br>Eliminación de Empleado| | | | | | | | | | | |X| | | | | | | | | | |
|RF19<br>Busqueda de Empleado| | | | | | | | | |X|X|X| | | | | | | | | | |
|RF20<br>Reportes de Empleados| | | | | | | | | | | | | | | | | | | |X| | |
|RF21<br>Reportes de Clientes| | | | | | | | | | | | | | | | | |X| | | | |
|RF22<br>Reportes de Pagos| | | | | | | | | | | | | | | | | | | | | |X|
|RF23<br>Reportes de Ventas| | | | | | | | | | | | | | | | | | |X| | | |
|RF24<br>Reportes de Bodega| | | | | | | | | | | | | | | | | | | | |X| |
|RF25<br>Registro de Pago| | | | | | | |X|X| | | | | | | | | | | | | |
