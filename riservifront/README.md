# Riservifront

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
   git clone <https://github.com/Maicolev/reservation-app.git>
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

