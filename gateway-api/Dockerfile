FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .
ENTRYPOINT ["./wait-for-it.sh", "-t", "60","eureka-service:8761", "--", "java", "-jar", "app.jar"]