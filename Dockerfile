# Use Java 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the correct JAR built by Maven
COPY target/Onlineappoitment-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port
EXPOSE 8035

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
