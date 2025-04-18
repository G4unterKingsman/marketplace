FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/orderService-0.0.1-SNAPSHOT.jar /app/order-service.jar

CMD ["java", "-jar", "order-service.jar"]

EXPOSE 8082