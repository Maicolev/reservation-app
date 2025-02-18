# Aplicación de Gestión de Reservas

Este es un servicio de gestión de reservas que permite a los usuarios crear, consultar y actualizar reservas en un sistema de restaurante. La aplicación está construida (esta y seguirá en construcción mejor dicho 😄 ) usando **Spring Boot** y **MySQL** como base de datos, y está diseñada para interactuar con un frontend en **Angular**.

## Endpoints de la API

### **1. Crear una reserva**
Para crear una nueva reserva, se debe enviar una solicitud POST al siguiente endpoint:

```
POST http://localhost:8080/api/reservations
```

### **Cuerpo de la solicitud (JSON)**

```json
{
  "reservation": {
    "customer": {
      "id": 111234567,
      "firstName": "Rodolfo",
      "lastName": "Perez",
      "email": "r.peredssz@example.com",
      "phoneNumber": "+573201234567",
      "customerType": {
        "id": 1
      }
    },
    "schedule": {
      "id": "1601202520",
      "scheduleDate": "2024-01-12",
      "startTime": "20:00",
      "endTime": "21:00",
      "isAvailable": true
    },
    "reservationType": {
      "id": 1
    },
    "reservationDate": "2024-01-12",
    "numberOfPeople": 4,
    "discountApplied": 0
  }
}
```

### **Respuesta esperada (JSON)**

```json
{
  "message": "Reserva creada exitosamente",
  "reservationId": 1
}
```

---

### **2. Consultar todas las reservas**
Para consultar todas las reservas existentes, envía una solicitud GET al siguiente endpoint:

```
GET http://localhost:8080/api/reservations
```

### **Respuesta esperada (JSON)**

```json
[
  {
    "id": 1,
    "customer": {
      "id": 111234567,
      "firstName": "Rodolfo",
      "lastName": "Perez",
      "email": "r.peredssz@example.com",
      "phoneNumber": "+573201234567",
      "customerType": {
        "id": 1
      }
    },
    "schedule": {
      "id": "1601202520",
      "scheduleDate": "2024-01-12",
      "startTime": "20:00",
      "endTime": "21:00",
      "isAvailable": true
    },
    "reservationType": {
      "id": 1
    },
    "reservationDate": "2024-01-12",
    "numberOfPeople": 4,
    "discountApplied": 0
  }
]
```

---

### **3. Actualizar una reserva**
Para actualizar una reserva existente, envía una solicitud PUT al siguiente endpoint:

```
PUT http://localhost:8080/api/reservations/{id}
```

**Ejemplo de URL con ID de reserva:**

```
PUT http://localhost:8080/api/reservations/1
```

### **Cuerpo de la solicitud (JSON)**

```json
{
  "reservation": {
    "customer": {
      "id": 111234567,
      "firstName": "Rodolgo",
      "lastName": "Perez",
      "email": "r.peredssz@example.com",
      "phoneNumber": "+573201234567",
      "customerType": {
        "id": 1
      }
    },
    "schedule": {
      "id": "1601202520",
      "scheduleDate": "2024-01-12",
      "startTime": "20:00",
      "endTime": "21:00",
      "isAvailable": false
    },
    "reservationType": {
      "id": 1
    },
    "reservationDate": "2024-01-12",
    "numberOfPeople": 4,
    "discountApplied": 10
  }
}
```

### **Respuesta esperada (JSON)**

```json
{
  "message": "Reserva actualizada exitosamente"
}
```

---

### **4. Crear un cliente**
Para registrar un nuevo cliente, se debe enviar una solicitud POST al siguiente endpoint:

```
POST http://localhost:8080/api/customers
```

### **Cuerpo de la solicitud (JSON)**

```json
{
  "id": "123456",
  "firstName": "Camila",
  "lastName": "Bolaños",
  "email": "cami.boe@example.com",
  "phoneNumber": "6666",
  "customerType": {
    "id": 1
  }
}
```

### **Respuesta esperada (JSON)**

```json
{
  "id": "123456",
  "firstName": "Camila",
  "lastName": "Bolaños",
  "email": "cami.boe@example.com",
  "phoneNumber": "6666",
  "customerType": {
    "id": 1
  }
}
```

---

### **5. Consultar todos los clientes**
Para consultar todos los clientes registrados, envía una solicitud GET al siguiente endpoint:

```
GET http://localhost:8080/api/customers
```

### **Respuesta esperada (JSON)**

```json
[
  {
    "id": "123456",
    "firstName": "Camila",
    "lastName": "Bolaños",
    "email": "cami.boe@example.com",
    "phoneNumber": "6666",
    "customerType": {
      "id": 1
    }
  },
  {
    "id": "7891011",
    "firstName": "Rodolfo",
    "lastName": "Perez",
    "email": "rodolfo@example.com",
    "phoneNumber": "5555",
    "customerType": {
      "id": 2
    }
  }
]
```

---

## Configuración de la base de datos

A continuación se muestra la configuración de la base de datos para conectarse a MySQL en el archivo `application.yml` de Spring Boot:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/RISERVI
    username: newuser
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
    database-platform: org.hibernate.dialect.MySQLDialect
```

### **Detalles de la base de datos:**

- **URL**: jdbc:mysql://localhost:3306/RISERVI
- **Usuario**: newuser
- **Contraseña**: password
- **Driver**: com.mysql.cj.jdbc.Driver
- **Dialect**: org.hibernate.dialect.MySQLDialect
- **Modo de Hibernate**: update

---

## Dependencias del Proyecto

- **Spring Boot**: Para la gestión del proyecto y la creación del servicio RESTful.
- **MySQL**: Para almacenar las reservas y la información relacionada.
- **JPA**: Para la persistencia de datos en la base de datos.
- **Hibernate**: Como el proveedor de JPA.
- **Lombok**: Para simplificar la creación de modelos (entidades) y otros objetos.
- **Gradle**: Para gestionar paquetes.

---

## Instrucciones de ejecución

1. Clona el repositorio en tu máquina local.
2. Configura tu base de datos MySQL.
   - 2.1. Ejecutar script de base de datos para creación de tablas, índices y procedimientos almacenados, entre otros elementos necesarios.
3. Modifica el archivo `application.yml` con los detalles correctos de tu base de datos.
4. Ejecuta la aplicación con `gradle build`.
5. Prueba la API con una herramienta como Postman, Thunderclient para VScode o cURL.

---

## Posibles errores

Habilitar el annotation processing en intellij o se puede quitar el Lombok del desarrollo si presenta inconvenientes por bug o cambio de configuración en intellij, eliminar los annotationProcessor y librerías de lombok en el Build.gradle

---
## License

Maicol Orejuela.
