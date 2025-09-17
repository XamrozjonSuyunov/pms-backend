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

## CORS Configuration

This application has a global CORS configuration that allows cross-origin requests. This is necessary for frontend applications running on different domains to interact with the API.

By default, it allows all origins (`*`), but this can be configured in `application.properties` using the `application.cors.origins` property.

-   **Allowed Methods**: `GET`, `POST`, `DELETE`, `PUT`, `PATCH`
-   **Allowed Headers**: `Origin`, `Content-Type`, `Accept`, `Authorization`

## API Endpoints

The following table lists the available API endpoints:

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/projects` | Get paginated list of projects |
| GET | `/api/v1/projects/stats` | Get overall project statistics |
| GET | `/api/v1/projects/{id}` | Get project by ID |
| POST | `/api/v1/projects` | Create a new project |
| PUT | `/api/v1/projects/{id}` | Update an existing project by ID |
| DELETE | `/api/v1/projects/{id}` | Delete project by ID |
| GET | `/api/v1/projects/{id}/monthly-sales` | Get project monthly sales by ID |
| GET | `/api/v1/projects/sales-revenue` | Get monthly sales revenue by year |
| GET | `/api/v1/projects/top-revenue-ratio` | Get top revenue ratio by year |
| GET | `/api/v1/projects/plan` | Get project plan by year |
| GET | `/api/v1/projects/delayed-pm` | Get project delayed PM by year |
| GET | `/api/v1/projects/types` | Get project types |
| GET | `/api/v1/projects/statuses` | Get project statuses |

## API Documentation

API documentation is available via Swagger UI at `http://localhost:8080/swagger-ui.html`. It is highly recommended to use Swagger UI to explore and test the APIs.
