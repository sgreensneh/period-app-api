# Use a base image with Java pre-installed
FROM eclipse-temurin:17

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/period-app-0.0.1-SNAPSHOT.jar /app/period-app-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8891

# Set the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "period-app-0.0.1-SNAPSHOT.jar"]