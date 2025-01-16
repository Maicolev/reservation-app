# Riservifront

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 19.0.7.



# Angular Reservation System

This project is a front-end application built with **Angular 19** that consumes a REST API for managing customers and reservations. The application also uses **Angular Material** for UI components.

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Components](#components)
- [Services](#services)

## Project Description

This Angular project serves as the front-end for a reservation management system. It allows users to:

- Create and manage customers.
- Create, update, delete, and list reservations.

The project interacts with a back-end API that handles the business logic.

## Technologies Used

- **Angular 19**: Front-end framework for building the user interface.
- **Angular Material**: Provides Material Design components for a modern and responsive UI.
- **RxJS**: Used for handling asynchronous data streams and HTTP requests.

## Features

- **Customer Management**:
  - View a list of all customers.
  - Add new customers.
  
- **Reservation Management**:
  - View all reservations.
  - Create new reservations.
  - Update and delete existing reservations.

## Setup Instructions

1. Clone this repository to your local machine:

   ```bash
   git clone <https://github.com/Maicolev/reservation-app.git>
   cd <project-folder>
   ```

2. Install the dependencies:

   ```bash
   npm install
   ```

3. Make sure the back-end API is running on `http://localhost:8080`. Update the API URLs in the services if the back-end API is hosted elsewhere.

4. Start the Angular development server:

   ```bash
   ng serve
   ```

   The app will be accessible at `http://localhost:4200` by default.

## Running the Application

After setting up the project and running `ng serve`, you can open the application in your browser. # Riservifront

Este proyecto fue generado utilizando [Angular CLI](https://github.com/angular/angular-cli) versión 19.0.7.

# Sistema de Reservas con Angular

Este proyecto es una aplicación frontend construida con **Angular 19** que consume una API REST para gestionar clientes y reservaciones. La aplicación también utiliza **Angular Material** para los componentes de la interfaz de usuario.

## Tabla de Contenidos

- [Descripción del Proyecto](#descripcion-del-proyecto)
- [Tecnologías Utilizadas](#tecnologias-utilizadas)
- [Características](#caracteristicas)
- [Instrucciones de Configuración](#instrucciones-de-configuracion)
- [Ejecución de la Aplicación](#ejecucion-de-la-aplicacion)
- [Endpoints de la API](#endpoints-de-la-api)
- [Componentes](#componentes)
- [Servicios](#servicios)

## Descripción del Proyecto

Este proyecto Angular funciona como el frontend de un sistema de gestión de reservas. Permite a los usuarios:

- Crear y gestionar clientes.
- Crear, actualizar, eliminar y listar reservaciones.

El proyecto interactúa con una API de backend que maneja la lógica del negocio.

## Tecnologías Utilizadas

- **Angular 19**: Framework frontend para construir la interfaz de usuario.
- **Angular Material**: Proporciona componentes con diseño Material Design para una interfaz moderna y responsiva.
- **RxJS**: Utilizado para manejar flujos de datos asíncronos y peticiones HTTP.

## Características

- **Gestión de Clientes**:
  - Ver una lista de todos los clientes.
  - Agregar nuevos clientes.

- **Gestión de Reservaciones**:
  - Ver todas las reservaciones.
  - Crear nuevas reservaciones.
  - Actualizar y eliminar reservaciones existentes.

## Instrucciones de Configuración

1. Clonar este repositorio en tu máquina local:

   ```bash
   git clone <repository-url>
   cd <project-folder>
   ```

2. Instalar las dependencias:

   ```bash
   npm install
   ```

3. Asegúrate de que la API de backend esté ejecutándose en `http://localhost:8080`. Actualiza las URLs de los servicios si la API está alojada en otra ubicación.

4. Iniciar el servidor de desarrollo de Angular:

   ```bash
   ng serve
   ```

   La aplicación estará disponible por defecto en `http://localhost:4200`.

## Ejecución de la Aplicación

Después de la configuración, al ejecutar `ng serve`, puedes abrir la aplicación en tu navegador. La aplicación mostrará las siguientes rutas:

- `/customer`: Página para gestionar clientes.
- `/reservation`: Página para crear nuevas reservaciones.
- `/list`: Página para ver, actualizar y eliminar reservaciones.

## Endpoints de la API

Esta aplicación Angular interactúa con los siguientes endpoints de la API de backend:

- **Servicio de Clientes**:
  - `GET /api/customers`: Obtiene todos los clientes.
  - `POST /api/customers`: Crea un nuevo cliente.

- **Servicio de Reservaciones**:
  - `GET /api/reservations`: Obtiene todas las reservaciones.
  - `POST /api/reservations`: Crea una nueva reservación.
  - `PUT /api/reservations/{id}`: Actualiza una reservación existente.
  - `DELETE /api/reservations/{id}`: Elimina una reservación.

## Componentes

La aplicación está dividida en los siguientes componentes:

1. **CustomerComponent**: Muestra y gestiona clientes.
   - Ruta: `/customer`

2. **ReservationComponent**: Gestiona la creación de reservaciones.
   - Ruta: `/reservation`

3. **ListComponent**: Muestra, actualiza y elimina reservaciones.
   - Ruta: `/list`

## Servicios

### `CustomerService`
- **Métodos**:
  - `getAllCustomers()`: Obtiene todos los clientes.
  - `createCustomer(customer: Customer)`: Crea un nuevo cliente.

### `ReservationService`
- **Métodos**:
  - `createReservation(request: ReservationRequest)`: Crea una nueva reservación.
  - `getAllReservations()`: Obtiene todas las reservaciones.
  - `updateReservation(id: number, request: ReservationRequest)`: Actualiza una reservación existente.
  - `deleteReservation(id: number)`: Elimina una reservación.

## Notas

- El método `generateScheduleId()` en `ReservationComponent` se utiliza para crear un ID de horario único basado en la fecha y hora actual.
- La aplicación asume que la API de backend se ejecuta localmente en `http://localhost:8080`.

## Servidor de Desarrollo

Para iniciar un servidor de desarrollo local, ejecuta:

```bash
ng serve
```

Una vez que el servidor esté en ejecución, abre tu navegador y navega a `http://localhost:4200/`. La aplicación se recargará automáticamente cuando modifiques los archivos fuente.

## Generación de Código

Angular CLI incluye herramientas poderosas para la generación de código. Para crear un nuevo componente, ejecuta:

```bash
ng generate component nombre-componente
```

Para ver una lista completa de los esquemas disponibles (como `components`, `directives`, o `pipes`), ejecuta:

```bash
ng generate --help
```

## Compilación

Para compilar el proyecto, ejecuta:

```bash
ng build
```

Esto compilará tu proyecto y almacenará los archivos en el directorio `dist/`. La compilación de producción optimizará la aplicación para rendimiento y velocidad.

## Ejecutar Pruebas Unitarias

Para ejecutar las pruebas unitarias con el framework [Karma](https://karma-runner.github.io):

```bash
ng test
```

## Ejecutar Pruebas End-to-End

Para ejecutar pruebas end-to-end (e2e):

```bash
ng e2e
```

Angular CLI no incluye un framework de pruebas end-to-end por defecto. Puedes elegir uno según tus necesidades.

## Recursos Adicionales

Para más información sobre Angular CLI y referencias de comandos detallados, visita la página oficial de [Angular CLI](https://angular.dev/tools/cli).

## Licencia

Maicol Orejuela.

It will display the available routes:

- `/customer`: A page to manage customers.
- `/reservation`: A page to create new reservations.
- `/list`: A page to view, update, and delete reservations.

## API Endpoints

This Angular app interacts with the following back-end API endpoints:

- **Customer Service**:
  - `GET /api/customers`: Fetches all customers.
  - `POST /api/customers`: Creates a new customer.

- **Reservation Service**:
  - `GET /api/reservations`: Fetches all reservations.
  - `POST /api/reservations`: Creates a new reservation.
  - `PUT /api/reservations/{id}`: Updates an existing reservation.
  - `DELETE /api/reservations/{id}`: Deletes a reservation.

## Components

The app is divided into the following components:

1. **CustomerComponent**: Displays and manages customers.
   - Routes: `/customer`

2. **ReservationComponent**: Manages reservation creation.
   - Routes: `/reservation`

3. **ListComponent**: Displays, updates, and deletes reservations.
   - Routes: `/list`

## Services

### `CustomerService`
- **Methods**:
  - `getAllCustomers()`: Fetches all customers.
  - `createCustomer(customer: Customer)`: Creates a new customer.

### `ReservationService`
- **Methods**:
  - `createReservation(request: ReservationRequest)`: Creates a new reservation.
  - `getAllReservations()`: Fetches all reservations.
  - `updateReservation(id: number, request: ReservationRequest)`: Updates an existing reservation.
  - `deleteReservation(id: number)`: Deletes a reservation.

## Notes

- The `generateScheduleId()` method in `ReservationComponent` is used to create a unique schedule ID based on the current date and time.
- The app assumes the back-end API is running locally at `http://localhost:8080`.


## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Karma](https://karma-runner.github.io) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.



## License

Maicol Orejuela.
