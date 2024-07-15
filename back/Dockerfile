FROM openjdk:17
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/* ./app.jar
EXPOSE 8081
ENTRYPOINT exec java -jar app.jar
