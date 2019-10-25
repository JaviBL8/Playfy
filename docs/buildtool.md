## Buildtool

Como herramienta de construcción he empleado Apache Maven. Al usar Spring Boot, podría haberme decidido tanto por Gradle como Maven. Me decidí por la segunda porque quería aprender un poco más ya que con Gradle he trabajado con anterioridad. Una vez tomada esta decisión, para incluir directivas que me permitiesen iniciar y detener el servicio exploré varias alternativas.

En la primera, usé el comando que se usa por defecto para lanzar la aplicación:

`$ mvn spring-boot:run`

Para detenerla, la opción más fácil es usar un endpoint. Para ello hay que añadir una directiva a *application.properties* y quitar la seguridad que usa Sring Boot, ya que cada vez que se ejecuta aparece un login y resulta muy complicado detener la aplicación.

Sobra decir que este método no me convencía en absoluto por lo que decidí investigar si había otra solución a este problema.

Finalmente encontré la solución. Cambiando el fichero *pom.xml* y añadiendo las siguientes directivas:

~~~
<plugin>   
    <groupId>org.springframework.boot</groupId>   
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
         <executable>true</executable>
        <fork>true</fork>
        <addResources>true</addResources>
        <!-- <jvmArguments> -->
        <!-- -agentlib:jdwp=transport=dt_socket,address=localhost:5005,server=y,suspend=n -->
        <!-- </jvmArguments> -->
    </configuration>
</plugin>
~~~

Esto, me permite que pueda ejecutar mi programa en segundo plano añadiendo & y después pueda detenerlo. Primero hay que ejecutar `$ mvn clean` para limpiar el proyecto, y después podemos iniciarlo con `$ spring-boot:start &`, para detenerlo `$ spring-boot:stop`.

## Bibliografía

1ª opción:
  - https://www.baeldung.com/spring-boot-shutdown
  - https://stackoverflow.com/questions/38377156/how-to-stop-a-spring-boot-service-from-command-line

2ª opción:
  - https://stackoverflow.com/questions/45960578/spring-boot-maven-plugin-stop-goal
