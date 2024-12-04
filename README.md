# WakeWithMe API

A social alarm clock application that allows users to share wake-up messages and music.

## Project Description

WakeWithMe is a mobile application where users can:
- Set up alarms with personalized wake-up messages
- Share voice messages for friends' alarms
- Use Spotify tracks as alarm sounds
- Manage friend connections

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **PostgreSQL**
- **JWT Authentication**
- **Spring Security**
- **Swagger/OpenAPI Documentation**

## Project Setup

### Prerequisites

- Java 17 or higher
- Docker Desktop
- Postman (optional, for testing)
- IDE (IntelliJ IDEA recommended)

### Database Setup

1. Start PostgreSQL using Docker:
    ```bash
    docker run --name postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=wakewithme \
    -p 5432:5432 \
    -d postgres:13
    ```

### Application Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/wakewithme-api.git
    ```

2. Configure `application-local.yml`:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/wakewithme
        username: postgres
        password: postgres
      jpa:
        hibernate:
          ddl-auto: update

    jwt:
      secret: your-secret-key-here
      expirationMs: 86400000  # 24 hours
    ```

3. Run the application:
    ```bash
    ./gradlew bootRun
    ```

## API Documentation

- **Swagger UI**: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)

## Testing

### Postman Collection

The project includes a Postman collection in the `postman-collection` folder:
- **`collections/user.collection.json`**: API endpoints for testing
- **`environments/local.environment.json`**: Local environment variables

### Authentication Flow Test

#### Register a new user:
```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
    "email": "test@example.com",
    "password": "password123"
}
```

#### Login:
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "email": "test@example.com",
    "password": "password123"
}
```

#### Use the returned JWT token in the `Authorization` header for protected endpoints:
```
Authorization: Bearer <your-jwt-token>
```

## Project Structure

```
src/main/java/com/wakewithme/api/
├── common/
│   ├── config/
│   ├── exception/
│   └── security/
├── user/
│   ├── entity/
│   ├── repository/
│   ├── service/
│   └── web/
├── alarm/
│   ├── entity/
│   ├── repository/
│   ├── service/
│   └── web/
└── message/
    ├── entity/
    ├── repository/
    ├── service/
    └── web/
```

## Development Guidelines

- Follow domain-driven design principles
- Keep controllers thin, use services for business logic
- Write unit tests for services
- Document new endpoints in OpenAPI
- Use appropriate HTTP methods and status codes

## Available Endpoints

- **POST** `/api/auth/register` - Register new user
- **POST** `/api/auth/login` - Authenticate user
- **GET** `/api/users/{id}` - Get user profile
- (More endpoints coming soon)

## Future Features

- Voice message storage
- Spotify integration
- Friend management
- Alarm sharing
