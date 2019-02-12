# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/bookmark-app-sample-0.1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} bookmark-app-sample.jar

# Add the wait-for-it script to wait for mysql to be totally up
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
RUN apk add --no-cache bash
