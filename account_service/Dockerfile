FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} account_service.jar
ENTRYPOINT ["java","-jar","/account-service.jar"]
EXPOSE 9004
