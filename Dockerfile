# Use an official Gradle image with JDK 17 to build the application
FROM gradle:7.4.2-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy source code
COPY --chown=gradle:gradle . .

# Build the application
RUN gradle build --no-daemon

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
