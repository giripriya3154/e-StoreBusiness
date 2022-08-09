FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} shipping_service.jar
ENTRYPOINT ["java","-jar","/shipping-service.jar"]
EXPOSE 9008
