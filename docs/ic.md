# IC

Si configuramos Travis de la forma básica, en mi caso:

> language: java  
> jdk: oraclejdk11

Por defecto lo que hará es ejecutar **mvn tests -B** esto se debe a que mi proyecto tiene un fichero `pom.xml` pero no build-gradle. Así se ejecutan todas los tests unitarios que haya dentro de /src/main/tests/... en mi caso PlayfyTests. Seguiré la nomenclatura de *Tests.java* para los unitarios e *IT.java* para los de integración.

Esta configuración, si bien es válida para el primer prototipo que realicé (HelloWorld). Se queda desfasada en el momento que añado una base de datos simple. Tenemos que indicarle a Travis CI la base de datos y el usuario en mi caso que tiene que usar; para ello lo configuramos de la siguiente manera:

>language: java
>jdk: oraclejdk11
>services:
>  - mysql
>sudo: required
>before_install:
>  - mysql -u root -e 'CREATE DATABASE songs_database'
>  - mysql -u root -e "CREATE USER 'JAVIBL8'@'localhost' IDENTIFIED BY 'practicas,IV';"
>  - mysql -u root -e "GRANT ALL ON songs_database.* TO 'JAVIBL8'@'localhost';"   

Así lo que indicamos es qeu antes de instalar cree la base de datos `songs_database`, cree el usuario `JaviBL8` con contraseña `practicas,IV` y por último le garantice privilegios sobre la base de datos.
