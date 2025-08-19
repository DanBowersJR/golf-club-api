# Step 1: Use a lightweight JDK to build the app
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Use a lightweight JRE to run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
