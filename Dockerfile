FROM openjdk:8-jdk
EXPOSE 8081
ARG JAR_FILE=target/apiapp.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]