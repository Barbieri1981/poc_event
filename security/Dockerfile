# Build stage
FROM gradle:7.4.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# Run stage
FROM openjdk:17
EXPOSE 8082
COPY --from=build /app/build/libs/security-0.0.1-SNAPSHOT.jar /security-app.jar
ENTRYPOINT ["java","-jar","/security-app.jar"]
