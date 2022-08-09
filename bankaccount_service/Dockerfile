FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bankaccount_service.jar
ENTRYPOINT ["java","-jar","/bankaccount-service.jar"]
EXPOSE 9003
