FROM maven:3.6-jdk-8
COPY . /playfy
WORKDIR playfy
EXPOSE 8080
CMD mvn spring-boot:run -Dserver.port=8080
