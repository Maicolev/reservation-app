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
   git clone <repository-url>
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

After setting up the project and running `ng serve`, you can open the application in your browser. It will display the available routes:

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
