# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (cache dependencies)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies (caching layer)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the JAR
RUN ./mvnw clean package -DskipTests

# Expose the port your app will run on
EXPOSE 8035

# Set environment variables for DB (optional)
ENV DB_HOST=mend-zone-db.cqcpodz6vodb.ap-south-1.rds.amazonaws.com
ENV DB_PORT=3306
ENV DB_NAME=appointments_db
ENV DATASOURCE_USER=root
ENV DATASOURCE_PASSWORD=Supriya@123
ENV SERVER_PORT=8035

# Start the Spring Boot app
CMD ["java", "-jar", "target/Onlineappoitment-0.0.1-SNAPSHOT.jar"]
