# Build stage
FROM gradle:7.4.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# Run stage
FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/build/libs/producer-0.0.1-SNAPSHOT.jar /producer-app.jar
ENTRYPOINT ["java","-jar","/producer-app.jar"]
