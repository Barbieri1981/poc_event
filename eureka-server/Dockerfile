# Build stage
FROM gradle:7.4.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# Run stage
FROM openjdk:17
EXPOSE 8761
COPY --from=build /app/build/libs/eureka-server-0.0.1-SNAPSHOT.jar /eureka-server-app.jar
ENTRYPOINT ["java","-jar","/eureka-server-app.jar"]
