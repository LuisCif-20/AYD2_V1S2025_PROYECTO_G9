# 📦 IMPORCOMGUA – Backend REST API
![Java](https://img.shields.io/badge/Java-21%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)

Este proyecto contiene la API REST para el sistema de gestión de IMPORCOMGUA, desarrollado en **Java 21** usando **Spring Boot**.

## 🚀 Tecnologías principales

- Java 21
- Spring Boot 3.5
- Maven
- PostgreSQL
- Swagger UI

---

## 🛠️ Configuración y ejecución
### Configura las variables de entorno

Copia el archivo `.env.example` y renómbralo como `.env`. Luego edita sus valores según tu entorno de desarrollo:

```bash
cp .env.example .env
```
Utiliza las variables para el proyecto las cuales estan en el siguiente [documento de google](https://docs.google.com/document/d/1Uaa0Ij9dkQ2dBta8Z6N8YTGLyxgoy7ZZ2MWxmEoQbMY/edit?usp=sharing)
> Si no te deja entrar pidele permiso al encargado del backend
### 📝 Variables de entorno por defecto
Si no se crea el archivo `.env`, la aplicación usará los siguientes valores por defecto:

- El servidor se ejecutará en el `puerto 8080`.
- Se intentará conectar a una base de datos PostgreSQL local en la dirección `localhost:5432`.
- El nombre de la base de datos será `imporcomgua`.
- El usuario de la base de datos será `postgres`.
- La contraseña de la base de datos será `postgres`.

> 📌 Asegúrate de tener PostgreSQL instalado y configurado localmente, y que exista una base de datos llamada imporcomgua.

---

### Ejecuta el proyecto en modo desarrollo

Usa Maven para iniciar el servidor:

```bash
mvn spring-boot:run
```

El servidor iniciará en el puerto definido en tu archivo `.env`.

---

## 📚 Documentación de la API

Una vez el servidor esté corriendo, puedes acceder a la documentación Swagger en:

```
http://localhost:3000/api/swagger-ui/index.html
```

> 🔁 El dominio y puerto pueden variar según el entorno o configuración.

------
