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

## 🗺️ Diseño de la Base de Datos (Modelo Entidad-Relación)

La imagen presenta el **Modelo Entidad-Relación (MER)** del proyecto, donde se definen las tres tablas principales y sus relaciones dentro del esquema `public` de **PostgreSQL**.

<img width="796" height="716" alt="image" src="https://github.com/user-attachments/assets/80bb9baa-6b02-4994-92a9-e06b2de085a6" />

### Tablas y Relaciones Clave

| Tabla | Atributos Principales | Relación con otras Tablas |
| :--- | :--- | :--- |
| **`usuarios`** | `id`, `nombre`, `email`, `contrasena`, `tipo_usuario` (corresponde a los roles: Estudiante o Instructor). | Relación **uno a muchos** (`1:N`) con `cursos` (un usuario es instructor de muchos cursos) y con `inscripciones` (un usuario es estudiante de muchas inscripciones). |
| **`cursos`** | `id`, `titulo`, `descripcion`, `estado`. Incluye la llave foránea `id_instructor`. | Relación **uno a muchos** (`1:N`) con `inscripciones` (un curso tiene muchas inscripciones). |
| **`inscripciones`** | `id`, `estado`, `fecha_inscripcion`. Sirve como tabla de relación *muchos a muchos* e incluye las llaves foráneas `id_curso` e `id_estudiante`. | Relación **muchos a uno** (`N:1`) tanto con la tabla `cursos` como con la tabla `usuarios`. |


---

## 🛠️ Estructura del Proyecto

<img width="514" height="515" alt="image" src="https://github.com/user-attachments/assets/a68cfd04-2a6a-4304-a27b-df155bbdae2b" />

---

La imagen anterior muestra el árbol de directorios principal del proyecto Spring Boot/Gradle, destacando la convención de paquetes (folders) utilizada para organizar el código en capas:


El proyecto sigue una arquitectura común en Spring Boot, estructurada en capas:

---

| Directorio | Descripción |
| :--- | :--- |
| `controller` | Contiene las clases **REST Controllers** (`@RestController`) que manejan las peticiones HTTP (los *endpoints* de la API). |
| `dto` | Contiene los **Data Transfer Objects** (DTOs) usados para enviar y recibir datos a través de los *endpoints*. |
| `exception` | Clases personalizadas para manejar errores y excepciones de la aplicación. |
| `model` | Contiene las clases de las entidades del dominio (`@Entity`), como `Usuario`, `Curso`, etc. |
| `repository` | Interfaces que extienden `JpaRepository` para la comunicación con la base de datos (capa de persistencia). |
| `service` | Contiene la lógica de negocio central de la aplicación. Es la capa intermedia entre el controlador y el repositorio. |
| `resources` | Contiene el archivo de configuración principal (`application.yml` o `application.properties`). |
| `build.gradle` | Archivo de configuración para el sistema de construcción **Gradle**, donde se definen las dependencias. |

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

## Configuraciones Clave

### ⚙️ Configuración de la Base de Datos (`application.yml`)

El archivo `application.yml` centraliza la configuración del entorno, asegurando la comunicación entre la API de Spring Boot y la base de datos **PostgreSQL**,
mostrando el puerto 8080, los datos de la URL, usuario y contraseña (definidos por el usuario), y las propiedades de Hibernate DDL-Auto update.

<img width="589" height="758" alt="image" src="https://github.com/user-attachments/assets/3142f501-fa61-4a44-b2fd-ffd9c87f9825" />

---

### ⚙️ Configuración de Construcción (`build.gradle`)

El archivo `build.gradle` es el script de configuración principal para **Gradle**, el sistema de construcción del proyecto. Define las tecnologías clave y las 
librerías necesarias para compilar, ejecutar y probar la API.

<img width="713" height="759" alt="image" src="https://github.com/user-attachments/assets/bf7618f3-48e7-4ca8-92cf-61b319edeb81" />

<img width="898" height="365" alt="image" src="https://github.com/user-attachments/assets/9ef829b6-420d-481c-a0f5-cc6086d4f796" />

---

| Sección | Descripción |
| :--- | :--- |
| **Plugins** | Define el uso de **Java** y los plugins esenciales de **Spring Boot** e **`io.spring.dependency-management`**. |
| **`java`** | Especifica que el código fuente debe compilarse usando la versión **Java 21**. |
| **Dependencies** | Incluye los *starters* clave de Spring Boot para crear la **API REST** (`spring-boot-starter-web`), la capa de persistencia con **JPA** (`spring-boot-starter-data-jpa`) y el controlador de la base de datos **PostgreSQL**. |
| **Lombok** | Se configura como librería de compilación para reducir el código repetitivo (getters, setters, etc.). |

---

### 🚀 Pruebas y Consumo de la API (Postman)

Para interactuar, probar y consumir los diferentes *endpoints* de esta API (como el registro de usuarios, la creación de cursos o la inscripción), se recomienda utilizar la herramienta **Postman**.

| Característica | Propósito |
| :--- | :--- |
| **URL Base** | El acceso a la API se realiza a través de `http://localhost:8080/` |
| **Métodos** | Se utiliza para enviar peticiones con los métodos HTTP correspondientes: **POST** (crear), **GET** (consultar), **PUT** (actualizar) y **DELETE** (eliminar). |
| **Colección** | Se recomienda crear una colección en Postman para organizar los *endpoints* de Usuarios, Cursos e Inscripciones. |

<img width="454" height="865" alt="image" src="https://github.com/user-attachments/assets/d7880873-0fe3-416e-9c93-383cfcd7e6a5" />


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

**| GT02 Ing. Erick Adiel Trigueros Jerez |**

---

## 🏛️ Información Institucional

| | |
| :--- | :--- |
| **UNIVERSIDAD DE EL SALVADOR** | **INGENIERÍA EN DESARROLLO DE SOFTWARE - 2025** | 

---

