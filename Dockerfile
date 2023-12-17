FROM centos:maven-3.6.3-jdk8

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar --spring.profiles.active=prod"]
