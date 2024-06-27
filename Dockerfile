FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/libs/cac_api-1.1.1S.jar
COPY ${JAR_FILE} cac_api_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cac_api_app.jar"]