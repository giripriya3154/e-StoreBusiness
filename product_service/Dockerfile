FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product_service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]
EXPOSE 9007
