FROM maven:3.5-jdk-8 AS build
ARG DECRYPT_KEY
COPY padelmanager /padelmanager
WORKDIR padelmanager
EXPOSE 8080
CMD java -Dspring.profiles.active=prod -Dspring-boot.run.profiles=prod -Djasypt.encryptor.password=$DECRYPT_KEY -jar target/*.jar
