# ForoHub API - Primera Versión

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-Security-yellow)

**ForoHub** es una API REST desarrollada con **Spring Boot** como proyecto de aprendizaje. Su objetivo es simular un foro donde los usuarios pueden crear y gestionar **tópicos** (preguntas, discusiones). Esta primera versión se centra en:

- **Gestión de usuarios** (registro, autenticación con JWT).
- **CRUD de tópicos** (crear, listar, detallar, actualizar y eliminar).
- Aplicación de **buenas prácticas** (validaciones, manejo de excepciones, uso de DTOs, paginación, ordenamiento).
- **Seguridad** con Spring Security y tokens JWT.

El proyecto fue desarrollado con fines educativos para comprender el ecosistema Spring, la implementación de estándares REST y la protección de endpoints mediante autenticación.

---

## 🛠️ Tecnologías utilizadas

- **Java 17** * (o superior)
- **Spring Boot 3.2.x**
- **Spring Web** (controladores REST)
- **Spring Data JPA** (persistencia)
- **Spring Security** (autenticación y autorización)
- **JJWT** (generación y validación de tokens JWT)
- **MySQL** (base de datos)
- **Flyway** (migraciones de base de datos)
- **Lombok** (reducción de código boilerplate)
- **Validation** (validaciones con anotaciones)
- **Maven** (gestión de dependencias)

---

## 📋 Requisitos previos

Para ejecutar este proyecto necesitas:

- **JDK 17** o superior.
- **Maven** (o puedes usar el wrapper `./mvnw`).
- **MySQL Server** (versión 8.0 recomendada).
- **Postman** (o cualquier cliente HTTP) para probar los endpoints.

---

## ⚙️ Configuración del entorno

### 1. Clonar el repositorio
```bash
git clone https://github.com/dear-/forohub.git
cd forohub
```
---

### 2. Configurar la base de datos
Crea una base de datos en MySQL, por ejemplo `forohub_db`:
```sql
CREATE DATABASE forohub_db;
```
### 3. Configurar application.properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:8080/forohub_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña

# JWT
api.security.secret=${JWT_SECRET:miClaveSecretaSuperSegura}

Puedes realizar pruebas en Postman o Insomnia, el que prefieras!!!
