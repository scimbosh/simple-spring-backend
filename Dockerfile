FROM openjdk:17-oracle
ARG JAR_FILE=/build/libs/simplespringbackend-1.0.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-Dserver.port=8080","-Dspring.profiles.active=local-docker","-jar","app.jar"]
