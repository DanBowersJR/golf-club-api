# -------------------------------
# Step 1: Build the app with Maven + JDK 17
# -------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven files and resolve dependencies first (faster rebuilds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# -------------------------------
# Step 2: Run the app with lightweight JRE
# -------------------------------
# Use slim JDK runtime (smaller image, cross-platform, works on Mac M1/M2)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (for documentation; docker-compose still maps ports)
EXPOSE 8080

# Run the Spring Boot app, default profile = docker
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
