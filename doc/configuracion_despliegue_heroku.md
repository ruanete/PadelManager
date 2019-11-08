# Configuración y despliegue en PaaS. Heroku.
La plataforma como servicio en este caso que he usado para desplegar el proyecto es [Heroku](https://www.heroku.com) debido a su sencillez a la hora del despliegue, por que permite usarlo de forma gratuita y por su integración directa con GitHub. Lo pasos que he realizado para realizar el despliegue en dicho PaaS son los siguiente:

1. Primero vamos a la web de [Heroku](https://www.heroku.com) y nos registramos, si ya tenemos cuenta tan solo hay que iniciar sesión. Seguidamente sincronizamos nuestra cuenta de GitHub con Heroku para poder tener acceso a los repositorios de forma más rapida.
2. El siguiente paso es instalar en nuestra máquina en local [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli), para ello instalamos en Ubuntu en mi caso haciendo uso del siguiente comando:
```
sudo snap install --classic heroku
```

3. Una vez instalado tenemos que iniciar sesión desde nuestra máquina en local, para ello usaremos el comando siguiente que nos llevara a la web de **Heroku** en la que iniciaremos sesión, esto no es más que para identificarnos y poder acceder y crear nuestros proyectos, para ello realizamos:
```
heroku login
```

4. Una vez loggeado, lo siguiente sería generar un nuevo proyecto, para ello y estando siempre en la raíz de este repositorio (entendemos por ello que ha sido clonado en su local) realizamos:
```
heroku create [nombre_proyecto]
```
Un ejemplo sería:
```
heroku create padelmanageriv
```

5. Una vez generado el proyecto, debemos decirle a **Heroku** donde está el proyecto, debido a que en mi caso está en un subdirectorio y no en el directorio raíz, por ello debemos configurar el buildpack de **Heroku** de la siguiente manera:
```
heroku buildpacks:clear -a padelmanageriv
```
Con esto limpiamos el buildpack inicial si es que lo hubiera, lo siguiente sería setear un buildpack de un repositorio de GitHub que dejaré en la bibliografía el cual realiza el cambio de directorio al directorio del proyecto, luego añadiremos el buildpack del lenguaje que hemos usado en este caso Java y por ultimo setearemos una variable de entorno en **Heroku** para que sepa donde se encuentra el subdirectorio del proyecto, para ello realizamos los siguiente tres comandos:
```
heroku buildpacks:set https://github.com/timanovsky/subdir-heroku-buildpack -a padelmanageriv
```
```
heroku buildpacks:add heroku/java -a padelmanageriv
```
```
heroku config:set PROJECT_PATH=padelmanager -a padelmanageriv
```
Con esto ya tenemos configurado donde se realizará la build, lo siguiente sería configurar la variable de entorno que especifica la clave para que la contraseña de MySQL pueda ser descifrada, para ello realizamos lo siguiente:
```
heroku config:set DECRYPT_KEY=[clave_de_descifrado] -a padelmanageriv
```
Esta clave no se puede hacer pública, si es necesaría para ejecutar este proyecto contactar con el desarrollador. Esta clave es la que generamos cuando explicamos en [Instalación, ejecución y uso en producción. Explicación de perfiles.](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion_prod.md).

6. El fichero que realiza la ejecución correcta del proyecto se encuentra en la carpeta del proyecto, debería de ir en la raíz del repositorio, pero debido a que tengo el proyecto en un subdirectorio he tenido que mover dicho fichero al subdirectorio **padelmanager**, este fichero se llama **Procfile** y es el que realiza la ejecución de la build una vez generada y el que finalmente ejecuta la instancia, este fichero ya está incluido en el proyecto, pero la línea que usa es la siguiente:
```
web: java -Dserver.port=$PORT -Dspring.profiles.active=prod -Djasypt.encryptor.password=$DECRYPT_KEY -jar target/padelmanager-0.0.1-SNAPSHOT.jar
```
En el que podemos observar como le pasamos la contraseña (haciendo uso de la variable de entorno antes agregada) para que pueda hacer uso de la base de datos que tenemos alojada en Google Cloud.

7. Una vez tenemos todo lo dicho configurado tan solo nos queda activar los despliegues automáticos de nuestro repositorio, para ello tan solo debemos ir a la configuración del proyecto en **Heroku** y en **Deploy** activamos **Automatic Deploys** así cada vez que realizamos push en el repositorio se desplegará. Destacar que para realizar esto antes hay que conectar al repositorio concreto que queremos que se despliegue, eso se encuentra en la misma pestaña de **Deploy** dentro del proyecto en **Heroku**. Destacar activar la opción de **Wait for CI to pass before deploy** para desplegar siempre y cuando se pasen todo los test que realiza **GitHub Actions** y **Travis CI** en el caso de este repositorio.

8. Finalmente para realizar el despliegue del proyecto tan solo debemos realizar el siguiente comando en nuestro local:
```
git push heroku master
```
Como hemos configurado los despliegues automáticos, cuando realicemos un **push** en nuestro repositorio de **GitHub** directamente este desplegará en Heroku sin necesidad de realizar este comando anterior, pero si solo queremos desplegar en **Heroku** y no realizar **push** en **GitHub** si podremos realizarlo.

#### Bibliografía
1. [Heroku: The Procfile](https://devcenter.heroku.com/articles/procfile)
2. [Heroku buildpack to support deployment from subdirectory](https://medium.com/@timanovsky/heroku-buildpack-to-support-deployment-from-subdirectory-e743c2c838dd)
3. [Heroku: Configuration and Config Vars](https://devcenter.heroku.com/articles/config-vars)
4. [Spring Boot: Deploying Spring Boot Applications](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#cloud-deployment-heroku)
