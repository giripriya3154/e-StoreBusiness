FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} creditcard_service.jar
ENTRYPOINT ["java","-jar","/creditcard-service.jar"]
EXPOSE 9001
