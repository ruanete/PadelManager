# Instalación, ejecución y uso en local
Para realizar la compilación del proyecto debemos de situarnos en la carpeta **"padelmanager"** del proyecto y ejecutamos el siguiente comando:

```
mvn clean install
```

Con este comando se compilará el proyecto y se ejecutarán los test. Una vez compilado y ejecutados los test se debe ejecutar el proyecto mediante el siguiente comando situandonos antes en el directorio **"target"** dentro del que ya hemos mencionado anteriormente **"padelmanager"**:

```
java -jar padelmanager-0.0.1-SNAPSHOT.jar
```

Una vez ejecutado podemos ver la documentación de la REST API ingresando en la dirección siguiente:

```
http://localhost:8080/swagger-ui.html
```

Y para realizar peticiones al microservicio una vez desplegado es tan sencillo como realizar peticiones a las direcciónes **/api/{opción}**, en este caso pongo el ejemplo de aquellas que devuelven el contenido de las respectivas tablas realizando una petición **GET**:

```
http://localhost:8080/api/player
http://localhost:8080/api/match
http://localhost:8080/api/track
http://localhost:8080/api/reservation
```

En la documentación de la REST API se pueden encontrar las diferentes peticiones posibles que se pueden realizar.

#### Bibliografía
[Spring Boot 2 RESTful API Documentation With Swagger 2 Tutorial](https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg)
