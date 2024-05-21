# Use an official Eclipse Temurin JDK as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Volume for temporary files
VOLUME /tmp

# Copy the JAR file from the build/libs directory to the container
COPY applications/app-service/build/libs/*.jar bookApp.jar

# Set environment variables
ENV JAVA_OPTS="-Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Create a non-root user and switch to it
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar bookApp.jar"]
