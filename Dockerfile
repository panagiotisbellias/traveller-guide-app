# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Copy pom and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime ---
FROM eclipse-temurin:21-jre-jammy

# Set working directory
WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/traveller-guide-app-2.7.0.jar app.jar

# Expose port if your app listens (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]
