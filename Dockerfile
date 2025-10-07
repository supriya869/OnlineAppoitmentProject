# Base image
FROM openjdk:17-jdk-slim

# Working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the JAR
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE ${PORT}

# Start Spring Boot app
CMD ["java", "-jar", "target/Onlineappoitment-0.0.1-SNAPSHOT.jar"]
