FROM openjdk:8-jre-alpine
#COPY "./target/microplan-service.jar" .
#run the jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "/opt/creative/microplan-service.jar"]