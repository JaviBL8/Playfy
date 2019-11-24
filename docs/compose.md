## Docker compose

**Compose** es una herramienta para definir y ejecutar aplicaciones multi-container 
de Docker. Hace uso de un fichero YAML para configurar los servicios de la aplicación.

*docker-compose.yml*
~~~
version: '3'
services:
  playfy:
    image: javibl8/docker-playfy
    container_name: playfy
    ports:
      - '8080:8080'
~~~

**Version**: Hace referencia a la version de compose que se va a usar. 
Uso la 3 que es la recomendada.  
**Services**: Establece el servicio que se va a usar, en este caso lo hemos definido con el 
nombre del repositorio.  
**Image**: Se usa para identificar que imagen queremos usar. Previamente hay que subir la
imagen a DockerHub.  
**Container name**: Determina el nombre que va a tener nuestro contenedor.  
**Ports**: Realiza un mapeo de puertos. En este caso el 8080 de la app al 8080 del contenedor.

Para usar este docker-compose, tenemos que definir el fichero docker-compose.yml como
aparece arriba y ejecutar `docker-compose up`.  

### Bibliografía
 - https://docs.docker.com/compose/