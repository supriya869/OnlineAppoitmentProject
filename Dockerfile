# Use Java 17 as base image (or 21 if you use that)
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven/Gradle build JAR to container
COPY target/Onlineappoitment.jar app.jar

# Expose the app port
EXPOSE 8035

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
