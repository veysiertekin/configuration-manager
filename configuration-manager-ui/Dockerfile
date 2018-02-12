FROM openjdk:8-jdk-alpine
VOLUME /tmp

ARG JAR_FILE
ADD ${JAR_FILE} app.jar

COPY docker/start_jar.sh .

ENTRYPOINT ["sh","start_jar.sh"]