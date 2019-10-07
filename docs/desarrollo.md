# Desarrollo

Para el desarrollo del microservicio establecí un patrón de diseño modelo-vista-controlador siguiendo la web de SpringBoot. Primero realicé un hola mundo con solo dos clases, una que en el @RequestMapping "/" que simplemente devolvia "Hola mundo". Y una clase test que comprobaba si el String era correcto. Proseguí con el desarrollo y ahora sí estructuré el proyecto en paquetes, al principio Travis CI me daba error y era debido a que tenía dos main, así que borré lo anterior. Después cree una base de datos simple MYSQL y añadí las directivas a SpringBoot usando Hibernate. Para conseguir que funcione en local, hay que añadir en primer lugar las dependencias de hibernate, luego modificar el fichero application.properties y añadir nuestra conexión, en mi caso:
>jdbc:mysql://localhost/songs_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

Tuve que añadir useUnicode useJDBCCompliantTimezoneShift... porque sino fallaba aunque no se indicaba en la página oficial. Ahora si, escribí el código de la API sin extenderme demasiado y a modo de prueba. Puede consultarse documentación en */docs/api.md/*, y unos cuantos tests.

1. getAllSongs(): Comprueba que se devuelve un objeto no nulo
2. testGetSongById(): Comprueba que el objeto con id no es nulo
3. testAddSong(): Se crea un objeto del tipo Song y se comprueba que no sea nulo
4. testUpdateSong(): Se modifican los atributos de un objeto existente y se comprueba si el actualizado no es nulo
5. testDeleteSong(): Hay varias comprobaciones, primero se crea un objeto Song y se comprueba que no sea nulo, luego se comprueba que se ha borrado o no

Estos tests me funcionaban de forma local pero para que Travis los pudiese ejecutar tuve que añadir unas directivas al fichero .travis.yml que explico en `docs/ic.md`


## Bibliografía

[Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)  
[How to Create a REST API With Spring Boot](https://dzone.com/articles/how-to-create-rest-api-with-spring-boot)  
[CI/CD for SpringBoot applications using Travis-CI](https://sivalabs.in/2018/01/ci-cd-springboot-applications-using-travis-ci/)
