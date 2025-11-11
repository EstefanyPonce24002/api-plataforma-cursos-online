# 💻 API para Plataforma de Cursos Online

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API (Interfaz de Programación de Aplicaciones) que servirá como el backend
principal para una plataforma de cursos en línea. La API estará encargada de gestionar los datos y la lógica
de negocio relacionada con los usuarios, cursos e inscripciones.

El proyecto se basa en los principios de la **Programación Orientada a Objetos (POO)** y está estructurado en
torno a las siguientes clases principales:

* **`Usuario`**: La clase base que representa a un usuario genérico con funcionalidades comunes a todos los
  roles, como el registro y la actualización de datos.
* **`Instructor`**: Hereda de `Usuario` y se especializa en la gestión de cursos, permitiendo crear, editar y
  listar cursos, además de ver a los estudiantes inscritos.
* **`Estudiante`**: Hereda de `Usuario` y se enfoca en la interacción con los cursos, facilitando la inscripción,
  anulación de matrícula y listado de cursos en los que está inscrito.
* **`Curso`**: Representa la información de un curso dentro de la plataforma, incluyendo su título, descripción
  y estado.
* **`Inscripción`**: Gestiona la relación entre un estudiante y un curso, registrando el estado de dicha relación.

---

## Endpoints Principales

### 👩‍🎓 Endpoints para Estudiantes

* POST /api/estudiantes → Crear un nuevo estudiante
* GET /api/estudiantes → Listar todos los estudiantes
* GET /api/estudiantes/{id} → Obtener estudiante por ID
* PUT /api/estudiantes/{id} → Actualizar datos de un estudiante
* DELETE /api/estudiantes/{id} → Eliminar estudiante

### 👨‍🏫 Endpoints para Instructores

* POST /api/instructores → Registrar un nuevo instructor
* GET /api/instructores → Listar todos los instructores
* GET /api/instructores/{id} → Obtener instructor por ID
* PUT /api/instructores/{id} → Actualizar datos de un instructor
* DELETE /api/instructores/{id} → Eliminar instructor

### 📚 Cursos
* POST /api/cursos → Crear curso
* GET /api/cursos → Listar cursos
* GET /api/cursos/{id} → Obtener curso por ID
* PUT /api/cursos/{id} → Actualizar curso por ID
* DELETE /api/cursos/{id} → Eliminar curso por ID

### 📝 Inscripciones

* POST /api/inscripciones → Crear inscripción 
* GET /api/inscripciones → Listar inscripciones
* GET /api/inscripciones/{id} → Obtener inscripción por ID
* PUT /api/inscripciones/{id}/cancelar → Cancelar inscripción
* DELETE /api/inscripciones/{id} → Eliminar inscripción por ID

---

## 👥 Integrantes del Equipo

Este proyecto fue desarrollado por las integrantes del **Grupo 5** de la asignatura de Programación Orientada a Objetos:

| Nombre del Integrante                 | Carnet  |
|---------------------------------------|---------|
| Katherine Tatiana Hernández Hernández | HH20017 |
| Irene Guadalupe León Madrid           | LM24048 |
| Ana Estefany Quintanilla de Ponce     | QP24002 |
| Nayeli Saraí Santos Hernández         | SH24001 |

---

## 👥 Tutor Asignado

| Ing. Erick Adiel Trigueros Jerez | 

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** [Java](https://www.oracle.com/java/)
* **Sistema de Construcción:** [Gradle](https://gradle.org/)
* **IDE:** [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* **Base de Datos:** [PostgreSQL](https://www.postgresql.org/)
* **Control de Versiones:** [Git](https://git-scm.com/) & [GitHub](https://github.com/)
* **Librerías:**
  * [Spring Web](https://spring.io/projects/spring-framework)
  * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  * [Lombok](https://projectlombok.org/)
  * [JUnit 5](https://junit.org/junit5/) *(para pruebas unitarias)*
* **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)

