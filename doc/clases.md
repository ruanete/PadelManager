# Clases proyecto
En este proyecto hago uso de varios paquetes en Java y en dichos paquetes cuatro clases cada uno, haciendo referencia a las cuatro tablas que se encuentran en la base de datos. Basicamente existen tres tipos de clases: dominio, repositorio y controlador.

* Las clases que se encuentran en el paquete **domain** son aquellas clases que representan la tabla en la base de datos, es decir, cada atributo de la clase suele corresponder a una columna de la tabla en la base de datos, a veces pueden ocurrir que aparezca un atributo que sea una relación y no se vea directamente reflejado como una columna en la base de datos. En el caso de este proyecto se encuentran las clases:

  * **Player:** Representa un jugador del partido de padel (Match), está constituido por el nombre del jugador, el email y la lista de partidos en las que juega o ha jugado.

  * **Match:** Representan un partido de padel, con los sets resultados de los jugadores 1 y 2, los sets resultados de los jugadores 3 y 4 así como la lista de jugadores (Player) que conforman un partido.

  * **Track:** Representan una pista de padel en la que se jugaran partidos. Esta está constituida por el número de la pista y un booleano para saber si esa pista está funcionando o no.

  * **Reservation:** Representa una reserva. La reserva se constituye de un partido, una pista, el precio y la fecha de check in y check out.

* Las clases que se encuentran en el paquete **repository** son aquellas clases que se encargan de gestionar todas las operaciones de persistencia contra una tabla de la base de datos, de ahí que exista un repositorio por tabla en la base de datos. Existen cuatro repositorios en este microservicio que son los que corresponden a los dominios anteriormente especificados.

* Las clases que se encuentran en el paquete **controller** son aquellas clases que se encargan de gestionar las solicitudes HTTP entrantes del usuario y devuelve una respuesta adecuada. Existen cuatro controladores correspondientes a las cuatro tablas de la base de datos:

  * **Player:** Actualmente existen en ella los métodos GET, POST y PUT para listar, añadir y editar un jugador. Será mejorado en siguientes versiones, añadiendo por ejemplo DELETE o PATCH para ciertas columnas de la tabla.

  * **Match:** Actualmente existen en ella los métodos GET, POST y PUT para listar, añadir y editar un partido. Será mejorado en siguientes versiones, añadiendo por ejemplo DELETE o PATCH para ciertas columnas de la tabla.

  * **Track:** Actualmente existen en ella los métodos GET, POST y PUT para listar, añadir y editar una pista. Será mejorado en siguientes versiones, añadiendo por ejemplo DELETE o PATCH para ciertas columnas de la tabla.

  * **Reservation:** Actualmente existen en ella los métodos GET, POST y PUT para listar, añadir y editar una reserva. Será mejorado en siguientes versiones, añadiendo por ejemplo DELETE o PATCH para ciertas columnas de la tabla.

Por ultimo, hay que tener en cuenta los paquetes en el que se encuentran los test del microservicio, estos se encuentran en la ruta **src/test/java** y dentro de esta ruta deberan aparecen los mismos paquetes que hemos tratado anteriormente, pero esta vez lo que realizan son los test, por ejemplo los test de las peticiones GET de la REST API.
