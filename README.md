
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
 
* **BD (MYSQL)
  
![Untitled](https://github.com/user-attachments/assets/41960770-2e92-4143-99e2-c84331297250)


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

### BD
1. Ejecutar script de bd para creación de tablas, index y store procedure, entre otros elementos necesarios


## Este archivo README proporciona una visión general del proyecto y guía a los desarrolladores en la configuración y ejecución de la aplicación.

## Decisiones:
 Indices en la base de datos para mejorar los consultas, (Nota: no olvidar correr estadísticas para mejorar que el motor cree su roadmap o diccionario para los indices)

 
 Decidí que los horarios se manejarian insertando con llave único cada vez que se crean
  Ejemplo del formato 1501202511 - esto seria el 15 de enero del 2025 a las 11, el usuario reservara solo en horas en punto y por una hora,
  Así me ahorro tener que buscar cada vez que voy a insertar a ver si no se repita, aunque debería agregar el numero de mesa por ejemplo.
  es una decisión loca y rara pero se me ocurrió probarla y con un poco mas de refinamiento quizás se vea mas interesante, problemas: solo se puede reservar en horario en punto porque si se pone minutos habría que   añadir mas lógica y también añadir horario por mesa o por capacidad de gente, ventajas no tengo que poblar la base de datos con registros no ocupados.

  la lógica decidí manejarla por base de datos, por eso el store procedure que valida si hay horarios disponibles y si no los crea y lo mismo para los clientes, si no existe lo crea de una vez, quería aprovechar para dejar el ms mas limpio ya que el resto del crud que es mas sencillo nos ayuda spring boot sin problema.


## License

Maicol Orejuela.
