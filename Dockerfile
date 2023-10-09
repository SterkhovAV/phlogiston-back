FROM openjdk:11-jdk-slim

ENV APP_HOME /app
WORKDIR $APP_HOME

COPY build/libs/*.jar $APP_HOME/phlogiston-app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/phlogiston-app.jar"]
