FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/var/lib/jenkins/.m2/repository/com/gameguides/api/0.0.1/api-0.0.1.jar"]