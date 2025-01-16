
# ReservaApp: Backend y Frontend

Este proyecto abarca tanto el desarrollo del backend como del frontend de un sistema de gestión de reservas. El backend se ha construido utilizando **Spring Boot** y **MySQL**, mientras que el frontend está desarrollado con **Angular 19** y **Angular Material**.

## Resumen de Proyectos

* **Riservifront:**
  * **Frontend** desarrollado en **Angular 19**.
  * Permite la interacción con el backend a través de una interfaz de usuario intuitiva.
  * Utiliza **Angular Material** para una experiencia de usuario moderna y adaptable.
* **Aplicación de Gestión de Reservas:**
  * **Backend** desarrollado en **Spring Boot**.
  * Proporciona una **API RESTful** para gestionar las reservas.
  * Utiliza **MySQL** como base de datos para almacenar la información de clientes y reservas.

## Estructura del Proyecto

* **Frontend (Angular):**
  * Gestión de clientes.
  * Gestión de reservas.
  * Interacción con el backend a través de una API REST.
* **Backend (Spring Boot):**
  * API RESTful para gestionar reservas.
  * Conexión a base de datos MySQL.
  * Controladores y servicios para manejar las operaciones CRUD.

## Repositorios
* **Frontend:** Riservifront
* **Backend:** Aplicación de Gestión de Reservas

## Configuración y Ejecución

### Frontend
1. Clona el repositorio y navega a la carpeta del proyecto.
2. Instala las dependencias: `npm install`
3. Ejecuta el servidor: `ng serve`
   * Accede a la aplicación en http://localhost:4200

### Backend
1. Clona el repositorio y configura la base de datos MySQL.
2. Modifica el archivo `application.yml` con tus credenciales de base de datos.
3. Ejecuta el servicio: `gradle build`
   * Accede a la aplicación en http://localhost:8080
   * Prueba los endpoints con una herramienta como Postman Thunderclient o por curl.

## Este archivo README proporciona una visión general del proyecto y guía a los desarrolladores en la configuración y ejecución de la aplicación.

By Maicol Orejuela
