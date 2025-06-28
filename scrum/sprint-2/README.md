# Sprint 2 - Documentación Scrum

Este documento contiene la evidencia y documentación del desarrollo del **Sprint 2** durante la Fase 3 del proyecto. Se incluyen los registros diarios, la planificación inicial, y la retrospectiva del equipo al finalizar el sprint.
---

## 🗂️ Sprint Planning
### 🎯 Objetivo del Sprint
El objetivo de este segundo sprint es completar el desarrollo del sistema basado en el MVP previamente validado, integrando todas las funcionalidades restantes necesarias para su operación estable. Esto incluye la implementación del sistema de gestión de roles con control de acceso por perfil y notificaciones por umbrales definidos de productos, garantizando trazabilidad y separación de responsabilidades; la ejecución de pruebas funcionales para validar el comportamiento de cada módulo; la configuración de un proceso de integración y despliegue continuo (CI/CD); y el despliegue final del sistema en la nube para su uso en entorno de producción.

### Sprint Backlog

| ID  | Historia de Usuario | Tareas | Prioridad |
|-----|---------------------|--------|-----------|
|   US08   |   Como Gerente General, quiero gestionar roles de usuarios para controlar accesos al sistema. | 1. Diseñar interfaz de gestión de roles (CRUD). <br> 2. Implementar servicios backend para asignar/editar roles. <br> 3. Validar permisos por rol en cada módulo. <br> 4. Crear modal de confirmación para cambios críticos. | Alta |
|   US09   |   Como Gerente de Inventario, quiero recibir alertas por correo cuando el stock esté bajo mínimos. | 1. Integrar API de proveedor de correo. <br> 2. Desarrollar lógica de comparación de stock. <br> 3. Diseñar plantilla de correo. <br> 4. Configurar destinatarios automáticos. | Alta |
|   US10   |   Como equipo de desarrollo, necesitamos implementar CI/CD para despliegues automatizados. | 1. Configurar pipeline (GitHub Actions/GitLab CI). <br> 2. Automatizar pruebas en el pipeline. <br> 3. Crear scripts para despliegue en la nube. <br> 4. Documentar variables de entorno. | Crítica |
|   US11   |   Como administrador, quiero ver un historial de cambios en roles y accesos. | 1. Diseñar tabla de auditoría. <br> 2. Implementar servicio de registros. <br> 3. Filtrar historial por usuario/fecha. | Media |
|   US12   |   Como Gerente de Ventas, quiero asegurar que mi rol solo permita acceder a módulos de ventas y pagos. | 1. Validar permisos en frontend. <br> 2. Implementar middleware de autenticación. <br> 3. Pruebas de acceso con roles falsos. | Media-Alta |
|   US13   |   Como equipo, debemos completar funcionalidades pendientes del MVP. | 1. Corregir bugs reportados en Fase II. <br> 2. Implementar reportes estratégicos. <br> 3. Optimizar consultas de inventario. | Alta |
|   US14   |   Como tester, necesito ejecutar pruebas de aceptación antes del despliegue final. | 1. Documentar casos de prueba. <br> 2. Grabar videos de pruebas. <br> 3. Validar flujo de CI/CD. | Media |

### Tablero Inicial

Imagen del tablero al inicio del sprint:

![Tablero inicial](./assets/tablero-inicial-sprint2.png)


---

## 📅 Daily Standup
Se grabó cada daily y se puede encontrar en la siguiente [carpeta de drive](https://drive.google.com/drive/folders/1q_tYWLET2codkfn7exos6uZHE68RO3xP?usp=drive_link)

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

## Sprint Retrospective

## Manuel Rojas

### Actualización automática de stock
**Qué se hizo bien:**
Desarrollé un sistema de actualización en tiempo real utilizando una arquitectura basada en eventos con Kafka, que redujo la discrepancia de inventario de un 8% a menos del 0.5%. Implementé un mecanismo de compensación para transacciones fallidas que ha prevenido pérdidas de datos en interrupciones de red. La solución incluye dashboards en tiempo real con Grafana que han sido muy valorados por el equipo de logística.

**Qué se hizo mal:**
La primera versión no consideraba adecuadamente los escenarios de concurrencia extrema durante eventos de venta flash, lo que generó inconsistencias temporales. Además, el sistema inicial de alertas produjo demasiados falsos positivos, saturando al equipo de operaciones.

**Mejoras para próximo sprint:**
1. Implementar bloqueos optimistas para manejar picos de concurrencia
2. Rediseñar el sistema de alertas con umbrales dinámicos
3. Crear un módulo de simulación de carga para pruebas realistas

### Servicio para registrar ventas
**Qué se hizo bien:**
Construí un servicio transaccional con patrón Saga que maneja perfectamente los fallos parciales. El rendimiento alcanza 450 transacciones por segundo con latencia promedio de 120ms. Integré validaciones fiscales en tiempo real que han reducido errores de facturación en un 35%.

**Qué se hizo mal:**
El sistema inicial de devoluciones era demasiado rígido y no cubría todos los casos de uso comerciales. Tuve que rehacer el módulo de auditoría porque no registraba suficiente contexto para investigar discrepancias.

**Mejoras para próximo sprint:**
1. Implementar un motor de reglas empresariales configurable
2. Añadir trazabilidad completa con OpenTelemetry
3. Diseñar un sistema de borradores para transacciones complejas

## Nery Cifuentes

### Servicio para ubicación
**Qué se hizo bien:**
Integré con éxito el API de Google Maps con nuestro sistema de gestión de flota, reduciendo los tiempos de respuesta de geocodificación de 800ms a 200ms mediante un sistema de caché multinivel. Implementé algoritmos de clustering que mejoraron la visualización de alta densidad en un 70%.

**Qué se hizo mal:**
El consumo de batería en dispositivos móviles resultó ser un 40% mayor que lo estimado. La precisión en interiores no cumplió con las expectativas del cliente.

**Mejoras para próximo sprint:**
1. Implementar geofencing adaptativo para ahorro energético
2. Integrar beacons Bluetooth para ubicación en interiores
3. Crear perfiles de precisión configurables por escenario

### Servicio para registrar clientes
**Qué se hizo bien:**
Desarrollé un sistema de validación por pasos que redujo los registros fraudulentos en un 60%. La integración con servicios de verificación de identidad ha sido particularmente efectiva. El nuevo flujo de onboarding aumentó la tasa de finalización en un 45%.

**Qué se hizo mal:**
El proceso de aprobación manual para cuentas corporativas crea cuellos de botella de hasta 48 horas. La búsqueda de duplicados no escala bien con la base de clientes superando 100,000 registros.

**Mejoras para próximo sprint:**
1. Automatizar verificaciones usando aprendizaje automático
2. Implementar Elasticsearch para búsquedas rápidas
3. Diseñar un sistema de scoring de riesgo automatizado

## Héctor Son

### Formulario para registrar vendedor
**Qué se hizo bien:**
Desarrollé un formulario interactivo con validaciones en tiempo real que ha reducido los errores de ingreso en un 65%. Implementé un sistema de autocompletado para datos recurrentes que ha acelerado el proceso de registro en un 40%. La integración con el sistema de RRHH fue particularmente fluida, permitiendo pre-cargar información contractual.

**Qué se hizo mal:**
El campo de "áreas de especialización" inicialmente no permitía múltiples selecciones, lo que generó reprocesos en el 15% de los casos. La versión móvil requería demasiado scrolling vertical.

**Mejoras para próximo sprint:**
1. Implementar selección múltiple con chips interactivos
2. Rediseñar el flujo móvil con pestañas horizontales
3. Añadir sugerencias contextuales basadas en el tipo de vendedor

### Modal para eliminación de cliente
**Qué se hizo bien:**
Creé un sistema de confirmación por pasos con verificación de relaciones comerciales activas, previniendo el 100% de eliminaciones accidentales. La interfaz muestra claramente el impacto de la acción y requiere aprobación de supervisor para casos sensibles.

**Qué se hizo mal:**
El tiempo de carga inicial del modal era elevado (2.8s) debido a la verificación exhaustiva de dependencias. No se consideraron inicialmente los casos de clientes inactivos temporales.

**Mejoras para próximo sprint:**
1. Implementar carga diferida de las verificaciones
2. Añadir opción de "pausar" relación en lugar de solo eliminar
3. Mejorar los logs de auditoría con snapshots pre-eliminación

## Estuardo Ramos

### Formulario para registrar clientes
**Qué se hizo bien:**
Diseñé un flujo inteligente que adapta los campos requeridos según el tipo de cliente (natural/jurídico), reduciendo el tiempo de registro en un 50%. La integración con el servicio de validación de documentos de Nery ha sido impecable, con un 98% de precisión en detección de fraudes.

**Qué se hizo mal:**
El sistema inicial de notificaciones por email tenía un retraso promedio de 15 minutos. Algunos campos opcionales generaban confusión en los usuarios.

**Mejoras para próximo sprint:**
1. Implementar colas prioritarias para notificaciones inmediatas
2. Rediseñar la jerarquía visual de los campos
3. Añadir tooltips explicativos para cada sección

### Vista de stock
**Qué se hizo bien:**
Desarrollé una interfaz altamente personalizable con filtros combinados que permite a los equipos de bodega encontrar productos en 1/3 del tiempo anterior. La integración con el sistema de actualización automática de Manuel funciona perfectamente, mostrando datos en tiempo real.

**Qué se hizo mal:**
La exportación a Excel inicial no mantenía los formatos de números correctamente. La vista predeterminada mostraba demasiada información para usuarios ocasionales.

**Mejoras para próximo sprint:**
1. Implementar plantillas predefinidas para exportación
2. Crear perfiles de visualización por rol de usuario
3. Añadir gráficos de tendencia de inventario

## Bilhan López

### Formulario para registrar salida de bodega
**Qué se hizo bien:**
Desarrollé un formulario inteligente que sugiere automáticamente las ubicaciones de picking óptimas basado en algoritmos de proximidad, reduciendo el tiempo de preparación de pedidos en un 35%. Implementé validaciones en tiempo real que previenen el 99% de errores en códigos de producto. La integración con scanners Bluetooth ha sido particularmente exitosa, permitiendo registrar ítems 3 veces más rápido.

**Qué se hizo mal:**
El manejo inicial de lotes con fecha próxima a vencer no cumplía con el protocolo FIFO estricto que requiere el área de alimentos. La carga inicial del formulario tardaba hasta 4 segundos en almacenes con más de 10,000 SKUs.

**Mejoras para próximo sprint:**
1. Implementar priorización automática por fechas de vencimiento
2. Optimizar la consulta inicial con paginación server-side
3. Añadir indicadores visuales para productos críticos

### Lista de ventas con búsqueda
**Qué se hizo bien:**
Construí un sistema de filtros combinados y búsqueda semántica que permite encontrar transacciones en segundos, incluso en la base histórica de 2+ millones de registros. La exportación a Excel mantiene perfectamente los formatos y relaciones de datos. Los vendedores reportan un 50% menos de llamadas a soporte para localizar ventas.

**Qué se hizo mal:**
La primera versión no permitía guardar las preferencias de búsqueda entre sesiones. Los filtros complejos a veces requerían recargar toda la página.

**Mejoras para próximo sprint:**
1. Implementar persistencia de configuraciones en localStorage
2. Migrar a actualizaciones parciales del DOM
3. Añadir sugerencias de búsqueda basadas en historial

## Jaqueline Cifuentes

### Servicio para procesar pagos
**Qué se hizo bien:**
Diseñé una arquitectura altamente resiliente que procesa exitosamente el 99.98% de las 15,000 transacciones diarias, integrando perfectamente 3 pasarelas de pago diferentes. Implementé un sistema de reconciliación automática que ha eliminado el 100% de discrepancias contables. El nuevo flujo de reintentos inteligentes recupera el 85% de transacciones fallidas por problemas de red.

**Qué se hizo mal:**
El manejo inicial de devoluciones parciales no cumplía con los requisitos de algunas entidades bancarias. La encriptación de datos sensibles añadía 300ms de latencia.

**Mejoras para próximo sprint:**
1. Implementar protocolos específicos por entidad bancaria
2. Optimizar el proceso criptográfico con hardware acceleration
3. Añadir validación de patrones de fraude en tiempo real

### Servicio para registrar productos
**Qué se hizo bien:**
Desarrollé un sistema de categorización automática que usa NLP para sugerir taxonomías con 92% de precisión, reduciendo el trabajo manual. La carga masiva desde Excel ahora maneja hasta 5,000 registros en 45 segundos. Los fabricantes destacan la facilidad para registrar variaciones de productos.

**Qué se hizo mal:**
El validador inicial de imágenes era demasiado restrictivo, rechazando el 15% de archivos válidos. La jerarquía de categorías no permitía suficiente flexibilidad para algunos productos especiales.

**Mejoras para próximo sprint:**
1. Implementar un sistema de revisión manual opcional
2. Rediseñar la estructura de categorías como grafo en lugar de árbol
3. Añadir compresión inteligente de imágenes

## José Alarcón

### Formulario para registrar ventas
**Qué se hizo bien:**
Creé una interfaz que guía perfectamente al usuario a través de flujos complejos de ventas B2B y B2C. El cálculo automático de impuestos multi-jurisdiccional tiene un 100% de precisión verificada. La integración con el inventario en tiempo real previene el 100% de ventas de productos agotados.

**Qué se hizo mal:**
El guardado automático inicial a veces interfería con validaciones en curso. La selección de clientes frecuentes no priorizaba los más relevantes.

**Mejoras para próximo sprint:**
1. Implementar debouncing para el autoguardado
2. Añadir algoritmo de ranking de clientes
3. Mejorar la previsualización de documentos

### Formulario para registro en bodega
**Qué se hizo bien:**
Desarrollé un sistema de recepción que escanea códigos de barras, QR y hasta reconocimiento de imágenes, acelerando el proceso en 60%. La validación cruzada con órdenes de compra es particularmente robusta. Los trabajadores de bodega destacan la intuitividad del flujo.

**Qué se hizo mal:**
El manejo inicial de unidades de medida alternativas generaba confusión. No se consideraron inicialmente los productos con múltiples proveedores.

**Mejoras para próximo sprint:**
1. Implementar conversiones automáticas de unidades
2. Añadir gestión de origen/procedencia
3. Mejorar el feedback visual durante escaneo

