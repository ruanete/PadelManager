# PadelManager

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.org/ruanete/PadelManager.svg?branch=master)](https://travis-ci.org/ruanete/PadelManager)

## ¿Qué es PadelManager?
PadelManager es un microservicio centrado en la gestión de pistas de padel y la generación de partidos en los cuales los usuarios tendrán la posibilidad de apuntarse, siempre y cuando la pista este disponible y no existan en dicho partido cuatro jugadores.

## ¿Por qué PadelManager?
Esta idea surje debido a que un conocido que tiene pistas de padel se dedica a crear partidos cada día para que los usuarios se apunten, comprobar horarios disponibles y llevar el control de las pistas alquiladas mediante mensaje en Whatsapp. Para facilitar su trabajo se va a llevar a cabo dicho microservicio para poder ser consumido finalmente mediante una pagina web o incluso una aplicación para el móvil.

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
* [MySQL](https://www.mysql.com/): Gestor de bases de datos.
* [Spring Boot Actuator](https://www.baeldung.com/spring-boot-actuators): Dependencia para Spring Boot que monitoriza, recopila metricas, comprende el trafico o el estado de la base de datos.
* [Travis-CI](https://travis-ci.org/): Servicio de integración continua.
* [Heroku](https://www.heroku.com/): Servicio de computación en la Nube (PaaS).
