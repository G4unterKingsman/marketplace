FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/productService-0.0.1-SNAPSHOT.jar /app/product-service.jar

CMD ["java", "-jar", "product-service.jar"]

EXPOSE 8080