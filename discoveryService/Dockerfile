FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/discoveryService-0.0.1-SNAPSHOT.jar /app/doscovery-server.jar

CMD ["java", "-jar", "doscovery-server.jar"]

EXPOSE 8761