# BookmarkApp

This is a sample Spring Application for managing bookmarks.

#API
Check localhost:8080/swagger-ui.html for documentation.

API:

    GET     /app/bookmarks
        - Returns list of bookmarks
        
    GET     /app/bookmarks/{id}
        - Returns bookmark at index
        
    POST    /app/bookmarks
        - Add a bookmark

# Running Setup

This app can be run locally using the jar or inside a docker container using docker-compose

## Local

Start a mysql service. An example would be:

`docker run -p 3306:3306 --name local-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=bookmark mysql:latest`

Confirm that the application-local.yml configuration is appropriate.

Compile the project:

``./gradlew build``

Run the jar file using:

`java -jar -Dspring.profiles.active=local build/libs/bookmark-app-sample-0.1.0.jar`

## docker-compose
Before running docker compose, compile the application:

`./gradlew build`

Install docker-compose to local machine and run:

`docker-compose up`

The application should be available on localhost:8080


