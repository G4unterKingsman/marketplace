FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/github-config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

CMD ["java", "-jar", "config-server.jar"]

EXPOSE 8888