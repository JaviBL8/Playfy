FROM openjdk:8
ADD target/playfy.jar playfy.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","playfy.jar"]