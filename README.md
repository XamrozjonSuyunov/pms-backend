# PMS Backend

This is the backend for a Project Management System (PMS). It provides RESTful APIs for managing projects.

## Tech Stack

*   **Java 17**
*   **Spring Boot 3.5.5**
*   **Spring Web**
*   **Spring Data JPA**
*   **Hibernate**
*   **SQLite**
*   **SpringDoc OpenAPI (Swagger UI)**
*   **Lombok**
*   **Gradle**
*   **Docker**

## Build and Run

### Using Gradle

To build the project, run the following command:

```sh
./gradlew build
```

To run the project, run the following command:

```sh
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

### Using Docker

To build and run the project using Docker, run the following command:

```sh
docker-compose up --build
```

The application will be available at `http://localhost:8080`.

## API Endpoints

The following table lists the available API endpoints:

| Method | Endpoint                      | Description                      |
|--------|-------------------------------|----------------------------------|
| GET    | `/api/v1/projects`            | Get paginated list of projects   |
| GET    | `/api/v1/projects/stats`      | Get overall project statistics   |
| GET    | `/api/v1/projects/{id}`       | Get project by ID                |
| POST   | `/api/v1/projects`            | Create a new project             |
| PUT    | `/api/v1/projects/{id}`       | Update an existing project by ID |
| DELETE | `/api/v1/projects/{id}`       | Delete project by ID             |

## API Documentation

API documentation is available via Swagger UI at `http://localhost:8080/swagger-ui.html`. It is highly recommended to use Swagger UI to explore and test the APIs.
