FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/userService-0.0.1-SNAPSHOT.jar /app/user-service.jar

CMD ["java", "-jar", "user-service.jar"]

EXPOSE 8081