FROM maven:3.5-jdk-8 AS build
ARG DECRYPT_KEY
COPY padelmanager /padelmanager
EXPOSE 8080
CMD cd padelmanager && mvn spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="--jasypt.encryptor.password=$DECRYPT_KEY"
