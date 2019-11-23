FROM openjdk:8
ADD target/playfy.jar playfy.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dserver.port=8080","-jar","playfy.jar"]