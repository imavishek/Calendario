# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Make port 8036 available to the world outside this container
EXPOSE 8036

# The application's jar file
ARG JAR_FILE=target/user-service-0.0.1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} user-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
