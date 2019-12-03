# PadelManager

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.org/ruanete/PadelManager.svg?branch=master)](https://travis-ci.org/ruanete/PadelManager)
![](https://github.com/ruanete/PadelManager/workflows/PadelManager/badge.svg)

## ¿Qué es PadelManager?
PadelManager es un microservicio centrado en la gestión de pistas de padel y la generación de partidos en los cuales los usuarios tendrán la posibilidad de apuntarse, siempre y cuando la pista este disponible y no existan en dicho partido cuatro jugadores.

## ¿Por qué PadelManager?
Esta idea surge debido a que un conocido que tiene pistas de padel se dedica a crear partidos cada día para que los usuarios se apunten, comprobar horarios disponibles y llevar el control de las pistas alquiladas mediante mensaje en Whatsapp. Para facilitar su trabajo se va a llevar a cabo dicho microservicio para poder ser consumido finalmente mediante una pagina web o incluso una aplicación para el móvil.

## ¿Que es lo que se podrá realizar?
* Añadir tantas pistas de padel como el usuario desee.
* Añadir horarios a las pistas.
* Creación de partidos en dichas pistas.
* Realización de reservas en las pistas con una fecha y hora dada.
* Posibilidad a los usuarios de crear partidos y de apuntarse a estos.

## ¿Cómo se llevará a cabo?
Inicialmente la implementación de dicho microservicio será realizada en **Java**, concretamente usando el framework **Spring Boot**. He decidido hacer uso de este framework debido a que actualmente estoy trabajando con él realizando una **API REST** para manejar una base de datos. Además, como Java es uno de los lenguajes en los que más he trabajado me parece más acorde trabajar con un framework que haga uso de este. Aunque esto puede formar parte de un servicio y no de un microservicio que es en lo que estamos centrandonos, solo será implementado parte de este proyecto.

## Tecnologías a usar
* [Java](https://www.java.com/es/): Lenguaje de programación.
  * [Spring Boot](https://spring.io/): Framework basado en Java que se centra en el desarrollo de aplicaciones simplificando los pasos de creación del proyecto y despliegue.
  * [Spring Test (JUnit 4)](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html): Biblioteca para la realización de test unitarios. Usada por su facilidad de uso y por su integración directa con el framework usado como es el caso de Spring Boot.
* [MySQL](https://www.mysql.com/): Gestor de bases de datos.
* [Swagger](https://swagger.io/): Herramienta que ayuda a desarrollar, construir, documentar y consumir la REST API. Es de mucha utilidad mantener una buena documentación de la API, por ello he usado esta herramienta que va ayudar a mantener la API REST documentada sin que tenga mucho coste de trabajo.
* [Spring Boot Actuator](https://www.baeldung.com/spring-boot-actuators): Dependencia para Spring Boot que monitoriza, recopila metricas, comprende el trafico o el estado de la base de datos.
* [Travis-CI](https://travis-ci.org/): Servicio de integración continua. Usado debido a la integración directa que tiene con GitHub y que es usado directamente desde la nube, además es fácil de configurar y compatible con la mayoría de lenguajes.
* [GitHub Actions](https://github.com/features/actions): Servicio de integración continua. Al ser un servicio propio de GitHub está totalmente integrado con este y las facilidades que con esto conlleva, muy parecido de configurar a Travis CI.
* [Heroku](https://www.heroku.com/): Servicio de computación en la Nube (PaaS).
* [Google Cloud](https://cloud.google.com/): Servicio de infraestructura en la nube (IaaS).
* [Docker](https://www.docker.com/): Contenedores que facilita el despliegue del proyecto.

## [Documentación](https://github.com/ruanete/PadelManager/blob/master/doc/README.md)
* [Instalación, ejecución y uso en local](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion.md)
* [Instalación, ejecución y uso en producción. Explicación de perfiles.](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion_prod.md)
* [Documentación de las clases del microservicio](https://github.com/ruanete/PadelManager/tree/master/doc/clases.md)
* [Documentación API REST](https://github.com/ruanete/PadelManager/tree/master/doc/documentacion_api.md)
* Integración continua
  * [Configuración Travis CI](https://github.com/ruanete/PadelManager/tree/master/doc/travis.md)
  * [Configuración de GitHub Actions](https://github.com/ruanete/PadelManager/tree/master/doc/github_actions.md)
* [Tests REST API](https://github.com/ruanete/PadelManager/tree/master/doc/test.md)
* [Herramienta de construcción](https://github.com/ruanete/PadelManager/tree/master/doc/herramienta_construccion.md)
* [Configuración y despliegue en PaaS. Heroku.](https://github.com/ruanete/PadelManager/tree/master/doc/configuracion_despliegue_heroku.md)
* [Creación de imagen Docker y uso en local. Uso de DockerHub.](https://github.com/ruanete/PadelManager/tree/master/doc/docker.md)
* [Despliegue de contenedor Docker en Google Cloud. Autodespliegues.](https://github.com/ruanete/PadelManager/tree/master/doc/docker_to_googlecloud.md)

## [Herramienta de construcción](https://github.com/ruanete/PadelManager/tree/master/doc/herramienta_construccion.md)
```
buildtool: pom.xml
```

## [Despliegue en Heroku](https://github.com/ruanete/PadelManager/tree/master/doc/configuracion_despliegue_heroku.md)
```
Despliegue: https://padelmanager.herokuapp.com
```

## Repositorio DockerHub
```
Repositorio: https://hub.docker.com/r/ruanete/padelmanager
```

## [Despliegue contenedor en  Google Cloud](https://github.com/ruanete/PadelManager/tree/master/doc/docker_to_googlecloud.md)
```
Contenedor: https://padelmanager-yt2vhm5fza-uc.a.run.app/
```
