# Spring Boot Eureka Server Application

## Overview
API Gateway serves as the primary entry point for the requests in the microservices architecture.    
Integrated with a Spring Boot-based Eureka Server, the API Gateway efficiently manages service discovery, routing, and load balancing.   

## Features
- Spring Boot 3.1.7 with Java 17.
- Eureka Server integration using Spring Cloud Netflix Eureka.
- Centralized service registry for managing microservices.
- Easy integration with Spring Boot applications.

## Prerequisites
- JDK 17
- Docker and Docker Compose
- Gradle

### Core Technologies
- **Spring Boot (version 3.2.1)**: A framework for building Spring-based Applications.
- **Java (version 17)**: The project utilizes Java 17 for its strong features in terms of performance, security, and reliability.

### Microservices and Service Discovery
- **Spring Cloud Gateway**: Used for setting up the API Gateway.
- **Netflix Eureka Client**: Service discovery, load balancing.

### Security and JWT
- **JSON Web Token (JWT)**: JWT handling.


# API Gateway Configuration Details

This section of the README outlines the configuration of the API Gateway using Spring Cloud Gateway and Eureka for service discovery. The configuration is specified in the `application.yml` file of the project.

## Spring Cloud Gateway Routes

The gateway is configured with several routes, each directing traffic to different microservices. The configuration is as follows:

```yaml
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: producer-route
          uri: lb://producer-service
          predicates:
            - Path=/producer/**
          filters:
            - AuthorizationHeaderFilter
            - StripPrefix=1

        - id: security-route
          uri: lb://security-service
          predicates:
            - Path=/security/**
          filters:
            - StripPrefix=1

        - id: consumer-route
          uri: lb://consumer-service
          predicates:
            - Path=/consumer/**
          filters:
            - StripPrefix=1
```

- `spring.cloud.gateway.routes`: routes for the API Gateway.   
- `id`: unique identifier for each route. For example, 'producer-route' is the ID for the route to the producer service.   
- `uri`: URI of the microservice to which the route directs traffic.
- `predicates`: Conditions to determine if a route should be chosen based on the incoming request. The Path=/producer/** predicate, for instance, matches all requests with paths starting with /producer/.
- `filters`:
  - `StripPrefix=1`: Removes the first segment of the request URL path. For example, a request with the path `/producer/event` will be modified to `/event` before being forwarded to the corresponding microservice.
  - `AuthorizationHeaderFilter`: A custom filter for handling authentication. It inspects the request headers and validate authentication tokens ensuring that only authenticated requests are processed by our microservices.
