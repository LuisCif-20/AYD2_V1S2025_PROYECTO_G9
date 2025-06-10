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

### Listado
- **CDU01**: Registrar cliente  
- **CDU02**: Actualizar información del cliente  
- **CDU03**: Dar de baja a cliente  
- **CDU04**: Efectuar venta  
- **CDU05**: Aplicar descuento  
- **CDU06**: Modificar cantidades en inventario  
- **CDU07**: Anular venta  
- **CDU08**: Encontrar venta  
- **CDU09**: Realizar pago  
- **CDU10**: Registrar empleado  
- **CDU11**: Actualizar información del empleado  
- **CDU12**: Dar de baja a empleado  
- **CDU13**: Registrar producto  
- **CDU14**: Actualizar información del producto  
- **CDU15**: Dar de baja a producto  
- **CDU16**: Registrar ingreso a bodega  
- **CDU17**: Registrar salida de bodega  

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


## 3. Drivers arquitectónicos
### Requerimientos funcionales críticos (RF)

#### Gestión de Clientes
* **RF01**: El sistema debe permitir registrar un nuevo cliente con sus datos personales.
* **RF02**: El sistema debe permitir modificar los datos de un cliente existente.
* **RF03**: El sistema debe permitir eliminar un cliente registrado.
* **RF04**: El sistema debe permitir buscar clientes por distintos criterios.

#### Gestión de Bodega
* **RF05**: El sistema debe permitir registrar un nuevo producto en el inventario.
* **RF06**: El sistema debe permitir modificar los detalles de un producto existente.
* **RF07**: El sistema debe permitir eliminar productos del inventario.
* **RF08**: El sistema debe permitir buscar productos por diferentes criterios.
* **RF09**: El sistema debe permitir registrar una nueva venta con los datos del cliente y productos vendidos.
* **RF10**: El sistema debe permitir registrar la salida de productos por ventas u otros motivos.
* **RF11**: El sistema debe permitir registrar el ingreso de productos al inventario.
* **RF12**: El sistema debe permitir realizar ajustes manuales al inventario registrado.

#### Gestión de Ventas
* **RF13**: El sistema debe permitir anular ventas realizadas anteriormente.
* **RF14**: El sistema debe permitir buscar ventas realizadas por diferentes parámetros.
* **RF15**: El sistema debe permitir aplicar descuentos en las ventas según condiciones definidas.

#### Gestión de Empleados
* **RF16**: El sistema debe permitir registrar nuevos empleados con sus datos.
* **RF17**: El sistema debe permitir modificar los datos de un empleado existente.
* **RF18**: El sistema debe permitir eliminar empleados registrados.
* **RF19**: El sistema debe permitir buscar empleados por diferentes criterios.

### Requisitos No Funcionales (RNF)

#### Seguridad
- **RNF01**: El sistema requiere autenticación para acceder a funcionalidades de administración como la gestión de clientes, productos, ventas e inventario.
- **RNF02**: Las contraseñas de los usuarios se almacenarán cifradas utilizando el algoritmo AES.
- **RNF03**: Las sesiones de usuario tendrán una duración máxima de 24 horas, después de lo cual se cerrarán automáticamente.
- **RNF04**: Solo los usuarios autenticados y autorizados podrán registrar ventas, cobros, salidas de bodega o realizar pagos.

#### Eficiencia
- **RNF05**: Las operaciones críticas como registro de ventas, pagos y búsquedas de envíos deberán completarse en menos de 3 segundos.
- **RNF06**: El sistema debe soportar al menos 10,000 transacciones simultáneas sin degradar el rendimiento, especialmente en los módulos de inventario y ventas.

#### Usabilidad
- **RNF07**: La interfaz adaptarse correctamente a dispositivos de escritorios.
- **RNF08**: Las opciones del menú deben estar claramente categorizadas y organizadas según los roles de usuario (gerente, supervisor, vendedor).
- **RNF09**: El usuario debe poder realizar búsquedas de clientes, ventas o productos de forma intuitiva desde cualquier vista relevante.

#### Disponibilidad
- **RNF10**: El sistema deberá garantizar una disponibilidad del 99.99% anual.
- **RNF11**: La implementación de nuevas funcionalidades no debe requerir la interrupción del servicio activo para los usuarios.

#### Escalabilidad
- **RNF12**: La arquitectura del sistema debe ser modular y permitir la integración de nuevos módulos sin afectar el rendimiento existente.
- **RNF13**: Debe ser posible escalar vertical u horizontalmente la infraestructura en la nube para soportar el crecimiento del negocio.

#### Mantenibilidad
- **RNF14**: El código fuente deberá documentarse adecuadamente, siguiendo estándares de nomenclatura y comentarios estructurados.
- **RNF15**: El sistema debe permitir actualizaciones sin afectar la integridad de los datos ni requerir reinstalación total del software.

#### Portabilidad
- **RNF16**: El sistema debe poder ejecutarse en distintos navegadores web modernos (Chrome, Firefox, Edge).
- **RNF17**: La solución debe estar preparada para ser desplegada tanto en ambientes Linux.

#### Trazabilidad
- **RNF18**: Cada requerimiento funcional debe estar relacionado con uno o más casos de uso (CDU) y ser rastreable en una matriz de trazabilidad.
- **RNF19**: Cada modificación en el sistema debe quedar registrada con fecha, autor y motivo en un historial de cambios accesible al equipo.

## 4. Matrices de trazabilidad
### Stakeholders vs Requerimientos
| Stakeholder\Requerimientos       | RF01 | RF02 | RF03 | RF04 | RF05 | RF06 | RF07 | RF08 | RF09 | RF10 | RF11 | RF12 | RF13 | RF14 | RF15 | RF16 | RF17 | RF18 | RF19 |
|-------------------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|------|
| Gerente General   |  X   |  X   |  X   |      |      |      |      |      |  X   |      |      |  X   |  X   |  X   |  X   |      |      |      |      |
| Ventas            |  X   |  X   |  X   |  X   |      |      |      |  X   |  X   |  X   |      |      |  X   |  X   |  X   |      |      |      |      |
| Bodega            |      |      |      |      |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |      |      |      |      |      |
| Finanzas          |      |      |      |      |      |      |      |      |  X   |      |      |      |  X   |  X   |  X   |      |      |      |      |
| Administración    |      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |  X   |  X   |  X   |  X   |

### Stakeholders vs CDU
| Stakeholder\CDU       | CDU01 | CDU02 | CDU03 | CDU04 | CDU05 | CDU06 | CDU07 | CDU08 | CDU09 | CDU10 | CDU11 | CDU12 | CDU13 | CDU14 | CDU15 | CDU16 | CDU17 |
|-------------------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| Gerente General   |   X   |   X   |   X   |   X   |   X   |   X   |   X   |   X   |   X   |       |       |       |       |       |       |       |       |
| Ventas            |   X   |   X   |   X   |   X   |   X   |       |   X   |   X   |       |       |       |       |       |       |       |       |   X   |
| Bodega            |       |       |       |   X   |       |   X   |   X   |       |       |       |       |       |   X   |   X   |   X   |   X   |   X   |
| Finanzas          |       |       |       |   X   |   X   |       |   X   |   X   |   X   |       |       |       |       |       |       |       |       |
| Administración    |       |       |       |       |       |       |       |       |       |   X   |   X   |   X   |       |       |       |       |       |

### Requerimientos vs CDU
| Requerimiento\CDU | CDU01 | CDU02 | CDU03 | CDU04 | CDU05 | CDU06 | CDU07 | CDU08 | CDU09 | CDU10 | CDU11 | CDU12 | CDU13 | CDU14 | CDU15 | CDU16 | CDU17 |
|---------------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| RF01 - Registrar cliente             |   X   |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |
| RF02 - Modificar cliente             |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |
| RF03 - Eliminar cliente              |       |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |       |       |
| RF04 - Buscar cliente                |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |
| RF05 - Registrar producto            |       |       |       |       |       |       |       |       |       |       |       |       |   X   |       |       |       |       |
| RF06 - Modificar producto            |       |       |       |       |       |       |       |       |       |       |       |       |       |   X   |       |       |       |
| RF07 - Eliminar producto             |       |       |       |       |       |       |       |       |       |       |       |       |       |       |   X   |       |       |
| RF08 - Buscar producto               |       |       |       |       |       |       |       |       |       |       |       |       |   X   |   X   |       |       |       |
| RF09 - Registrar venta               |       |       |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |       |
| RF10 - Registrar salida productos    |       |       |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |   X   |
| RF11 - Registrar ingreso productos   |       |       |       |       |       |       |       |       |       |       |       |       |       |       |       |   X   |       |
| RF12 - Ajustes manuales inventario   |       |       |       |       |       |   X   |       |       |       |       |       |       |       |       |       |       |       |
| RF13 - Anular venta                  |       |       |       |       |       |       |   X   |       |       |       |       |       |       |       |       |       |       |
| RF14 - Buscar ventas                 |       |       |       |       |       |       |       |   X   |       |       |       |       |       |       |       |       |       |
| RF15 - Aplicar descuentos            |       |       |       |       |   X   |       |       |       |       |       |       |       |       |       |       |       |       |
| RF16 - Registrar empleados           |       |       |       |       |       |       |       |       |       |   X   |       |       |       |       |       |       |       |
| RF17 - Modificar empleados           |       |       |       |       |       |       |       |       |       |       |   X   |       |       |       |       |       |       |
| RF18 - Eliminar empleados            |       |       |       |       |       |       |       |       |       |       |       |   X   |       |       |       |       |       |
| RF19 - Buscar empleados              |       |       |       |       |       |       |       |       |       |       |   X   |       |       |       |       |       |       |
