FROM eclipse-temurin:19-jdk-alpine

MAINTAINER ekgreen

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENV SERVER_PORT 8080

ENTRYPOINT ["java","-jar","/app.jar"]