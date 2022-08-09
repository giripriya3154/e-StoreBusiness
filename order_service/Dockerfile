FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} order_service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]
EXPOSE 9006
