FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transaction-service.jar
ENTRYPOINT ["java","-jar","/transaction-service.jar"]
EXPOSE 9005