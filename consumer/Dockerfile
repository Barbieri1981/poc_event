# Build stage
FROM gradle:7.4.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# Run stage
FROM openjdk:17
EXPOSE 8081
COPY --from=build /app/build/libs/consumer-0.0.1-SNAPSHOT.jar /consumer-app.jar
ENTRYPOINT ["java","-jar","/consumer-app.jar"]
