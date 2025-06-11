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
| **Otras secciones**         |                         |
| **Seccion**                 |  |
|                             |     |


| **ID**                      | CDU15                   |
|-----------------------------|-------------------------|
| **Nombre**                  | Dar de baja a producto  |
| **Actores**                 | Empleado de Bodega      |
| **Propósito**               | Inhabilitar productos que ya no están disponibles o han salido del catálogo comercial. |
| **Resumen**                 | El empleado de bodega puede marcar un producto como inactivo para evitar que se siga utilizando en nuevos registros de ventas o ingresos de inventario, sin eliminar su historial del sistema. |
| **Curso Normal de eventos** | 1. El empleado busca el producto que desea dar de baja. </br> 2. Revisa la información y confirma que el producto no se utiliza activamente. </br> 3. Marca el producto como "Inactivo" o "Dado de baja". </br> 4. El sistema actualiza el estado y restringe su uso en operaciones futuras. |
| **Cursos alternos**         | 1. Si el producto tiene inventario actual disponible, el sistema podría advertir antes de permitir su baja. </br> 2. Si el producto está asociado a una venta en proceso, el sistema impide la baja hasta que finalice.
 |
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
