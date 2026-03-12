# User Management API

REST API for **user management with authentication using Spring Security and JWT**.

The system allows authenticated users to manage **multiple contacts and multiple addresses**, using **1:N relationships**.

This project was built to practice backend development using **Java and Spring Boot**, applying common patterns used in production APIs.

---

# Technologies

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Bean Validation
* Lombok
* PostgreSQL
* Docker
* Docker Compose

---

# Features

### Authentication

* User registration
* User login with JWT
* Protected endpoints using Spring Security

### User

* Create user
* Authenticate user
* Retrieve authenticated user details

### Contacts

Each user can manage multiple contacts.

* Create contact
* View contact
* Update contact
* Delete contact

### Addresses

Each user can manage multiple addresses.

* Create address
* List addresses
* Update address
* Delete address

---

# Architecture

The project follows a **layered architecture**.

```
src
 ├── business
 │    └── services
 │
 ├── http
 │    ├── controller
 │    └── dtos
 │
 ├── infra
 │    ├── entity
 │    ├── mapper
 │    ├── security
 │    └── errorHandler
```

### Layer responsibilities

**Controller**
Handles HTTP requests and responses.

**Service**
Contains business logic.

**Entity**
Represents database tables.

**Mapper**
Converts DTOs to entities and vice versa.

**Security**
JWT authentication configuration.

**ErrorHandler**
Centralized exception handling.

---

# Entity Relationships

The application is centered around the **User** entity.

```
User
 ├── Contacts (1:N)
 └── Addresses (1:N)
```

Each authenticated user can manage only their own contacts and addresses.

---

# Entities

## User

Represents the system user and implements `UserDetails` for Spring Security authentication.

Fields:

* id
* name
* email
* password

Relationships:

* One user can have multiple addresses
* One user can have multiple contacts

---

## Contact

Represents a phone contact linked to a user.

Fields:

* id
* phone

Relationship:

Many contacts belong to one user.

---

## Address

Represents a user's address.

Fields:

* id
* street
* number
* complement
* city
* neighborhood
* state
* zipCode

Relationship:

Many addresses belong to one user.

---

# Authentication

Authentication is implemented using **JWT (JSON Web Token)**.

Workflow:

1. User registers
2. User logs in
3. The server generates a JWT token
4. The client sends the token in the Authorization header

Example request header:

```
Authorization: Bearer {token}
```

---

# API Endpoints

## User

### Create User

POST /user

Request

```json
{
  "name": "John Doe",
  "email": "john@email.com",
  "password": "123456"
}
```

Response

```
201 Created
```

---

### Login

POST /user/login

Request

```json
{
  "email": "john@email.com",
  "password": "123456"
}
```

Response

```
200 OK
```

Returns a JWT token.

---

### Get Authenticated User

GET /user/my

Header

```
Authorization: Bearer {token}
```

---

# Address Endpoints

### Create Address

POST /address

Request

```json
{
  "street": "Main Street",
  "number": 100,
  "complement": "Apartment 12",
  "city": "São Paulo",
  "neighborhood": "Center",
  "state": "SP",
  "zipCode": "01001-000"
}
```

---

### List Addresses

GET /address

---

### Update Address

PUT /address/{addressId}

---

### Delete Address

DELETE /address/{addressId}

Response

```
204 No Content
```

---

# Contact Endpoints

### Create Contact

POST /contact

Request

```json
{
  "phone": "11999999999"
}
```

---

### Get Contact

GET /contact/{contactId}

---

### Update Contact

PUT /contact/{contactId}

---

### Delete Contact

DELETE /contact/{contactId}

Response

```
204 No Content
```

---

# Validation

The project uses **Bean Validation** for request validation.

Examples include:

* Required fields
* Field format validation
* Custom validation messages

Example response:

```json
[
  {
    "field": "zipCode",
    "message": "Invalid zipCode format"
  }
]
```

---

# Error Handling

Global exception handling is implemented using `@RestControllerAdvice`.

Handled errors include:

* Validation errors
* Business rule errors
* Unexpected server errors

---

# Running the Project

## 1. Clone the repository

```
git clone https://github.com/your-username/user-management-api.git
```

```
cd user-management-api
```

---

# Running with Docker

## Start PostgreSQL with Docker Compose

```
docker compose up -d
```

Database configuration:

```
POSTGRES_USER=admin
POSTGRES_PASSWORD=postgres
POSTGRES_DB=db_users
```

PostgreSQL will run on:

```
localhost:5432
```

---

# Build the application

```
mvn clean package
```

---

# Build Docker image

```
docker build -t user-api .
```

---

# Run container

```
docker run -p 8080:8080 user-api
```

API will start at:

```
http://localhost:8080
```

---

# Docker Compose (Database)

```
services:
  postgres:
    container_name: postgres-users
    image: bitnami/postgresql:latest
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: db_users
```

---

# Author

Developed as a backend learning project focusing on:

* Spring Boot
* REST API architecture
* JWT authentication
* Database relationships
* Validation and error handling
* Containerization with Docker
