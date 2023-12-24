# Spring Boot and Spring Security with JWT Authentication

This project is a Spring Boot application configured with Spring Security to secure API using JSON Web Tokens (JWT) for authentication.

## Key Components

### WebSecurityConfiguration

Responsible for setting up the security configurations. Defines a `SecurityFilterChain` bean that configures HTTP security, enabling JWT authentication, disabling CSRF, and setting session management to stateless.

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    // ... existing code ...

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // ... existing code ...
    }

    // ... other beans ...
}
```

### UserDetailsServiceImpl
Implements UserDetailsService interface for fetching user details from the database and handling user authentication logic.

```java
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    // ... existing code ...

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        // ... existing code ...
    }
}

```
### JWTAuthenticationFilter
Extends UsernamePasswordAuthenticationFilter to handle user authentication requests and generate JWT tokens upon successful authentication.

```java
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // ... existing code ...

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // ... existing code ...
    }

    // ... other methods ...
}

```

## Project Dependencies

This section provides an overview of the dependencies used in this Spring Boot Security project with JWT authentication.

### Core

- **`org.springframework.boot:spring-boot-starter-web`**:
  Facilitates the creation of RESTful services. It embeds Tomcat.

- **`org.springframework.boot:spring-boot-starter-security`**:
  Security for Spring applications.

### Development Tools

- **`org.projectlombok:lombok`**:
  Reduces boilerplate code in Java applications (e.g., getters, setters, constructors) through annotations.

### Database

- **`com.h2database:h2`**:
  A lightweight, in-memory database.

### Validation

- **`jakarta.validation:jakarta.validation-api`** and **`org.hibernate.validator:hibernate-validator`**:
  Provide a framework for validating Java beans.

### Data Access

- **`org.springframework.boot:spring-boot-starter-data-jpa`**:
  Simplifies the implementation of data access layers, integrating Spring Data JPA with Hibernate.

### JWT Support

- **`io.jsonwebtoken:jjwt-api`**, **`io.jsonwebtoken:jjwt-impl`**, and **`io.jsonwebtoken:jjwt-jackson`**:
  Libraries for creating and parsing JSON Web Tokens.

  

## Prerequisites
- JDK 17
- Docker and Docker Compose
- Gradle


## Building the Application
To build the application, run the following commands:
```bash
./gradlew clean
./gradlew build
./gradlew bootRun
```

### API Endpoints

#### Login
Endpoint: /login  
Method: POST

```bash
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: text/plain' \
--data-raw '{
  "password": "admin",
  "userName": "admin@gmail.com"
}'
```