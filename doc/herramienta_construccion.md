# Herramienta Construcción
La herramienta de construcción que se utiliza en el framework **Spring Boot** es [Maven](https://maven.apache.org/). Uno de los puntos importantes que tiene **Spring Boot** es que la herramienta de construcción se autoconfigura sola, configurando dependencias, descargandolas y construyendo la aplicación final sin necesidad de realizar ningún procedimiento, más que el añadir las dependencias que necesitemos. En el caso de **Spring Boot** y haciendo uso de **Maven**, las dependencias de nuestro proyecto se encuentran en la carpeta del proyecto y toma el nombre de **pom.xml**, un ejemplo de dependencia es el siguiente:

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Esta dependencia y muchas más son añadidas a dicho fichero y cuando se realiza la construcción del proyecto **Maven** es el encargado de instalarlas todas. Aunque este framework facilita que se autoconfigure la herramienta de construcción, cabe la posibilidad de configurar ciertas cosas que necesitemos, en mi caso, para poder finalizar la aplicación usando **Maven** he tenido que configurar un plugin ([Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html)) que se agrega en **pom.xml** que nos facilita el arranque y la finalización de nuestro proyecto haciendo uso de comandos sencillos, dicha configuración es la siguiente:

```
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
```

Esta configuración ha sido realizada debido a que inicialmente sin ella no podia finalizar la aplicación, en la bibliografía adjunto donde ha sido sacada dicha configuración. Esta configuración permite que la orden **mvn spring-boot:stop** pueda finalizar el microservicio anteriormente iniciado con **mvn spring-boot:start &** iniciado en segundo plano para que se pueda finalizar con **stop**, si no se usa **&** no funcionará la finalización y es debido a que se ejecuta en una hebra en la que no se permite finalizar o realizar un shutdown. Como resumen adjunto los comando que se pueden usar para realizar la compilación del proyecto, ejecución de test, ejecución del microservicio y detención del mismo:

1. Usado para la construcción del proyecto.
```
mvn clean install
```
2. Usado para la ejecución de los test del proyecto.
```
mvn test
```

3. Usado para arrancar el proyecto.
```
mvn spring-boot:start &
```

4. Usado para finalizar la ejecución del proyecto habiendo usado la anterior orden.
```
mvn spring-boot:stop
```

Esto que he explicado anteriormente ha sido explicado también en [Instalación, ejecución y uso en local](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion.md) aunque aquí hacemos mayor referencia a la própia herramienta de construcción usada.

#### Bibliografía
[Spring Boot Maven Plugin Stop Goal](https://stackoverflow.com/questions/45960578/spring-boot-maven-plugin-stop-goal)
