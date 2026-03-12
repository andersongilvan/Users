FROM eclipse-temurin:17

LABEL maintainer="torresdinho8@gmail.com"

WORKDIR /app

COPY target/users-0.0.1-SNAPSHOT.jar /app/user-application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

EXPOSE 8080