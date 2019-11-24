## Creación de la imagen y uso en local

### Definición Dockerfile

Para crear una imagen de nuestro proyecto, tenemos que definir un fichero de configuración.
Como uso Docker, mi fichero se encontrará en **/** y se llamará Dockerfile. 

~~~
#DOCKERFILE
FROM openjdk:8
ADD target/playfy.jar playfy.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dserver.port=8080","-jar","playfy.jar"]
~~~

FROM indica que se va a usar otra imagen de Docker como base. En este caso usamos 
openjdk-8 porque la imagen de Java en DockerHub ya no se usa. 

ADD se usa para copiar los ficheros y directorios en la imagen de docker.

EXPOSE sirve para exponer un puerto al exterior.

ENTRYPOINT define como se ejecuta la aplicación dentro del contenedor.

### Build&Run

Una vez tenemos definido nuestro Dockerfile podemos empezar a crear la imagen.

1. Empaquetamos la aplicacción en jar. Para ello: `mvn clean package`
2. Construimos la imagen. `docker build -t docker-playfy .`
3. Podemos comprobar que se ha creado correctamente haciendo: `docker images`
4. Corremos la imagen, usando: `docker run -p 8080:8080 docker-playfy`. 
-p indica el puerto, en nuestro caso el puerto 8080 en nuestra app lo mapeamos al 8080.

## Biblografía
 - https://www.callicoder.com/spring-boot-docker-example/
 - https://spring.io/guides/gs/spring-boot-docker/
 - https://spring.io/guides/topicals/spring-boot-docker/
 - https://www.youtube.com/watch?v=FlSup_eelYE&t=998s