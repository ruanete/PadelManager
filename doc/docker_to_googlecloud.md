# Despliegue de contenedor Docker en Google Cloud. Autodespliegues.
En este caso se va a realizar el despliegue del contenedor Docker del proyecto en **Google Cloud**, para ello y teniendo en cuenta que ya tenemos cuenta en **Google Cloud**, que tenemos instalado en local el Google SDK y conectado con nuestra cuenta de Google, se puede seguir [este tutorial](https://cloud.google.com/sdk/docs/?hl=es) y un proyecto generado vamos a realizar las siguientes configuraciones que van a permitir el despliegue del contenedor Docker así como los autodespliegues cuando se realice "push" en la rama Master de Github. Para poder hacer uso de los contenedores de Google Cloud realizamos los siguientes pasos para poder subir la imagen de Docker generada en local a nuestro repositorio "Container Registry" de Google Cloud:
1. Habilitamos la API de Container Registry entrando en la siguiente dirección donde se podrá activar para nuestro proyecto [Habilitar Container RegistryAPI](https://console.cloud.google.com/flows/enableapi?apiid=containerregistry.googleapis.com&redirect=https%3A%2F%2Fcloud.google.com%2Fcontainer-registry%2Fdocs%2Fquickstart&hl=es&_ga=2.54149160.-1801513465.1570210114)

2. Configuramos gcloud (SDK de Google Cloud) desde local para poder usar docker, para ello realizamos:
```
gcloud auth configure-docker
```
Y pulsamos en "Y" y enter para confirmar.

3. Una vez habilitada y teniendo la imagen de docker generada en local como ya se ha explicado en [Creación de imagen Docker y uso en local. Uso de DockerHub.](https://github.com/ruanete/PadelManager/tree/master/doc/docker.md) realizamos un primer comando para darle un "tag" a la imagen docker que luego será pusheada al repositorio **"Container registry"** de **Google Cloud**:
```
docker tag padelmanager gcr.io/padelmanager/padelmanager
```
Siendo el primer valor padelmanager el nombre de la imagen, el segundo valor de padelmanager el **PROJECT-ID de Google Cloud** y el tercer padelmanager el nombre que tendra la imagen dentro del repositorio de **Google Cloud**.

4. Por ultimo subimos dicha imagen **Docker** al repositorio de **Google Cloud** realizando el siguiente comando:
```
docker push gcr.io/padelmanager/padelmanager
```
Siendo el primer valor de padelmanager el **PROJECT-ID de Google Cloud** y el segundo el nombre que tendra la imagen dentro del repositorio de **Google Cloud**.

En este punto ya tenemos la imagen del contenedor subida a nuestro repositorio de contenedores de Google Cloud lo siguiente sería el despliegue de la misma, este es tan sencillo como realizar los siguientes pasos:
1. Buscamos en Google Cloud, en su buscador, **"Container Registry"**.
2. Buscamos en imágenes nuestra imágen anteriormente subida con nombre **"padelmanager"** y pulsamos encima de ella.
3. Ahora aparecerán todas las imagenes subidas, en este caso probablemente solo haya una, pero conforme subamos imagenes nuevas todas quedaran almacenadas, elegimos la primera y pulsamos encima.
4. Ahora aparecerá un desplegable en el que pone **"Desplegar"** pulsamos en el y seguidamente en **"Desplegar en GC"**.
5. Configuramos el despliegue de la siguiente manera:
  1. Plataforma de despliegue: **Cloud Run en us-central1**
  2. Activamos **"Permitir las invocaciones sin autenticar"**
  3. Pulsamos en **"Configuración opcional de revisión"**
  4. Memoria asignada de **1GiB**
  5. Maximo número de instancias de **1**
  6. Pulsamos en añadir variable y añadimos **DECRYPT_KEY** con valor contraseñaBD (este valor será el que por seguridad no es añadido a este repositorio publicamente).
  7. Pulsamos en **crear** y automaticamente se desplegará, tan solo debemos usar la url que aparecerá arriba en cuanto acabe de desplegar.

Como lo interesante es que se autodespliegue automáticamente siempre que realicemos cambios en la rama Master de nuestro repositorio de Github lo que he realizado es la configuración de Google Cloud para que en cuanto se cambie la rama Master, automáticamente se genere la imagen del contenedor nueva, se suba al repositorio de Google Cloud y finalmente se despliegue, para ello he seguido los siguientes pasos:
1. Primero vamos a dar permisos para poder realizar los despliegues automáticos, para ello vamos [aquí](https://console.cloud.google.com/cloud-build/settings?_ga=2.247492359.-1801513465.1570210114) y en donde aparezca **Cloud Run** ponemos el Status en **Enabled**.
2. En el buscador de Google Cloud buscamos **"Cloud Build"** y vamos **"Activadores"** y arriba aparecerá **"Conectar repositorio"** pulsamos en Github y continuamos.
3. Elegimos nuestro repositorio de Github.
4. Elegimos como **"Build configuration"** la opción **"Cloud Build configuration file"** y añadimos el fichero **"cloudbuild.yaml"** que luego explicaremos con más detalle, se encontrará en la raíz del repositorio de Github.
5. Ahora al realizar **"push"** en Master de Github se autodesplegará el contenedor con la imagen actualizada.

El fichero **"cloudbuild.yaml"** es un fichero que le dice a Google Cloud que pasos debe realizar para generar una nueva imagen, crear el contenedor y finalmente desplegarlo, este fichero se encuentra en la raíz del repositorio y tiene el siguiente contenido:
```
steps:
  # build the container image
- name: 'gcr.io/cloud-builders/docker'
  args: ['build', '-t', 'gcr.io/$PROJECT_ID/padelmanager:${SHORT_SHA}', '.']
  # push the container image to Container Registry
- name: 'gcr.io/cloud-builders/docker'
  args: ['push', 'gcr.io/$PROJECT_ID/padelmanager']
  # deploy container image to Cloud Run
- name: 'gcr.io/cloud-builders/gcloud'
  args: ['beta', 'run', 'deploy', 'padelmanager', '--image', 'gcr.io/$PROJECT_ID/padelmanager:${SHORT_SHA}', '--region', 'us-central1', '--allow-unauthenticated', '--platform', 'managed', '--memory', '1Gi', '--max-instances', '1']
  env:
  - 'PORT=8080'
images:
- gcr.io/$PROJECT_ID/padelmanager
```

Cada "name" no es más que cada uno de los pasos que se realizan para el despliegue final, el primer paso es la creación de la imagen con nombre **padelmanager**. El segundo paso es la subida de dicha imagen al repositorio de "Google Cloud", de ahí que se realice "push". El tercer y ultimo paso es el despliegue final, este procedimiento no es más que el procedimiento que realizamos a mano antes, pero se realizaría de forma automática.

#### Bibliografía
1. [Quickstart for Container Registry](https://cloud.google.com/container-registry/docs/quickstart?hl=es)
2. [Continuous Deployment from git using Cloud Build](https://cloud.google.com/run/docs/continuous-deployment-with-cloud-build#continuous)
3. [Cómo lanzar una aplicación web en Google Cloud Run con Cloud Build](https://carlosazaustre.es/google-cloud-run/)
