FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka_service.jar
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
EXPOSE 8761
