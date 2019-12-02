# Playfy
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.org/JaviBL8/Playfy.svg?branch=master)](https://travis-ci.org/JaviBL8/Playfy)
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://playfy.herokuapp.com/)
[![Run on Google Cloud](https://deploy.cloud.run/button.svg)](https://docker-playfy-ewjnsmckva-ew.a.run.app/)

Microservicio para recomendar música. El usuario introducirá unos cuantos datos y el sistema realizará una recomendación en función a estos parámetros. Para llevar a cabo esta idea tendré un conjunto de parámetros asociados a cada canción (género, año...) que almacenaré dentro de una base de datos a la que realizaré consultas.

## Ciclo de vida :rocket:

- [x] Planear proyecto (Descripción, herramientas...)
- [x] Desarrollo microservicio (Java, SpringBoot)
- [x] Integración continua y creación de tests unitarios
- [x] Despliegue del microservicio
- [x] Despliegue automático

## [Herramienta de construcción](docs/buildtool.md)

    buildtool: pom.xml

## [Despliegue en Heroku](docs/despliegue.md)

    Despliegue: https://playfy.herokuapp.com/

## [Repositorio DockerHub](docs/dockerhub.md)

    Repositorio: https://hub.docker.com/r/javibl8/docker-playfy

## [Despliegue contenedor Cloud Run](docs/despliegue_run.md)

    Contenedor: https://docker-playfy-ewjnsmckva-ew.a.run.app

## [Documentación](/docs) :notebook:

- [Subir imagen y autobuild (DockerHub)](docs/dockerhub.md)
- [Crear imagen en local (Docker)](docs/crear_imagen.md)
- [Uso de docker-compose](docs/compose.md)
- [Despligue contenedor (Cloud Run)](docs/despliegue_run.md)
- [Despliegue PaaS (Heroku)](docs/despliegue.md)
- [Despliegue PaaS (AppEngine)](docs/despliegue_appengine.md)
- [Herramientas](docs/herramientas.md)
- [Buildtool](docs/buildtool.md)
- [Desarrollo](docs/desarrollo.md)
- [Tests](docs/tests.md)
- [Integración continua](docs/ic.md)
- [Status](docs/status.md)
- [API](/docs/api.md)
- [Base de datos](/docs/bd.md)


---
Si el proyecto te resulta interesante no dudes en marcarlo con una estrella :star:
