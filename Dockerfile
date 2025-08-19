# -------------------------------
# Step 1: Build the app with Maven + JDK 17
# -------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# -------------------------------
# Step 2: Run the app with lightweight JRE
# -------------------------------
# Use a cross-platform compatible image (works on Mac M1/M2)
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (for documentation; docker-compose still maps ports)
EXPOSE 8080

# Pass profile dynamically (defaults to docker)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:docker}", "app.jar"]
