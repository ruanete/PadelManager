# Creación de imagen Docker y uso en local. Uso de DockerHub.
Para la creación de un contenedor del proyecto he usado [Docker](https://www.docker.com/). Para la creación de un contenedor **Docker** lo primero que hay que realizar es la creación de una imagen la cual luego será usada para su despliegue, ya sea en nuestra propia máquina en local o en la propia nube que es lo que realizaremos después. Lo primero que se ha realizado es la creación del fichero de configuración para la creación de la imagen **Docker** del proyecto, en este caso, el fichero de configuración se encuentra en la raíz del repositorio, para luego también ser usado por **Google Cloud** que es donde desplegaremos y es leido desde la raíz del mismo, dicho fichero, tiene el nombre de **Dockerfile** y tiene el siguiente contenido en este caso:
```
FROM maven:3.5-jdk-8 AS build
ARG DECRYPT_KEY
COPY padelmanager /padelmanager
WORKDIR padelmanager
EXPOSE 8080
CMD mvn spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="--jasypt.encryptor.password=$DECRYPT_KEY,--server.port=8080"
```
Esta configuración lo que realiza es el uso de una imagen de **Maven** que es lo que observamos cuando se realiza **FROM maven:3.5-jdk-8 AS build**, esto es debido a que este proyecto hace uso de **Maven** para su ejecución. Se añade un parametro **ARG DECRYPT_KEY** que no es más que una variable, que como ya expliqué en [Configuración y despliegue en PaaS. Heroku.](https://github.com/ruanete/PadelManager/tree/master/doc/configuracion_despliegue_heroku.md) para pasar un clave que sirve para el dewcifrado de la contraseña de la base de datos, esta variable será usada finalmente cuando realizamos la ejecución del proyecto haciendo uso de **Maven**. Lo siguiente que realiza el **Dockerfile** es la copia del directorio padelmanager (que se encuentra en la raíz del repositorio) al directorio padelmanager dentro del contenedor para que así desde dentro del contenedor se pueda hacer uso del código del mismo. Lo siguiente es la selección del directorio anterior de dentro del contenedor como el directorio de trabajo, para así no tener que movernos entre directorio cuando realicemos el comando para levantar el servicio. Por ultimo con **CMD mvn spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="--jasypt.encryptor.password=$DECRYPT_KEY,--server.port=8080"** lo que le decimos es que cuando se ejecute un contenedor con dicha imagen se ejecute este comando que no es más que el comando que ejecuta el servicio y que ya fué explicado en [Instalación, ejecución y uso en producción. Explicación de perfiles.](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion_prod.md)

Para generar dicha imagen en local podemos realizar el siguiente comando:
```
docker build -t padelmanager .
```

Y para hacer uso de esa imagen y desplegar un contenedor debemos realizar:
```
docker run -d --name padelmanager -p 8080:8080 -e DECRYPT_KEY=contraseñaBD padelmanager
```

Otra forma de generar dicho contenedor es haciendo uso de la imagen de **Dockerhub** (explicado a continuación) realizando lo mismo, pero haciendo uso de la imagen de **Dockerhub** y no de la generada localmente:
```
docker run -d --name padelmanager -p 8080:8080 -e DECRYPT_KEY=contraseñaBD ruanete/padelmanager
```

Esto descargará la imagen directamente desde **Dockerhub** sin necesidad de generarla localmente.

Otra forma que he añadido es haciendo uso de un fichero **docker-compose.yml**, estando en la raíz del repositorio y si añadimos como variable de entorno la clave de descifrado de la base de datos podremos desplegar localmente un contenedor con la imagen del proyecto directamente, para ello realizamos:
```
export DECRYPT_KEY=contraseñaBD
```
```
docker-compose up -d
```

Este fichero **docker-compose.yml** contiene:
```
version: '2'
services:
  padelmanager:
    image: ruanete/padelmanager
    container_name: padelmanager
    environment:
      - DECRYPT_KEY=$DECRYPT_KEY
    ports:
      - '8080:8080'
```

Este lo que hace es levantar el servicio con nombre "padelmanager", se descarga la imagen **ruanete/padelmanager** de **Dockerhub**, setea la variable de entorno **DECRYPT_KEY** y finalmente el mapeo de puertos desde la máquina host al contenedor.

Con esto ya podremos acceder a la dirección **localhost:8080** para acceder al servicio ya desplegado en local.

Siendo **contraseñaBD** la clave para descrifrar la contraseña de la base de datos, que no es añadida a la documentación por seguridad, puede ser pedida al desarrollador.

Para poder hacer uso de ella directamente a través de un repositorio en [Dockerhub](https://hub.docker.com/) y no tener que generarla cada vez que la queramos usar vamos a configurar la automatización de la creación de la imagen y subida de la misma al repositorio de **Dockerhub** cuando se realice push en el repositorio de Github. Los pasos a realizar son los siguiente:
1. Creación de una cuenta en [Dockerhub](https://hub.docker.com/) y creación de un repositorio, para ello nos vamos a nuestro perfil creamos un repositorio, le ponemos nombre, descripción, lo conectamos a nuestra cuenta de Github y elegimos el repositorio a usar.
2. Elegimos el tipo de origen en nuestro caso una rama o "Branch", en **Source** elegimos master, como docker tag "latest", en "Dockerfile location" ponemos Dockerfile, en "Build Context" elegimos la ruta "/" que es la del repositorio, activamos "Autobuild" y "Build caching".
3. Guardamos y con esto ya tendriamos **Dockerhub** configurado para que en cuanto se realice push en la rama Master del repositorio de Github se autogenera la imagen y se tenga automaticamente actualizada en este repositorio.
