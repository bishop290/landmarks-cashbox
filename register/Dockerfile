FROM gradle:8.1.1 AS build
ENV APP_HOME=/app
COPY . $APP_HOME
WORKDIR $APP_HOME
RUN gradle build -x test

FROM openjdk:17-oracle
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/build/libs/*.jar app.jar
ENV REGISTER_HOST=localhost
ENV REGISTER_PORT=8761
ENTRYPOINT ["java", "-jar", "app.jar"]
