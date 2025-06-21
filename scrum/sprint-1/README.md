# Sprint 1 - Documentación Scrum

Este documento contiene la evidencia y documentación del desarrollo del **Sprint 1** durante la Fase 2 del proyecto. Se incluyen los registros diarios, la planificación inicial, y la retrospectiva del equipo al finalizar el sprint.
---

## 🗂️ Sprint Planning
### 🎯 Objetivo del Sprint
Tener un Producto Mínimo Viable (MVP) del sistema de automatización para IMPORCOMGUA, que incluya las funcionalidades esenciales (drivers funcionales) identificadas en conjunto con el cliente. Estas funcionalidades permitirán gestionar el inventario, las ventas, los pagos y el mantenimiento de clientes, productos y vendedores.

### Sprint Backlog

| ID | Historia de Usuario | Tareas | Prioridad	|
|	:---:	|	-----	|	-----	| :------: 	|
|	US01	|	Como administrador quiero registrar ventas para llevar control diario               	|	1.	Diseñar formulario para ventas con campos de cliente, productos, cantidades, total.<br>2.	Crear servicio backend para registrar la venta (incluyendo lógica de stock).<br>3.	Permitir búsqueda de ventas por cliente, fecha o estado.<br>4.	Permitir anulación de ventas con justificación (modal + servicio).<br>5.	Mostrar historial de ventas con filtros.	|	 Alta      	|
|	US02	|	Como administrador deseo registrar el ingreso de productos al inventario            	|	1.	Crear interfaz para registrar nuevos ingresos a inventario.<br>2.	Validar existencia de producto y cantidad ingresada.<br>3.	Actualizar automáticamente el stock al registrar el ingreso.<br>4.	Mostrar confirmación y registro histórico de ingresos. "	|	 Alta     	|
|	US03	|	Como administrador quiero registrar salidas de inventario para mantener trazabilidad	|	1.	Diseñar formulario con producto, cantidad y motivo de salida.<br>2.	Validar que la cantidad no exceda el stock actual.<br>3.	Registrar salida en el sistema con fecha y usuario.<br>4.	Mostrar historial de salidas por producto."	|	 Media-Alta    	|
|	US04	|	Como cajero deseo registrar pagos de clientes                                       	|	1.	Crear servicio para registrar pagos asociados a una venta.<br>2.	Validar monto ingresado y actualizar estado de la venta.<br>3.	Permitir ver historial de pagos de un cliente.	|	 Media-Alta    	|
|	US05	|	 Como administrador deseo gestionar clientes                                         	|	1.	Formularios para crear, editar y eliminar clientes.<br>2.	Servicios backend para registrar, editar, eliminar y buscar clientes.<br>3.	Vista que permita listar clientes con filtros por nombre, correo, estado.<br>4.	Confirmaciones visuales (modales) para eliminar clientes.	|	 Alta     	|
|	US06	|	 Como administrador deseo gestionar productos                                        	|	1.	Formularios para crear y editar productos con validaciones.<br>2.	Vista con búsqueda de productos por nombre o categoría.<br>3.	Servicios backend para registrar, buscar, editar y eliminar productos.<br>4.	Confirmación para eliminación de productos mediante modal.	|	 Alta     	|
|	US07	|	 Como administrador deseo gestionar vendedores                                       	|	1.	Formularios para añadir y editar vendedores.<br>2.	Vista de lista de vendedores con búsqueda por nombre o estado.<br>3.	Modal de confirmación para dar de baja (eliminación lógica).<br>4.	Servicio backend para búsquedas filtradas.	|	 Media-Alta    	|



### Tablero Inicial

Imagen del tablero al inicio del sprint:

![Tablero inicial](../../docs/assets/trello/TABLERO-GENERAL.png)


---

## 📅 Daily Standup
Se grabó cada daily y se puede encontrar en la siguiente [carpeta de drive](https://drive.google.com/drive/folders/1cfmjz10v609Wzfselk9M3RHIlcwegJWg?usp=sharing)

Ademas, cada integrante del equipo documentó diariamente lo siguiente:

- ✅ Qué hizo el día anterior
- 🔜 Qué planea hacer el día actual
- ⚠️ Si tiene algún impedimento

Los registros diarios se encuentran en la carpeta [`daily-transcripts/`](./daily-transcripts), organizados por miembro del equipo:

1. [`Manuel Rojas`](./daily-transcripts/manuel-rojas.md)
2. [`Luis Cifuentes`](./daily-transcripts/luis-cifuentes.md)
3. [`Bilhán López`](./daily-transcripts/bilhan-lopez.md)
4. [`José Alarcón`](./daily-transcripts/jose-alarcon.md)
5. [`Jaqueline Cifuentes`](./daily-transcripts/jaqueline-cifuentes.md)
6. [`Hector Son`](./daily-transcripts/hector-son.md)
7. [`Estuardo Ramos`](./daily-transcripts/estuardo-ramos.md)

---

### Sprint Retrospective

![Backlog-Final](../assets/Backlog-Final-Spring-1.png)

### Manuel Rojas
***¿Qué se hizo bien durante el Sprint?***
Hubo una constante comunicacion con los compañeros que dependian de funcionalidades de otras para no quedarse estancados mucho tiempo.

***¿Qué se hizo mal durante el Sprint?***
Hubieron tareas que no se estimaron de manera correcta.

***¿Qué mejoras se deben implementar para el próximo sprint?***
Una mejor estimacion para las tareas y mejor planificacion

### Luis Nery Cifuentes Rodas
***¿Qué se hizo bien durante el Sprint?***
Se trabajo de forma organizada cada tarea.
***¿Qué se hizo mal durante el Sprint?***
No tengo ninguna objecion.
***¿Qué mejoras se deben implementar para el próximo sprint?***
Para mi nada, esta bien asi como lo estamos trabajando.

### Bilhán López
***¿Qué se hizo bien durante el Sprint?***
Asignación de actividades
***¿Qué se hizo mal durante el Sprint?***
Se inicio tarde el proyecto
***¿Qué mejoras se deben implementar para el próximo sprint?***
Iniciar lo mas pronto posible

### Hector Son
***¿Qué se hizo bien durante el Sprint?***
Se logró avanzar con éxito en la implementación de varios casos de uso clave como el registro de clientes, productos y empleados. También se cumplió con la organización del proyecto en Trello.

***¿Qué se hizo mal durante el Sprint?***
Hubo dificultades al integrar algunas partes del backend con el frontend, principalmente por falta de definición clara en los contratos de las APIs. También se detectaron retrasos por no estimar correctamente la complejidad de ciertas tareas técnicas, como el control de inventario.

***¿Qué mejoras se deben implementar para el próximo sprint?***
- Establecer reuniones de sincronización técnica al inicio y a mitad del sprint.
- Definir con anticipación los endpoints y esquemas de datos para evitar bloqueos entre frontend y backend.

### Estuardo Ramos
***¿Qué se hizo bien durante el Sprint?***
La distribución de cada tarea se hizo de una buena forma clara y ordenada
***¿Qué se hizo mal durante el Sprint?***
Nada, todo bien
***¿Qué mejoras se deben implementar para el próximo sprint?***
Mejorar un poco la comunicación


### Jaqueline Cifuentes
***¿Qué se hizo bien durante el Sprint?***
La asignación de las tareas se asignó de acuerdo a las habilidades de los miembros el equipo y hubo una buena comunicación.
***¿Qué se hizo mal durante el Sprint?***
No considero que se haya hecho mal algo durante el sprint.
***¿Qué mejoras se deben implementar para el próximo sprint?***
Por el momento no veo necesarias alguna mejora.

### José Alarcón
***¿Qué se hizo bien durante el Sprint?***
El equipo logró mantener un ritmo constante de trabajo, lo cual permitió avanzar de forma equilibrada en varias funcionalidades clave. También destaco la buena disposición de todos para ayudar cuando alguien se encontraba bloqueado.

***¿Qué se hizo mal durante el Sprint?***
Algunas decisiones técnicas se tomaron sobre la marcha sin una revisión conjunta, lo que generó ligeros retrabajos en la integración de algunos módulos.

***¿Qué mejoras se deben implementar para el próximo sprint?***
Sería ideal realizar sesiones breves de revisión técnica antes de comenzar las tareas más complejas y dejar asentadas convenciones claras para el código compartido, como el formato de respuestas de API y nombres de endpoints.



### Estado Final del Sprint Backlog

| ID   | Historia de Usuario                                                                  | Prioridad  | Estado       |
| ---- | ------------------------------------------------------------------------------------ | :--------: | :----------: |
| US01 | Como administrador quiero registrar ventas para llevar control diario                | Alta       | ✅ Completado |
| US02 | Como administrador deseo registrar el ingreso de productos al inventario             | Alta       | ✅ Completado |
| US03 | Como administrador quiero registrar salidas de inventario para mantener trazabilidad | Media-Alta | ✅ Completado |
| US04 | Como cajero deseo registrar pagos de clientes                                        | Media-Alta | ✅ Completado |
| US05 | Como administrador deseo gestionar clientes                                          | Alta       | ✅ Completado |
| US06 | Como administrador deseo gestionar productos                                         | Alta       | ✅ Completado |
| US07 | Como administrador deseo gestionar vendedores                                        | Media-Alta | ✅ Completado |

El proceso de validación y testeo quedó incompleto debido a que el tiempo designado para todas las tareas no fue suficiente.
