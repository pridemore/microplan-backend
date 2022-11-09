FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microplan-service.jar
ENTRYPOINT ["java","-jar","microplan-service.jar"]