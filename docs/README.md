# Fase 1 - Documentacion Sistema de Automatización IMPORCOMGUA
## Tabla de contenido
1. [Core del Negocio](#1-core-del-negocio)
2. [Casos de Uso Expandidos](#2-casos-de-uso-expandidos)
3. [Drivers Arquitectónicos](#3-drivers-arquitectónicos)
4. [Matrices de Trazabilidad](#4-matrices-de-trazabilidad)
5. [Estructuras Arquitectonicas y estilos arquitectonicos](#5-estructuras-arquitectonicas-y-estilos-arquitectonicos)
6. [Diagrama de bloques](#6-diagrama-de-bloques)
7. [Diagrama de despliegue](#7-diagrama-de-despliegue)
8. [Diagrama Entidad Relación](#8-diagrama-entidad-relación)
9. [Prototipo de interfaces](#9-mockups)
10. [Gestión del Proyecto](#10-gestión-del-proyecto)

## 1. Core del Negocio
### Descripcion
IMPORCOMGUA es una empresa guatemalteca dedicada al comercio de importaciones, especializada en la adquisición y distribución de productos provenientes del extranjero para su comercialización en el mercado nacional. Su modelo de negocio se centra en gestionar de forma eficiente el proceso completo de importación, almacenamiento, venta y entrega de mercancías, trabajando directamente con clientes y vendedores en distintas regiones del país.

### Stakeholders
* **Administrador**: Gestiona todos los procesos internos de IMPORCOMGUA. (Registrar, Actualizar, Dar de Baja)
* **Vendedor**: Provee informacion sobre si mismo y las ventas a favor de IMPORCOMGUA.
* **Cliente**: Provee informacion de si mismo para IMPORCOMGUA.
* **Aduana**: Se encarga de controlar que los tramites aduaneros esten en orden.
* **Proveedor**: Se encarga de proveer de productos a IMPORCOMGUA.

### Diagrama CDU de Alto Nivel
![CDU_alto_nivel](./assets/cdu/CDU-ALTO-NIVEL.png)

### Primera Descomposición
![CDU_primera_descomposicion](./assets/cdu/CDU-PRIMERA-DESCOMPOSICION.png)

## 2. Casos de Uso Expandidos
### Diagramas
![CDU_expandido-gestion_clientes](./assets/cdu/CDU-GESTION-CLIENTES.png)

![CDU_expandido-gestion_vendedores](./assets/cdu/CDU-GESTION-VENDEDORES.png)

![CDU_expandido-gestion_ventas](./assets/cdu/CDU-GESTION-VENTAS.png)

![CDU_expandido-gestion_bodega](./assets/cdu/CDU-GESTION-BODEGA.png)

![CDU_expandido-control_pagos](./assets/cdu/CDU-CONTROL-PAGOS.png)

### Listado
- **CDU100 - Gestionar Clientes**
- **CDU200 - Gestionar Ventas** 
- **CDU300 - Gestionar Bodega**
- **CDU400 - Gestionar Vendedores**
- **CDU500 - Controlar Pagos**

### Descripciones

| **ID**                      | CDU01                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Registrar Cliente       |
| **Actores**                 | Empleado de Ventas      |
| **Propósito**               | Formalizar la incorporación de nuevos clientes para habilitar relaciones comerciales y procesos de venta. |
| **Resumen**                 | Este proceso se inicia cuando un empleado de ventas identifica a un nuevo cliente interesado en establecer una relación comercial. Se recopilan los datos necesarios para su registro formal, con el objetivo de permitir futuras transacciones, seguimiento comercial y segmentación estratégica. |
| **Curso Normal de eventos** | 1. El empleado identifica a un cliente potencial. <br> 2. Se recopilan los datos relevantes del cliente (identificación, contacto, ubicación). <br> 3. Se valida que el cliente no haya sido previamente registrado. <br> 4. Se formaliza el alta del cliente como parte del portafolio comercial. <br> 5. Se confirma la incorporación y se habilita el historial comercial. |
| **Cursos alternos**         | 3a. Si el cliente ya existe en el portafolio, se notifica al empleado y se sugiere revisar o actualizar su información. <br> 2a. Si los datos recopilados son incompletos, se solicita al empleado completarlos antes de continuar. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Establecer un proceso de seguimiento posterior al registro para aumentar la conversión en ventas. <br> 2. Incorporar una categorización del cliente según su potencial comercial desde el momento del alta. |
| **Otras secciones**         | No aplica               |



| **ID**                      | CDU02                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Actualizar Información del Cliente |
| **Actores**                 | Empleado de Ventas      |
| **Propósito**               | Mantener actualizada la información comercial y de contacto de los clientes para asegurar la eficacia en las operaciones y la gestión de relaciones. |
| **Resumen**                 | Este proceso se inicia cuando un empleado de ventas identifica cambios en los datos de un cliente, como nueva dirección, contacto actualizado o modificaciones en su perfil comercial. La actualización oportuna de esta información permite conservar relaciones comerciales vigentes, prevenir errores y planificar interacciones estratégicas. |
| **Curso Normal de eventos** | 1. El empleado identifica que un cliente tiene datos desactualizados o incorrectos. <br> 2. Se recopila la información verificada directamente con el cliente o por medios oficiales. <br> 3. Se revisa y modifica la información registrada del cliente. <br> 4. Se confirma que los datos estén completos y actualizados. <br> 5. Se documenta la modificación como parte del seguimiento comercial. |
| **Cursos alternos**         | 2a. Si no se logra verificar la nueva información, se posterga la actualización hasta obtener confirmación válida. <br> 4a. Si los datos resultan inconsistentes con el historial comercial, se notifica al área correspondiente para revisión adicional. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Utilizar cada actualización como punto de contacto para fortalecer la relación con el cliente. <br> 2. Aplicar criterios de clasificación comercial según los cambios detectados para ajustar estrategias de venta o retención. |
| **Otras secciones**         | No aplica               |


| **ID**                      | CDU03                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Dar de Baja a Cliente   |
| **Actores**                 | Administrador del Sistema |
| **Propósito**               | Retirar del portafolio comercial a clientes que ya no mantienen una relación activa con la organización. |
| **Resumen**                 | Este proceso se lleva a cabo cuando se identifica que un cliente ha cesado su relación comercial con la empresa, ya sea por decisión propia, inactividad prolongada o criterios estratégicos definidos. Dar de baja a un cliente permite depurar la base activa, focalizar esfuerzos en relaciones vigentes y optimizar la asignación de recursos comerciales. |
| **Curso Normal de eventos** | 1. Se determina que un cliente no presenta actividad comercial reciente o ha solicitado su exclusión. <br> 2. Se valida el cumplimiento de criterios establecidos para la baja. <br> 3. Se documentan los motivos de finalización de relación. <br> 4. Se formaliza la exclusión del cliente del portafolio activo. <br> 5. Se conserva el historial del cliente para análisis y referencia futura. |
| **Cursos alternos**         | 2a. Si el cliente aún presenta obligaciones pendientes, se pospone la baja hasta su resolución. <br> 3a. Si los motivos no están claros o justificados, se solicita revisión adicional por parte de la unidad comercial. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | 1. Establecer indicadores automáticos de inactividad para identificar oportunamente clientes candidatos a baja. <br> 2. Analizar patrones de baja para fortalecer políticas de retención y servicio. |
| **Otras secciones**         | No aplica               |


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


| **ID**                      | CDU13                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Registrar producto      |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Permitir que el empleado de bodega pueda ingresar un producto nuevo y sus características, al inventario de la empresa. |
| **Resumen**                 | El empleado de bodega registra un producto que se ha agregado al inventario recientemente. |
| **Curso Normal de eventos** | 1. El empleado de bodega detecta que es necesario registrar un nuevo producto.<br> 2. Adquiere los datos relevantes del producto como el código, el nombre, la unidad de presentación (Unidad, Fardo o Paquete) y la cantidad de unidades por presentación.<br> 3. Verifica que la información esté completa y correcta.<br>4. Valida que no exista duplicidad. <br> 5. Formaliza el ingreso del producto en el negocio.  |
| **Cursos alternos**         | 3a. Si se omite un dato obligatorio, el empleado de bodega solicita el dato faltante.<br> 4a. Si el código del producto ya ha sido registrado, el empleado de bodega informa el error para colocar un código diferente. |
| **Prioridad**               | Alta                                                                                  |
| **Mejoras**                 | Disminución de errores de inventario por productos no registrados o mal clasificados. |
| **Otras secciones**         |                                                                                       |
| **Seccion**                 | Gestión de inventario                                                                 |
|                             | 1. Registro validado por encargado de bodega                                          |


| **ID**                      | CDU14                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Actualizar información del producto |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Modificar los datos ingresados anteriormente de un producto |
| **Resumen**                 | Cuando hay errores o cambios en la información de un producto, el empleado puede actualizar esa información para mantener el inventario preciso y coherente. |
| **Curso Normal de eventos** | 1. El empleado busca el producto por código o nombre.</br>2. Modifica uno o varios datos según sea necesario.</br>3. Archiva la información del producto con los cambios realizados. |
| **Cursos alternos**         | 1a. Si el producto no existe, el empleado de bodega informa al negocio. </br> 2a. Si se pretende modificar el código de un producto a uno ya existente, el negocio no debe aceptar dicho cambio. |
| **Prioridad**               | Alta                    |
| **Mejoras**                 | Corrige posibles errores y mantiene alineados los datos con la realidad operativa. |
| **Otras secciones**         | No aplica |


| **ID**                      | CDU15                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Dar de baja a producto  |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Inhabilitar productos que ya no están disponibles o han salido del catálogo comercial. |
| **Resumen**                 | El empleado de bodega puede definir un producto como inactivo para evitar que se siga utilizando en nuevos registros de ventas o ingresos de inventario, sin eliminar su historial del negocio. |
| **Curso Normal de eventos** | 1. El empleado busca el producto que desea dar de baja. </br> 2. Revisa que el producto no se haya descontinuado y confirma que el producto no se utiliza activamente. </br> 3. Especifica el estado del producto como "Inactivo" o "Dado de baja". </br> 4. El negocio restringe el uso del producto en operaciones futuras. |
| **Cursos alternos**         | 2a. Si el producto tiene inventario actual disponible, el encargado de bodega podría advertir antes de permitir su baja. </br> 2b. Si el producto está asociado a una venta en proceso, el negocio impide la baja de este hasta que la venta se concrete. |
| **Prioridad**               | Media                    |
| **Mejoras**                 | Evita errores al utilizar productos descontinuados y mejora la organización del catálogo.      |
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


| **ID**                 | CDU18                                                                       |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Analizar Resultados de Clientes                                             |
| **Actores**            | Gerente General                                                             |
| **Propósito**          | Analizar la información registrada de los clientes.                         |
| **Resumen**            | Se genera un informe con la información detallada de clientes.              |
| **Curso normal**       | 1. El Gerente obtiene la información archivada de los clientes. <br> 2. Organiza la información según el campo o campos que se desea analizar. <br> 3. Identifica solo los datos requeridos. <br> 4. Analiza los datos seleccionados para representarlos en un informe.   |
| **Cursos alternos**    | 1a. No hay clientes registrados.                                            |
| **Prioridad**          | Media                                                                       |
| **Mejoras**            | Permite análisis comercial y toma de decisiones.                            |
| **Otras secciones**    | No aplica                                                                   |


| **ID**                 | CDU19                                                                              |
|------------------------|------------------------------------------------------------------------------------|
| **Nombre**             | Analizar Resultados de Ventas                                                      |
| **Actores**            | Gerente General                                                                    |
| **Propósito**          | Analizar las ventas realizadas en un período específico.                           |
| **Resumen**            | Se genera un informe con la información detallada de las ventas.                   |
| **Curso normal**       | 1. El gerente obtiene la información archivada de las ventas. <br> 2. Organiza la información según el campo o campos que se desea analizar. <br> 3. Identifica solo los datos requeridos. <br> 4. Analiza los datos seleccionados para representarlos en un informe.   |
| **Cursos alternos**    | 1a. No hay ventas en el rango de fechas.                                           |
| **Prioridad**          | Alta                                                                               |
| **Mejoras**            | Facilita el control de ingresos y seguimiento de operaciones.                      |
| **Otras secciones**    | No aplica                                                                          |


| **ID**                 | CDU20                                                                   |
|------------------------|-------------------------------------------------------------------------|
| **Nombre**             | Analizar Resultados de Empleados                                        |
| **Actores**            | Gerente General                                                         |
| **Propósito**          | Visualizar la información de los vendedores y su desempeño.             |
| **Resumen**            | Se genera un informe con la información detallada de los empleados.     |
| **Curso normal**       | 1. El gerente obtiene la información archivada de los empleados. <br> 2. Organiza la información según el campo o campos que se desea analizar. <br> 3. Identifica solo los datos requeridos. <br> 4. Analiza los datos seleccionados para representarlos en un informe.   |
| **Cursos alternos**    | 1a. No hay empleados registrados.                                       |
| **Prioridad**          | Media                                                                   |
| **Mejoras**            | Mejora la gestión de personal y control de comisiones.                  |
| **Otras secciones**    | No aplica                                                               |


| **ID**                 | CDU21                                                                       |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Analizar Resultados de Bodega                                               |
| **Actores**            | Gerente General                                                             |
| **Propósito**          | Consultar el estado actual del inventario en bodega.                        |
| **Resumen**            | Muestra existencia de productos, cantidades disponibles y apartadas.        |
| **Curso normal**       | 1. El gerente obtiene la información relacionada con el inventario. <br> 2. Organiza la información según el campo o campos que se desea analizar. <br> 3. Identifica solo los datos requeridos. <br> 4. Analiza los datos seleccionados para representarlos en un informe.   |
| **Cursos alternos**    | 1a. No hay productos registrados.                                           |
| **Prioridad**          | Alta                                                                        |
| **Mejoras**            | Mejora el control de inventario y planificación de compras.                 |
| **Otras secciones**    | No aplica                                                                   |


| **ID**                 | CDU22                                                                       |
|------------------------|-----------------------------------------------------------------------------|
| **Nombre**             | Analizar Resultados de Pagos                                                |
| **Actores**            | Gerente General                                                             |
| **Propósito**          | Consultar los pagos realizados por los clientes y estados de cuenta.        |
| **Resumen**            | Lista abonos realizados, fechas, montos, bancos y estado de cobro.          |
| **Curso normal**       | 1. El gerente obtiene la información relacionada con los pagos. <br> 2. Organiza la información según el campo o campos que se desea analizar. <br> 3. Identifica solo los datos requeridos. <br> 4. Analiza los datos seleccionados para representarlos en un informe.   |
| **Cursos alternos**    | 1a. No hay pagos registrados.                                               |
| **Prioridad**          | Alta                                                                        |
| **Mejoras**            | Ayuda al seguimiento efectivo de cuentas por cobrar.                        |
| **Otras secciones**    | No aplica                                                                   |

| **ID**                      | CDU23                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Búsqueda de Cliente     |
| **Actores**                 | Empleado de Ventas      |
| **Propósito**               | Acceder rápidamente a la información de un cliente para facilitar decisiones comerciales, seguimiento o actualización de relación. |
| **Resumen**                 | Este proceso se activa cuando se necesita consultar la situación o los datos relevantes de un cliente en el contexto de una operación comercial. La identificación oportuna del cliente permite mejorar la atención, mantener relaciones efectivas y tomar decisiones basadas en el historial del vínculo comercial. |
| **Curso Normal de eventos** | 1. El empleado identifica la necesidad de consultar un cliente. <br> 2. Define uno o varios criterios de búsqueda basados en información conocida del cliente. <br> 3. Se revisan los datos disponibles sobre el cliente localizado. <br> 4. Se procede con la gestión comercial o administrativa requerida. |
| **Cursos alternos**         | 3a. Si no se encuentra información del cliente, se valora realizar un registro nuevo o solicitar verificación a otra área. |
| **Prioridad**               | Media                   |
| **Mejoras**                 | 1. Establecer criterios de priorización por valor comercial del cliente. <br> 2. Integrar datos de interacción previa para enriquecer la gestión. |
| **Otras secciones**         | No aplica               |


| **ID**                      | CDU24                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Búsqueda de Producto    |
| **Actores**                 | Empleado de Ventas, Bodeguero |
| **Propósito**               | Localizar productos relevantes para atención a clientes, control de inventario o soporte a decisiones de venta. |
| **Resumen**                 | Este proceso permite a los responsables identificar productos disponibles o relacionados, ya sea para preparar una cotización, atender una consulta o verificar el estado de inventario. Su correcta ejecución permite mejorar la eficiencia comercial, evitar pérdidas de tiempo y brindar mejor asesoramiento. |
| **Curso Normal de eventos** | 1. El empleado requiere identificar un producto por razones comerciales o logísticas. <br> 2. Se determinan las características conocidas del producto a localizar. <br> 3. Se analiza la información del producto recuperado. <br> 4. Se continúa con la acción comercial o de inventario según corresponda. |
| **Cursos alternos**         | 3a. Si no se identifica el producto, se plantea la búsqueda de equivalentes o alternativas de venta. |
| **Prioridad**               | Media                   |
| **Mejoras**                 | 1. Incorporar criterios de rotación, margen o demanda como filtros de búsqueda. <br> 2. Facilitar el acceso a información complementaria útil para el cierre de la venta. |
| **Otras secciones**         | No aplica               |


| **ID**                      | CDU25                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Búsqueda de Empleado    |
| **Actores**                 | Administrador del Sistema |
| **Propósito**               | Consultar información del personal para toma de decisiones operativas, asignación de tareas o evaluación administrativa. |
| **Resumen**                 | Esta actividad permite al responsable acceder a la información de un empleado para dar seguimiento a sus funciones, validar su estado actual o asignarle responsabilidades. Facilita la correcta gestión de los recursos humanos en función de las necesidades de la operación. |
| **Curso Normal de eventos** | 1. El responsable identifica la necesidad de consultar a un miembro del personal. <br> 2. Se determina un criterio de identificación o clasificación. <br> 3. Se revisa el perfil o historial del empleado. <br> 4. Se toma la decisión correspondiente o se coordina la acción administrativa. |
| **Cursos alternos**         | 3a. Si no se encuentra al empleado, se coordina con recursos humanos para validar su situación o estado. |
| **Prioridad**               | Media                   |
| **Mejoras**                 | 1. Considerar atributos operativos como experiencia, desempeño o disponibilidad para facilitar decisiones estratégicas. <br> 2. Incluir métricas laborales en los criterios de consulta. |
| **Otras secciones**         | No aplica               |


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
* **RF09 - Establecer Fecha de Salida**: El sistema debe permitir registrar la salida de productos por ventas u otros motivos.
* **RF10 - Registrar Ingreso a Inventario**: El sistema debe permitir registrar el ingreso de productos y modificar stock en inventario.

#### Gestión de Ventas
* **RF11 - Registro de Venta**: El sistema debe permitir registrar una nueva venta con los datos del cliente y productos vendidos.
* **RF12 - Anulación de Venta**: El sistema debe permitir anular ventas realizadas anteriormente.
* **RF13 - Busqueda de Ventas**: El sistema debe permitir buscar ventas realizadas por diferentes parámetros.

#### Gestión de Empleados
* **RF14 - Registro de Vendedor**: El sistema debe permitir registrar nuevos empleados con sus datos.
* **RF14 - Edición de Vendedor**: El sistema debe permitir modificar los datos de un empleado existente.
* **RF16 - Eliminación de Vendedor**: El sistema debe permitir eliminar empleados registrados.
* **RF17 - Busqueda de Vendedor**: El sistema debe permitir buscar empleados por diferentes criterios.

#### Control de Pagos
* **RF18 - Registro de Pago**: El sistema debe permitir el registro de abonos y/o pagos completos para una venta.

### Requisitos No Funcionales (RNF)

#### Eficiencia
- **RNF01 - Operaciones Rapidas**: Las operaciones críticas como registro de ventas, pagos y búsquedas de envíos deberán completarse en menos de 3 segundos.
- **RNF02 - Soporte a Varias Transacciones**: El sistema debe soportar al menos 10,000 transacciones simultáneas sin degradar el rendimiento, especialmente en los módulos de inventario y ventas.

#### Usabilidad
- **RNF03 - UI para Escritorio**: La interfaz adaptarse correctamente a dispositivos de escritorios.
- **RNF04 - UI Especifica por Rol**: Las opciones del menú deben estar claramente categorizadas y organizadas según los roles de usuario (administrador, supervisor, vendedor).
- **RNF05 - UI Intuitiva**: El usuario debe poder realizar búsquedas de clientes, ventas o productos de forma intuitiva desde cualquier vista relevante.

#### Disponibilidad
- **RNF06 - Alta Disponibilidad**: El sistema deberá garantizar una disponibilidad del 99.99% anual.
- **RNF07 - CI/CD**: La implementación de nuevas funcionalidades no debe requerir la interrupción del servicio activo para los usuarios.

#### Escalabilidad
- **RNF08 - Arquitectura Escalable**: La arquitectura del sistema debe ser modular y permitir la integración de nuevos módulos sin afectar el rendimiento existente.

#### Mantenibilidad
- **RNF09 - Clara Documentación**: El código fuente deberá documentarse adecuadamente, siguiendo estándares de nomenclatura y comentarios estructurados.

### Requisitos de Restricción (Drivers de Restricción)

#### Técnicos
- **RNF10 - Portabilidad de la Aplicación**: El sistema debe poder ejecutarse en distintos navegadores web modernos (Chrome, Firefox, Edge).
- **RNF11 - Entorno de Despliegue**: La solución debe estar preparada para ser desplegada en cualquier entorno..

#### Operacionales
- **RNF12 - Ventas**: Los procesos de venta deben permitir pagos parciales, y manejar correctamente los saldos pendientes.
- **RNF13 - Eliminación de registros**: No se permite eliminación física de registros por normativas del negocio. Solo debe aplicarse baja lógica o estado "inactivo".
- **RNF14 - Modificacion en Stock**: Los procesos involucrados con el stock de productos en el inventario, deben de ser seguros y correctos, manteniendo uniformidad entre los ingresos y ventas.

#### Gestión de proyecto
- **RNF15 - Desarrollo del sistema**: El sistema debe desarrollarse en 3 fases durante un mes, según cronograma aprobado.
- **RNF16 - Metodología**: Se debe usar metodología ágil con tablero Kanban.

## 4. Matrices de Trazabilidad
### Stakeholders vs Requerimientos
|Stakeholders\Requerimientos|RF01 Registro de Cliente|RF02 Edicion de Cliente|RF03 Eliminacion de Cliente|RF04 Busqueda de Cliente|RF05 Registro de Producto|RF06 Edicion de Producto|RF07 Eliminacion de Producto|RF08 Busqueda de Producto|RF09 Establecer de Salida de Inventario|RF10 Registro de Ingreso a Inventario|RF11 Registro de Venta|RF12 Anulacion de Venta|RF13 Busqueda de Venta|RF14 Registro de Vendedor|RF15 Edicion de Vendedor|RF16 Eliminacion de Vendedor|RF17 Busqueda de Vendedor|RF18 Registro de Pagos|
|--|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|Administrador|X|X|X|X|X|X|X|X|X|X|X|X|X|X|X|X|X|X|
|Vendedor| | | | | | | | | | |X| | |X|X| | | |
|Cliente|X|X| | | | | | | | |X| | | | | | |X|
|Aduana| | | | | | | | |X| | | | | | | | | |
|Proveedor| | | | | | | | |X| | | | | | | | | |

### Stakeholders vs CDU
|Stakeholders\CDU|CDU100 Gestionar Clientes|CDU200 Gestiona Ventas|CDU300 Gestionar Bodega|CDU400 Gestionar Vendedores|CDU500 Controlar Pagos| 
|--|:--:|:--:|:--:|:--:|:--:|
|Administrador|X|X|X|X|X|
|Vendedor| |X| |X| |
|Cliente|X|X| | |X|
|Aduana| | |X| | |
|Proveedor| | |X| | |

### Requerimientos vs CDU
|Requerimientos\CDU|CDU100 Gestionar Clientes|CDU200 Gestiona Ventas|CDU300 Gestionar Bodega|CDU400 Gestionar Vendedores|CDU500 Controlar Pagos|
|--|:--:|:--:|:--:|:--:|:--:|
|RF01<br>Registro de Cliente|X| | | | |
|RF02<br>Edición de Cliente|X| | | | |
|RF03<br>Eliminación de Cliente|X| | | | |
|RF04<br>Busqueda de Cliente|X|X| | |X|
|RF05<br>Registro de Producto| | |X| | |
|RF06<br>Edición de Producto| | |X| | |
|RF07<br>Eliminación de Producto| | |X| | |
|RF08<br>Busqueda de Producto| | |X| | |
|RF09<br>Establecer de Salida de Inventario| | |X| | |
|RF10<br>Registro de Ingreso a Inventario| | |X| | |
|RF11<br>Registro de Venta| |X| | | |
|RF12<br>Anulación de Venta| |X| | | |
|RF13<br>Busqueda de Ventas| |X|X| |X|
|RF14<br>Registro de Vendedor| | | |x| |
|RF15<br>Edición de Vendedor| | | |X| |
|RF16<br>Eliminación de Vendedor| | | |X| |
|RF17<br>Busqueda de Vendedor| |X| |X| |
|RF18<br>Registro de Pago| | | | |X|

## 5. Estructuras Arquitectonicas y estilos arquitectonicos
### Identificación de estructuras arquitectónicas


### 5.1 Estructura Modular

Representa el sistema como un conjunto de módulos lógicos organizados por responsabilidad o funcionalidad. Cada módulo encapsula una parte del comportamiento del sistema y se comunica con los demás a través de interfaces bien definidas.

El sistema automatizado de IMPORCOMGUA estará compuesto por módulos como:

1. Gestión de clientes
2. Gestión de productos
3. Ventas
4. Pagos
5. Inventario
6. Reportes

Esta estructura ofre las siguientes ventajas:

1. Mejora la mantenibilidad y escalabilidad.
2. Facilita la asignación de tareas en el equipo de desarrollo.
3. Permite la reutilización de componentes en futuros proyectos o versiones.

### 5.2 Componentes y Conectores

Describe el sistema en términos de componentes ejecutables (por ejemplo, servicios, APIs, interfaces de usuario) y conectores que representan la interacción entre ellos (llamadas a funciones y protocolos de red).

En el siste automatizado de IMPORCOMGUA, esta estructura arquitectonica se podrá ver reflejada en:

1. Un frontend (cliente web) que se comunica con el backend.
2. Servicios expuestos que permiten la integración modular.
3. Conector HTTP/REST entre cliente y servidor.
4. Posibilidad de desacoplar funcionalidades para futuras integraciones externas.

Esta estructura ofre las siguientes ventajas:

1. Modela cómo fluye la información entre los componentes.
2. Permite analizar rendimiento, escalabilidad y tolerancia a fallos.
3. Ideal para arquitecturas orientadas a servicios y cliente-servidor.

### Selección de los estilos arquitectónicos
El sistema propuesto para IMPORCOMGUA se construirá combinando tres estilos arquitectónicos que permiten una estructura clara, modular y fácil de entender. Cada uno responde a necesidades específicas del negocio y del desarrollo del software.
#### Arquitectura en o por capas
Este estilo organiza el sistema en capas independientes, donde cada una cumple una función específica: presentación, lógica de negocio y persistencia de datos.

Se eligió porque facilita la organización del sistema, separa responsabilidades, y mejora el mantenimiento y comprensión general de su funcionamiento.

#### Call return: Cliente-Servidor
Este estilo define una comunicación entre un cliente que solicita servicios y un servidor que los atiende mediante un esquema de petición-respuesta.

Se seleccionó porque estructura la interacción entre quien usa el sistema y quien lo procesa, permitiendo un flujo de datos claro.

#### Call return: SOA (Service-Oriented Architecture)
Este estilo implica que el sistema se estructura en servicios funcionales independientes, cada uno encargado de un proceso del negocio.

Se eligió porque permite una organización modular por funcionalidades, lo cual facilita el desarrollo, la documentación y futuras integraciones.

## 6. Diagrama de bloques
![BLOCKS_DIAGRAM_IMPORCOMGUA](./assets/architecture/blocks-diagram-IMPORCOMGUA-mod1.png)

## 7. Diagrama de despliegue

![diagrama-despliegue](./assets/architecture/DIAGRAMA-DESPLIEGUE.png)

### 7.1 Diagrama de Componentes
![diagrama-componentes](./assets/architecture/DIAGRAMA-COMPONENTES.png)

### 7.2 Diagrama de Distribucion
![diagrama-distribucion](./assets/architecture/DIAGRAMA-DISTRIBUCION.png)

### Justificacion de Frameworks y tecnologias
Para el desarrollo del backend se ha optado por el framework Spring Boot sobre el lenguaje de programación Java, debido a sus ventajas en la construcción de aplicaciones empresariales robustas y escalables.

#### Java + Spring Boot (Backend)
El backend de IMPORCOMGUA estará construido con Spring Boot, un framework moderno sobre Java que organiza el código en capas bien definidas: controladores, servicios y acceso a datos. Esto ayuda en la arquitectura a:

* Establecer una arquitectura lógica clara, donde cada módulo funcional (ventas, pagos, inventario, empleados, etc.) tiene una ubicación específica y fácilmente identificable.

* Mantener una correspondencia directa entre los casos de uso documentados y su implementación técnica, lo que facilita la trazabilidad entre los requerimientos del negocio y el código fuente.

* Soporte nativo para seguridad, autenticación y validación, útil para la gestión de usuarios y control de acceso.

* Aprovechar herramientas como validadores, control de errores, seguridad y configuración externa, que permiten enfocar el desarrollo en la lógica del negocio.

En el diagrama de despliegue, el contenedor del backend representa este núcleo funcional del sistema, separado del frontend y la base de datos, facilitando su comprensión y mantenimiento independiente.
#### Base de datos: PostgreSQL
Se eligió PostgreSQL como sistema de gestión de base de datos relacional (RDBMS) por su solidez, compatibilidad con estándares SQL y extensiones avanzadas. Estas serviran en la arquitectura para:

* Manejar relaciones complejas y garantizar integridad referencial entre las entidades del sistema.

* Ejecutar consultas eficientes gracias a índices, funciones agregadas y soporte para procedimientos almacenados.

* Integración eficiente con librerías de persistencia como JPA/Hibernate, lo cual facilita el mapeo de entidades desde PostgreSQL.

* Ofrecer una solución escalable y segura, sin costos de licenciamiento.

PostgreSQL asegura un backend de datos confiable, ideal para los módulos de ventas, pagos, inventario y clientes.

#### Angular (Frontend)
El frontend será desarrollado con Angular, un framework basado en TypeScript que permite organizar la interfaz de usuario en componentes reutilizables, cada uno asociado a una funcionalidad del sistema.

* Permite desarrollar una interfaz modular y reutilizable, organizada por componentes.

* Angular facilita el desarrollo de una interfaz rica y reactiva, que mejora la experiencia del usuario sin comprometer la claridad estructural del sistema.

* Integra mecanismos nativos de consumo de APIs RESTful, facilitando la conexión con el backend de Spring Boot.

* Se integrará una librería de componentes visuales para garantizar consistencia, accesibilidad y eficiencia en el diseño de pantallas.

Gracias a esta división, es fácil comprender cómo las vistas del usuario se comunican con el sistema y cómo cada acción corresponde a un flujo entre componentes documentado gráficamente.

#### Docker (Contenedores)
La utilización de contenedores Docker tanto para el backend como para el frontend:

* Representar de forma visual y clara la arquitectura modular del sistema, como se muestra en el diagrama de despliegue.
*Despliegue estandarizado y portable.

* Facilitar el aislamiento y la independencia de los servicios, lo que mejora la comprensión del entorno de ejecución del sistema.

* Reproducir fácilmente entornos de desarrollo y producción, garantizando coherencia en todas las etapas del ciclo de vida del software.

* Posibilita una mayor integración con herramientas CI/CD en fases futuras del proyecto

La combinación de Spring Boot + PostgreSQL + Angular, empaquetados en contenedores Docker, responde a las necesidades del proyecto IMPORCOMGUA en términos de modularidad, mantenibilidad, escalabilidad y portabilidad. 

## 8. Diagrama Entidad Relación
Se puede observar el diagrama con mas detalle en el siguiente [enlace](https://drive.google.com/file/d/1PPOJJGdaRHBWIRjnqh1tqRXfl900WQL-/view?usp=sharing)
![ER_IMPERCOMGUA](./assets/architecture/ER-DIAGRAM-IMPERCOMGUA-mod1.png)

## 9. Mockups

### 9.1 Clientes

#### 9.1.1 Listar clientes
![ER_IMPERCOMGUA](./assets/mockups/clientes/listar.png)

#### 9.1.2 Agregar clientes
![ER_IMPERCOMGUA](./assets/mockups/clientes/agregar.png)


#### 9.1.3 Editar clientes
![ER_IMPERCOMGUA](./assets/mockups/clientes/editar.png)

#### 9.1.4 Eliminar clientes
![ER_IMPERCOMGUA](./assets/mockups/clientes/eliminar.png)

### 9.2 Empleados

#### 9.2.1 Listar empleados
![ER_IMPERCOMGUA](./assets/mockups/empleados/listar.jpeg)

#### 9.2.2 Agregar empleados
![ER_IMPERCOMGUA](./assets/mockups/empleados/agregar.png)

#### 9.2.3 Editar empleados
![ER_IMPERCOMGUA](./assets/mockups/empleados/editar.png)

#### 9.2.4 Eliminar empleados
![ER_IMPERCOMGUA](./assets/mockups/empleados/eliminar.png)

### 9.3 Productos

#### 9.3.1 Listar productos
![ER_IMPERCOMGUA](./assets/mockups/productos/listar.png)

#### 9.3.2 Agregar productos
![ER_IMPERCOMGUA](./assets/mockups/productos/agregar.png)

#### 9.3.3 Editar productos
![ER_IMPERCOMGUA](./assets/mockups/productos/editar.png)

#### 9.3.4 Eliminar productos
![ER_IMPERCOMGUA](./assets/mockups/productos/eliminar.png)

### 9.4 Inventario

#### 9.4.1 Listar inventario
![ER_IMPERCOMGUA](./assets/mockups/inventario/listar.png)

#### 9.4.2 Agregar inventario
![ER_IMPERCOMGUA](./assets/mockups/inventario/agregar.png)

#### 9.4.3 Editar inventario
![ER_IMPERCOMGUA](./assets/mockups/inventario/editar.png)

#### 9.4.4 Eliminar inventario
![ER_IMPERCOMGUA](./assets/mockups/inventario/eliminar.png)

#### 9.4.5 Stock
![ER_IMPERCOMGUA](./assets/mockups/inventario/stock.jpeg)


### 9.5 Pagos

#### 9.5.1 Registrar
![ER_IMPERCOMGUA](./assets/mockups/pagos/registrar.png)

### 9.6 Salidas

#### 9.6.1 Registrar salida
![ER_IMPERCOMGUA](./assets/mockups/salidas/registrar.png)

#### 9.6.2 Buscar salidas
![ER_IMPERCOMGUA](./assets/mockups/salidas/buscar.png)

### 9.7 Ventas

#### 9.7.1 Listar ventas
![ER_IMPERCOMGUA](./assets/mockups/ventas/listar.png)

#### 9.7.2 Agregar ventas
![ER_IMPERCOMGUA](./assets/mockups/ventas/agregar.png)

#### 9.7.3 Editar ventas
![ER_IMPERCOMGUA](./assets/mockups/ventas/editar.png)

#### 9.7.4 Eliminar ventas
![ER_IMPERCOMGUA](./assets/mockups/ventas/eliminar.png)


## 10. Gestión del Proyecto

Utilizamos [Trello](https://trello.com) para planificar, asignar y seguir el avance de tareas del proyecto. A continuación, algunas capturas:

![Tablero General](./assets/trello/TABLERO-GENERAL.png)

![Dashboard de distribución](./assets/trello/DASHBOARD-TAREAS.png)


### Acceso al Tablero Trello

Puedes ver el tablero completo aquí:  
🔗 [Tablero Trello de IMPORCOMGUA](https://trello.com/invite/b/6847ba5b69dfcd9af1c397e7/ATTIb7e79dd9e84a644089e6ced6e8777d17530D11B0/ayd2v1s2025proyectog9)
