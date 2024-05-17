FROM eclipse-temurin:21-jre-alpine
ADD target/sneaker*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]