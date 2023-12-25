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

## Stopping and Removing Docker Containers, Networks, and Volumes

```bash
docker-compose down
```

## Removing Unused Docker Images

```bash
docker image prune
```

## Removing All Unused Docker Images

```bash
docker image prune -a
```

## Building and Running the Application
Before running the application, we need to build the Docker images:

```bash
docker-compose build
```
Once the build process is complete, we can start the application along with Kafka and Zookeeper using Docker Compose:

```bash
docker-compose up
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

#### Login Endpoint
Endpoint: /login   
Method: POST
Usage:   
To make a login request, use the following curl command   
```bash
curl --location --request POST 'http://localhost:8082/login' \
--header 'Content-Type: application/json' \
--data-raw '{
  "password": "admin",
  "userName": "admin@gmail.com"
}'
```

## Docker Environment Setup
docker-restart.sh, help manage Docker environment for this project.    
This script will clean up existing Docker resources and then start the Docker Compose setup.   

The script performs the following actions:   
- Stops all running Docker containers.
- Removes all stopped Docker containers.
- Removes Docker images.
- Removes unused Docker volumes and networks.
- Rebuilds the Docker images and starts the Docker Compose services.



Before running the script. Change the file's mode to executable.   

```bash
chmod +x docker-restart.sh
```

After making the script executable, run it to set up your Docker environment.   
```bash
sudo ./docker-restart.sh
```

## Viewing Logs
```bash
docker-compose logs -f
```
