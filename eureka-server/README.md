# Spring Boot Eureka Server Application

## Overview
This project is a Spring Boot-based Eureka Server application used for service discovery in a microservices architecture.

## Features
- Spring Boot 3.1.7 with Java 17.
- Eureka Server integration using Spring Cloud Netflix Eureka.
- Centralized service registry for managing microservices.
- Easy integration with Spring Boot applications.

## Prerequisites
- JDK 17
- Docker and Docker Compose
- Gradle

## Libraries and Plugins
The project uses the following key dependencies and plugins:
- Spring Cloud Starter Netflix Eureka Server
- Spring Boot Starter Test

## Configuration Properties Explained

### `server.port`
- **Description**: Defines the port on which the Eureka server will be listening.
- **Value**: `8761`
- **Purpose**: Sets `8761` as the port for accessing the Eureka server.

### `eureka.client.register-with-eureka`
- **Description**: Indicates whether this Eureka server should register itself as a client with another Eureka server.
- **Value**: `false`
- **Purpose**: By setting this to `false`, it prevents the Eureka server from attempting to register itself with another Eureka server.

### `eureka.client.fetch-registry`
- **Description**: Indicates whether this Eureka server should fetch the service registry from other Eureka servers.
- **Value**: `false`
- **Purpose**: Configured as `false`, the Eureka server will not attempt to fetch service registries from other Eureka servers.

