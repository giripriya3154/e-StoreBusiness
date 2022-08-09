FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config_service.jar
ENTRYPOINT ["java","-jar","/config-service.jar"]
EXPOSE 9011
