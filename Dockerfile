FROM openjdk:11-jdk

LABEL maintainer="me@deriklima.com"

RUN groupadd -r user_group && useradd -r -g user_group user

USER user:user_group

ENV spring.profiles.active=dev

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
