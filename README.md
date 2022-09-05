##Tech Stack
Spring Boot 2.6.6
Java 11
Jupiter tests
Gradle kotlin DSL
Sonar - Run completed and fixed all bugs, issues except minor code smells.
Postgres - DB for maintaining the state across Client & Expenses Service.
Swagger - API Documentation : `http://localhost:8081/swagger-ui.html#`

##Decisions
1. H2 In memory database has deprecated server-mode in recent spring versions. Hence decided to run a postgres db instance inside a docker container. So both client and expenses service will share this DB

##Start the application(Note: If DB already started in Client-service by running below commands not required to Start agian. Can Skip this step)
Postgres - Start the docker container from terminal (Shared): `docker run -d --name client-expense -p 5432:5432 -e POSTGRES_PASSWORD=abcd1234 -e POSTGRES_DB=postgresdb`

expense-service Application - Start the application from IDE or run gradle command: `gradlew clean bootRun`
Application will be running in the port: `http://localhost:8081/api`
