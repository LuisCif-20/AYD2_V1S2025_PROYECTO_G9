# Fase 2 - Documentacion Sistema de Automatización IMPORCOMGUA
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
![CDU_alto_nivel](./assets/cdu/CDU-ALTO-NIVEL-V2.png)

### Primera Descomposición
![CDU_primera_descomposicion](./assets/cdu/CDU-PRIMERA-DESCOMPOSICION-V2.png)

## 2. Casos de Uso Expandidos
### Diagramas
![CDU_expandido-gestion_clientes](./assets/cdu/CDU-GESTION-CLIENTES-V2.png)

![CDU_expandido-gestion_vendedores](./assets/cdu/CDU-GESTION-VENDEDORES-V2.png)

![CDU_expandido-gestion_ventas](./assets/cdu/CDU-GESTION-VENTAS-V2.png)

![CDU_expandido-gestion_bodega](./assets/cdu/CDU-GESTION-BODEGA-V2.png)

![CDU_expandido-control_pagos](./assets/cdu/CDU-CONTROL-PAGOS-V2.png)

### Listado
- **CDU100 - Gestionar Clientes**
- **CDU200 - Gestionar Ventas** 
- **CDU300 - Gestionar Bodega**
- **CDU400 - Gestionar Vendedores**
- **CDU500 - Controlar Pagos**

### Descripciones

| **ID**               | CDU100 - Gestionar Clientes                                                                                              |
|----------------------|--------------------------------------------------------------------------------------------------------------------------|
| **Nombre**           | Gestionar Clientes                                                                                                        |
| **Actores**          | Administrador, Cliente                                                                                                    |
| **Propósito**        | Administrar la información y relaciones con clientes para facilitar procesos comerciales y mejorar la fidelización.      |
| **Resumen**          | Involucra registrar nuevos clientes, actualizar datos existentes, eliminar clientes inactivos y realizar búsquedas eficientes. Permite mantener un portafolio comercial actualizado y confiable para optimizar la gestión de ventas y atención al cliente. |
| **Curso Normal de eventos** | 1. Identificación de cliente nuevo o existente.<br>2. Registro o actualización de datos personales y contacto.<br>3. Validación de integridad y duplicidad.<br>4. Confirmación de alta, edición o eliminación según corresponda.<br>5. Uso de funcionalidades de búsqueda para acceder a la información del cliente. |
| **Cursos alternos**  | 2a. Si los datos están incompletos o inválidos, se solicita corrección.<br>3a. Si el cliente está activo y no debe eliminarse, se bloquea la eliminación. |
| **Prioridad**        | Alta                                                                                                                     |
| **Mejoras**          | 1. Automatizar validaciones y alertas para mantener calidad de datos.<br>2. Integrar análisis para segmentación avanzada y seguimiento comercial. |
| **Otras secciones**  | Registro, Edición, Eliminación y Búsqueda de clientes (RF01-RF04)                                                        |


| **ID**               | CDU200 - Gestionar Ventas                                                                                                 |
|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Nombre**           | Gestionar Ventas                                                                                                          |
| **Actores**          | Administrador, Vendedor, Cliente                                                                                          |
| **Propósito**        | Formalizar, controlar y optimizar el proceso comercial desde la venta hasta la facturación.                               |
| **Resumen**          | Comprende registrar nuevas ventas con datos completos del cliente y productos, anular ventas cuando sea necesario y facilitar la búsqueda de ventas realizadas. Esto garantiza el correcto flujo comercial, financiero y la satisfacción del cliente. |
| **Curso Normal de eventos** | 1. Identificación de oportunidad de venta.<br>2. Registro de productos y cliente.<br>3. Cálculo y registro del pago.<br>4. Emisión de factura y actualización de inventario.<br>5. Consulta y anulación de ventas cuando corresponda.<br>6. Seguimiento y análisis de las transacciones realizadas. |
| **Cursos alternos**  | 3a. Si un pago es rechazado o insuficiente, se gestionan alternativas o cancelación.<br>2a. Si la venta debe anularse, se procesa la reversión y notificación. |
| **Prioridad**        | Alta                                                                                                                     |
| **Mejoras**          | 1. Integrar métodos de pago electrónico para mayor agilidad.<br>2. Implementar seguimiento postventa para mejorar la experiencia. |
| **Otras secciones**  | Registro, Anulación y Búsqueda de ventas (RF11-RF13)                                                                     |


| **ID**               | CDU300 - Gestionar Bodega                                                                                                 |
|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Nombre**           | Gestionar Bodega                                                                                                          |
| **Actores**          | Administrador, Aduana, Proveedor                                                                                          |
| **Propósito**        | Controlar el inventario para asegurar disponibilidad, trazabilidad y eficiencia operativa.                               |
| **Resumen**          | Incluye registrar nuevos productos en inventario, actualizar sus detalles, eliminar productos obsoletos, búsqueda avanzada de productos, registrar fechas de salida y controlar ingresos y stock. Garantiza que el inventario refleje fielmente la realidad operativa y comercial. |
| **Curso Normal de eventos** | 1. Registro y actualización de productos.<br>2. Control del stock mediante entradas y salidas.<br>3. Realización de búsquedas para facilitar la gestión.<br>4. Generación de reportes e indicadores de inventario.<br>5. Manejo de inconsistencias o ajustes cuando se detectan. |
| **Cursos alternos**  | 1a. En caso de información incompleta o inconsistencias, se solicitan correcciones.                                       |
| **Prioridad**        | Alta                                                                                                                     |
| **Mejoras**          | 1. Mejorar automatización en control de inventarios.<br>2. Integrar sistemas aduaneros para agilizar procesos.             |
| **Otras secciones**  | Registro, Edición, Eliminación y Búsqueda de productos; Control de ingresos y salidas (RF05-RF10)                          |


| **ID**               | CDU400 - Gestionar Vendedores                                                                                             |
|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Nombre**           | Gestionar Vendedores                                                                                                       |
| **Actores**          | Administrador, Vendedor                                                                                                    |
| **Propósito**        | Mantener un registro actualizado de vendedores para facilitar la gestión comercial y administrativa.                     |
| **Resumen**          | Incluye registro, actualización, eliminación y búsqueda de vendedores. Permite controlar el desempeño y la información del personal de ventas.                                                              |
| **Curso Normal de eventos** | 1. Identificación y registro de nuevo vendedor.<br>2. Actualización de datos personales y contacto.<br>3. Eliminación de registros obsoletos.<br>4. Uso de herramientas de búsqueda para consulta. |
| **Cursos alternos**  | 2a. Si los datos son incompletos, se solicita corrección.<br>3a. Si el vendedor tiene ventas activas, se bloquea la eliminación. |
| **Prioridad**        | Media                                                                                                                    |
| **Mejoras**          | 1. Implementar seguimiento de desempeño.<br>2. Integrar formación y evaluación continua.                                  |
| **Otras secciones**  | Registro, Edición, Eliminación y Búsqueda de vendedores (RF14-RF17)                                                      |


| **ID**               | CDU500 - Controlar Pagos                                                                                                  |
|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Nombre**           | Controlar Pagos                                                                                                           |
| **Actores**          | Administrador, Cliente                                                                                                    |
| **Propósito**        | Gestionar el registro y control de pagos y abonos para asegurar la correcta liquidación de ventas.                        |
| **Resumen**          | Permite registrar pagos completos o parciales, consultar historiales y gestionar cobros pendientes, garantizando la correcta gestión financiera.                                                |
| **Curso Normal de eventos** | 1. Registro de pagos y abonos.<br>2. Validación y conciliación con ventas.<br>3. Consulta de estado de pagos.<br>4. Gestión de cobros pendientes. |
| **Cursos alternos**  | 2a. Si un pago es rechazado, se notifica para corrección.<br>3a. Si un pago está pendiente, se envían alertas de seguimiento.|
| **Prioridad**        | Alta                                                                                                                     |
| **Mejoras**          | 1. Integrar métodos de pago digitales.<br>2. Automatizar notificaciones y recordatorios.                                   |
| **Otras secciones**  | Registro y Consulta de pagos (RF18)                                                                                       |


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
- **RNF04 - UI Intuitiva**: El usuario debe poder realizar búsquedas de clientes, ventas o productos de forma intuitiva desde cualquier vista relevante.

#### Disponibilidad
- **RNF05 - Alta Disponibilidad**: El sistema deberá garantizar una disponibilidad del 99.99% anual.
- **RNF06 - CI/CD**: La implementación de nuevas funcionalidades no debe requerir la interrupción del servicio activo para los usuarios.

#### Escalabilidad
- **RNF07 - Arquitectura Escalable**: La arquitectura del sistema debe ser modular y permitir la integración de nuevos módulos sin afectar el rendimiento existente.

#### Mantenibilidad
- **RNF08 - Clara Documentación**: El código fuente deberá documentarse adecuadamente, siguiendo estándares de nomenclatura y comentarios estructurados.

### Requisitos de Restricción (Drivers de Restricción)

#### Técnicos
- **RNF09 - Aplicacion Web**: La aplicación debe ser desarrollada como una aplicación web accesible mediante navegadores modernos (Chrome, Firefox, Edge), sin requerir instalación en el dispositivo cliente.
- **RNF10 - Entorno de Despliegue**: La solución debe estar preparada para ser desplegada en cualquier entorno..

#### Operacionales
- **RNF11 - Ventas**: Los procesos de venta deben permitir pagos parciales, y manejar correctamente los saldos pendientes.
- **RNF12 - Eliminación de registros**: No se permite eliminación física de registros por normativas del negocio. Solo debe aplicarse baja lógica o estado "inactivo".
- **RNF13 - Modificacion en Stock**: Los procesos involucrados con el stock de productos en el inventario, deben de ser seguros y correctos, manteniendo uniformidad entre los ingresos y ventas.

#### Gestión de proyecto
- **RNF14 - Desarrollo del sistema**: El sistema debe desarrollarse en 3 fases durante un mes, según cronograma aprobado.
- **RNF15 - Metodología**: Se debe usar metodología ágil con tablero Kanban.

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
![BLOCKS_DIAGRAM_IMPORCOMGUA](./assets/architecture/DIAGRAMA-BLOQUES.png)

## 7. Diagrama de despliegue (Componentes y Distribucion)

### 7.1 Diagrama de Componentes
![diagrama-componentes](./assets/architecture/DIAGRAMA-COMPONENTES.png)

### 7.2 Diagrama de Distribucion
![diagrama-distribucion](./assets/architecture/DIAGRAMA-DISTRIBUCION.png)

### 7.3 Digrama de Despliegue
![diagrama-despliegue](./assets/architecture/DIAGRAMA-DESPLIEGUE.png)

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
![ER_IMPERCOMGUA](./assets/architecture/DIAGRAMA-ER.png)

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
