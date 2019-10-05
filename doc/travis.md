# Configuración Travis CI
Para realizar la integración continua por médio de Travis CI he tenido que crear una cuenta enlazada a [GitHub](https://github.com)  en la web de [Travis CI](https://travis-ci.com/) y tan solo se debía activar el repositorio en el que quería que actuara. En este repositorio, en el que se trabaja en el proyecto, he generado un fichero **.travis.yml** que será ejecutado por Travis CI cuando se realiza un "push" en el mismo. El archivo de configuración que por el momento voy a utilizar es el siguiente:

```
language: java
jdk: oraclejdk11
services:
  - mysql
before_install:
  - mysql -u root --password="" < test/PadelManagerDatabase.sql
script:
  - cd padelmanager
  - mvn clean install
```

Como se puede observar y debido a que este proyecto se realiza haciendo uso de un framework de [Java](https://www.java.com/es/) como es el caso de [Spring Boot](https://spring.io/) he tenido que añadir a dicho fichero tanto el lenguaje usado como el JDK, en este caso el 11.

Debido a que este proyecto hace uso de una base de datos en MySQL, he configurado Travis CI para que se genere la misma base de datos que actualmente uso en local y así poder realizar los test al proyecto. Con la siguiente línea:

```
before_install:
  - mysql -u root --password="" < test/PadelManagerDatabase.sql
```

lo que he conseguido es poder modificar el usuario "root" para usar el mismo que se usa en local y así poder pasar los test, ya que simplemente lo que realiza Travis CI con esta configuración es replicar la misma situación que actualmente tengo yo en mi máquina en local para el desarrollo de dicho microservicio. Dentro del fichero PadelManagerDatabase.sql lo que nos encontramos es lo siguiente:

```
/*Change root password to use the same BD as in local*/
use mysql;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Ruanete1997.';

/*Create database*/
CREATE DATABASE IF NOT EXISTS `PadelManager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `PadelManager`;
```

Y como se puede observar lo que se realiza es un cambio de contraseña, la creación de la base de datos y el uso de la misma. Luego al compilar y pasar los test del proyecto, este automáticamente generará las diferentes tablas.

#### Bibliografía
[TravisCI: Setup MySQL Tables+Data before running Tests](https://andidittrich.de/2017/06/travisci-setup-mysql-tablesdata-before-running-tests.html)
