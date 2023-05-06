FROM openjdk:8-jre-alpine
COPY "./target/microplan-service.jar" .
#run the jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-jar","microplan-service.jar"]