# Use an official Eclipse Temurin JDK as a parent image for building the application
FROM eclipse-temurin:17-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and related files from the project root
COPY gradlew ./gradlew
COPY gradlew.bat ./gradlew.bat
COPY gradle ./gradle
COPY applications/App-service/build.gradle ./build.gradle
COPY applications/App-service/settings.gradle ./settings.gradle
COPY applications/App-service/src ./src

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Use an official Eclipse Temurin JRE as a parent image for running the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Volume for temporary files
VOLUME /tmp

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar bookApp.jar

# Set environment variables
ENV JAVA_OPTS="-Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Create a non-root user and switch to it
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar bookApp.jar"]
