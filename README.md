# Playfy
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.org/JaviBL8/Playfy.svg?branch=master)](https://travis-ci.org/JaviBL8/Playfy)
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://playfy.herokuapp.com/api/)

Microservicio para recomendar música. El usuario introducirá unos cuantos datos y el sistema realizará una recomendación en función a estos parámetros. Para llevar a cabo esta idea tendré un conjunto de parámetros asociados a cada canción (género, año...) que almacenaré dentro de una base de datos a la que realizaré consultas.

## Ciclo de vida :rocket:

- [x] Planear proyecto (Descripción, herramientas...)
- [x] Desarrollo microservicio (Java, SpringBoot)
- [x] Integración continua y creación de tests unitarios
- [ ] Despliegue del microservicio (IaaS, PaaS, SpringCloud)
- [ ] Despliegue automático

He elegido Java porque tengo soltura con el lenguaje de otras asignaturas (no descarto usar Kotlin también porque me gustaría aprenderlo). SpringBoot por ser uno de los framework basados en Java más utilizado para el desarrollo de microservicios y que además proporciona ventajas frente a Spring.

## Herramientas :wrench:

 - Lenguaje de programación:
    - [Java](https://java.com/es/)
 - ORM:
    - [Hibernate](https://hibernate.org/)
 - SGBD:
    - [MySQL](https://www.mysql.com/)
 - Framework:
    - [SpringBoot](https://spring.io/projects/spring-boot)
 - Despliegue:
    - PaaS: [Heroku](https://www.heroku.com/)
    - IaaS: [AWS](https://aws.amazon.com/es/)
 - Integración continua:
    - [Travis CI](https://travis-ci.org/)
 - Sitema de logs: ELK
    - [ElasticSearch](https://www.elastic.co/es/products/elasticsearch)
    - [Logstash](https://www.elastic.co/es/products/logstash)
    - [Kibana](https://www.elastic.co/es/products/kibana)
 - Tests:
    - [Junit 4](https://junit.org/junit4/)

## Herramienta de construción

    buildtool: pom.xml

Leer documentación para buildtool.

## [Documentación](/docs) :notebook:
- [API](/docs/api.md)
- [Integración continua](docs/ic.md)
- [Desarrollo](docs/desarrollo.md)
- [Buildtool](docs/buildtool.md)
- [Tests](docs/tests.md)
- [Status](docs/status.md)

---
Si el proyecto te resulta interesante no dudes en marcarlo con una estrella :star:
