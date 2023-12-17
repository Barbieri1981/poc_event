# Spring Boot Kafka POC Event

## Overview
This project demonstrates a Spring Boot application integrated with Apache Kafka to handle event publishing.

## Features
- Spring Boot 3.2.0 with Java 17.
- Apache Kafka integration using Spring Kafka.
- REST API endpoints for publishing string messages and complex event data.
- Utilizes Lombok for reducing boilerplate code.
- Jackson datatype integration for Java time objects.

## Prerequisites
- JDK 17
- Docker and Docker Compose (for running Kafka and Zookeeper)
- Gradle

## Libraries and Plugins
The project uses the following key dependencies and plugins:
- Spring Boot Starter Web
- Spring Kafka
- Lombok
- Jackson Datatype for JSR310 (Java Time API)

## Building the Application
To build the application, run the following commands:
```bash
./gradlew clean
./gradlew build
```
## Building and Running the Application
Before running the application, we need to build the Docker images:

```bash
docker-compose build
```
Once the build process is complete, we can start the application along with Kafka and Zookeeper using Docker Compose:

```bash
docker-compose up -d
```
### API Endpoints
The application provides two main endpoints:

#### Publish String Event
Endpoint: /publish  
Method: POST  
Parameter: event (String)  
Usage:

```bash
curl -X POST http://localhost:8080/publish?event=your-event
```

#### Publish Complex Event
Endpoint: /events  
Method: POST  
Body: EventRequestDTO JSON  
Usage:
```bash
curl -X POST http://localhost:8080/events \
-H "Content-Type: application/json" \
-d '{
    "title": "Title",
    "description": "Description",
    "startTime": "2023-12-17 10:00:00",
    "endTime": "2023-12-17 12:00:00",
    "location": "Location"
}'
```
## Viewing Logs
```bash
docker-compose logs -f
```