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

