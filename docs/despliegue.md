# Despliegue

### Elección

Como PaaS elegí Heroku por varios motivos:

- Su uptime es de 99.999091% en la región europea.  

- Soporta Java.   

- Tiene un plan gratuito.  

- Se integra con git y github.  

- Si en un momento se necesitan más recursos, se pueden añadir fácilmente.  

- Capacidad de provisionamiento, y rollback de la aplicación.

- Fácil y rápido despliegue.

También intenté desplegar en otros PaaS, dejo documentación para AppEngine de Google [aquí](despliegue_appengine.md).

### Preparación 
Antes de empezar con el proceso tenemo que visitar la página de [Heroku](https://www.heroku.com/) y crearnos una cuenta, e
intalar el CLI de Heroku con `sudo snap install --classic heroku`.

### Despliegue

Podemos distinguir entre dos formas de desplegar; usando el plugin de Maven que se 
 especifica en nuestro *pom.xml* o usando el CLI de Heroku símplemente.

#### 1ª Sin plugin

1. Iniciar sesión en Heroku desde la terminal con `heroku login`.
Se abrirá una ventana en el navegador en la que se podrá iniciar sesión.

2. Podemos comprobar que el proceso se ha completado de forma correcta visualizando si 
la clave se ha añadido correctamente, para ello ejecutamos `heroku keys`. 
Si la clave no estuviese ahí podemos añadirla con `heroku keys:add`.

3. Creamos la aplicación de
Heroku que estará vinculada a la cuenta con la que nos logeamos anteriormente, para
 ello ejecutamos`heroku create`. 
Podemos especificar el nombre de nuestra aplicación o no, 
si no lo especificamos se nos asignará uno aleatorio.

4. Desplegamos con `git push heroku master`, aquí se detecta que la aplicación tiene
un fichero *pom.xml* y por lo tanto se establece que la aplicación usa Maven/Java, 
por defecto usa Java8 pero se puede cambiar creando un fichero *system.properties*
5. Finalmente, para visitar la app `heroku open`
6. Como extra, podemos visualizar el log de la aplicación con `heroku log --tail`
(para que nos aparezcan los más recientes).

#### 2ª Con plugin

1. Primero añadimos el plugin de heroku al *pom.xml*:

~~~
<project>
  ...
  <build>
    ...
    <plugins>
      <plugin>
        <groupId>com.heroku.sdk</groupId>
        <artifactId>heroku-maven-plugin</artifactId>
        <version>2.0.13</version>
      </plugin>
    </plugins>
  </build>
</project>
~~~

2.Creamos una aplicación con `heroku apps:create playfy`. Tiene el mismo efecto que el
paso 3 de antes.

3.Ahora si, desplegamos con `mvn clean heroku:deploy`.

4.Para abrir la app `heroku open`

![heroku-app](./img/api-heroku.jpg)

*Esta información ahora se muestra en /status*


Por defecto Heroku usa el siguiente comando para iniciar una aplicación desplegada con Java.
~~~
web java -Dserver.port=$PORT $JAVA_OPTS -jar target/playfy-0.0.1-SNAPSHOT.jar
~~~

En otros PaaS hay que especificar como se hará el empaquetado, Heroku sin embargo lo 
detecta y no tenemos que modificar nada. 

Si quisiéramos cambiar el startapp tendríamos que añadir un fichero con nombre Procfile
a nuestro repositorio y en su interior introducir el comando que queramos precedido de *web*.

#### GitHub

Una vez desplegada nuestra aplicación desde la web de Heroku podemos configurarla.

![heroku](./img/heroku.png)


En mi caso activo el despliegue automático y espere el CI.
### Base de datos

Mi intención era también escoger un servicio para almacenar una base de datos que usara mi aplicación.
Elegí RDS de Amazon Web Services y para poder usarla añadí el endpoint de la base de datos.
Este endpoint se agrega al *application.properties*, junto con la clave y contraseña.

![aws](./img/aws.png)

## Bibliografía
Sin plugin
 - https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

Con plugin:
 - https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin