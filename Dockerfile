FROM openjdk:9-jdk-slim
RUN mkdir /app

RUN apt-get update && apt-get -y install libtcnative-1

ADD . /app
WORKDIR /app


#RUN mvn package -Dmaven.test.skip=true


EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","target/demo.jar"]

