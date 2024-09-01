FROM openjdk:19-jdk-oracle
VOLUME /tmp
COPY out/artifacts/karting_boards_backend_jar/*.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
EXPOSE 8080