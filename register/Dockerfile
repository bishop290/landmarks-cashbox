FROM gradle:8.1.1 AS build
ENV APP_HOME=/app
COPY . $APP_HOME
WORKDIR $APP_HOME
RUN gradle build --parallel --daemon --configuration-cache -x test

FROM openjdk:17-oracle
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
