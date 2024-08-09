# TaskManager

TaskManager is a Spring Boot application designed to help you manage tasks with user authentication and role-based access control using JWT. This project includes a PostgreSQL database and Docker support for easy deployment.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Project](#running-the-project)
  - [Running Locally](#running-locally)
  - [Running with Docker](#running-with-docker)
- [API Endpoints](#api-endpoints)
- [Database Configuration](#database-configuration)
- [Docker Configuration](#docker-configuration)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Features

- User authentication with JWT
- Role-based access control
- CRUD operations on tasks
- PostgreSQL database integration
- Dockerized application for easy deployment

## Requirements

- Java 17
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL (optional for local setup)

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/taskmanager.git
cd taskmanager
2. Configure Environment Variables
Create an .env file in the root directory with the following content:

env
Copy code
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/taskmanager
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_SECURITY_JWT_SECRET=your_jwt_secret_key
SPRING_SECURITY_JWT_EXPIRATION_MS=86400000
Replace your_db_username, your_db_password, and your_jwt_secret_key with your actual values.

Running the Project
Running Locally
Build the Project

Build the project using Maven:

bash
Copy code
./mvnw clean package
Run the Application

Run the application using:

bash
Copy code
java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
Access the Application

The application will be accessible at http://localhost:8080.

Running with Docker
Build and Run Docker Containers

Ensure Docker and Docker Compose are installed, then run:

bash
Copy code
docker-compose up --build
Access the Application

Once the containers are up and running, the application will be accessible at http://localhost:8080.

API Endpoints
Authentication
POST /api/auth/login: Authenticate and receive a JWT token.
Task Management
GET /api/tasks: Get all tasks.
POST /api/tasks: Create a new task.
GET /api/tasks/{id}: Get a specific task.
PUT /api/tasks/{id}: Update a specific task.
DELETE /api/tasks/{id}: Delete a specific task.
User Management
GET /api/users: Get all users (Admin only).
POST /api/users: Create a new user (Admin only).
Database Configuration
The application uses PostgreSQL as its database.
You can configure the database connection in the .env file.
Default Configuration:
yaml
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
Docker Configuration
The application uses a Dockerfile to build the application image.
docker-compose.yml is used to define the services: PostgreSQL and the Spring Boot application.
Dockerfile
Dockerfile
Copy code
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/taskmanager-0.0.1-SNAPSHOT.jar taskmanager.jar
ENTRYPOINT ["java","-jar","/taskmanager.jar"]
Docker Compose (docker-compose.yml)
yaml
Copy code
version: '3'
services:
  postgres:
    image: postgres:13
    environment:
      POSTGRES_DB: taskmanager
      POSTGRES_USER: your_db_username
      POSTGRES_PASSWORD: your_db_password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  app:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/taskmanager
      SPRING_DATASOURCE_USERNAME: your_db_username
      SPRING_DATASOURCE_PASSWORD: your_db_password
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_SECURITY_JWT_SECRET: your_jwt_secret_key
      SPRING_SECURITY_JWT_EXPIRATION_MS: 86400000
    ports:
      - "8080:8080"
    depends_on:
      - postgres

volumes:
  postgres_data:
Troubleshooting
Maven build issues: Ensure that Maven is correctly installed and available in your system's PATH.
Docker build issues: Ensure Docker is running and that your .jar file is correctly generated in the target directory.
Database connection issues: Ensure PostgreSQL is running and accessible using the credentials provided.