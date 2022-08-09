FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} paypal_service.jar
ENTRYPOINT ["java","-jar","/paypal-service.jar"]
EXPOSE 9002
