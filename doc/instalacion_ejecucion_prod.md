# Instalación, ejecución y uso en producción. Explicación de perfiles.
He generado tres tipos de perfiles que pueden ejecutar el proyecto: default, prod y test. El primero de ellos es el perfil por defecto, se usa una base de datos en local en MySQL, con credenciales simples y que no son necesarios de ocultar, ya que la base de datos es en local. El segundo perfil es generado para su uso en producción, este hace uso de una base de datos que se encuentra en Google Cloud, debido a que esta base de datos tiene acceso cualquier persona a través de su IP y haciendo uso de los respectivos credenciales, la contraseña para tener acceso a ella está cifrada y se hará uso de un argumento pasado como variable en el comando de ejecución para así evitar que esta sea pública. El tercer perfil es el que se viene usando hasta ahora simplemente se usa para pasar los test y hace uso también de una base de datos en local en MySQL la cual solo será usada para verificar con los test todo el proyecto.

Para la ejecución en local se puede seguir la anterior guía expuesta en [Instalación, ejecución y uso en local](https://github.com/ruanete/PadelManager/tree/master/doc/instalacion_ejecucion.md).

Para la ejecución a la que llamamos producción, que básicamente es por el uso de una base de datos que no es local, se debe realizar lo siguiente:

```
mvn spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="--jasypt.encryptor.password=contraseña_mysql"
```

Es el mismo comando que se usa para la ejecución en local, pero añadiendole el tipo de perfil que vamos a usar, en este caso **prod** y pasandole un texto con el que ciframos la contraseña que tenemos encriptada en el **application-prod.properties** de la aplicación. Destacar que donde pone **contraseña_mysql** hay que añadir el texto con el que ciframos la contraseña, haciendo uso de [Jasypt](http://www.jasypt.org/).

Para parar la ejecución podemos realizar igual que como haciamos en local (siempre dentro de la carpeta del proyecto **padelmanager**):
```
mvn spring-boot:stop
```

Voy a explicar como he hecho para cifrar la contraseña de Mysql. Para cifrar la contraseña se necesita un texto que se usará para cifrarla y descifrarla y la propia contraseña, realizamos el siguiente procedimiento:

```
./encrypt.sh input=textoCifrar password=contrasena_mysql
```

Y el resultado es el siguiente:

```
----ENVIRONMENT-----------------

Runtime: Private Build OpenJDK 64-Bit Server VM 25.222-b10



----ARGUMENTS-------------------

input: textoCifrar
password: contrasena_mysql



----OUTPUT----------------------

TsyWhiYFAadxI4wAbFd1UqhQhvd2xkeK

```

Como podemos observar la contraseña ("textoCifrar") usando el texto ("contrasena_mysql") nos devuelve una contraseña cifrada con valor: **TsyWhiYFAadxI4wAbFd1UqhQhvd2xkeK** que es el que usaremos en nuestro fichero de **application-prod.properties** para que luego pasandole el texto **contrasena_mysql** pueda descifrarlo. Esto no deja de ser un ejemplo, la contraseña final real no es la que he propuesto aquí.

#### Bibliografía
1. [Spring Boot: How to encrypt properties in application.properties](http://mbcoder.com/spring-boot-how-to-encrypt-properties-in-application-properties/)
2. [Get command-line arguments from spring-boot:run](https://stackoverflow.com/questions/23316843/get-command-line-arguments-from-spring-bootrun)
