# Tests REST API
Los test para la comprobación de la REST API han sido realizados en [Spring Boot Starter Test](https://www.baeldung.com/spring-boot-testing) que no es más que que una versión específica de **Spring Boot** que hace uso de [JUnit](https://junit.org/junit5/). Estos test como bien ya especifico en la [documentación de Travis](https://github.com/ruanete/PadelManager/tree/master/doc/travis.md) se encuentran en **padelmanager/src/test/java/com/ruanete/padelmanager/controller**.

Estos test se realizan usando una base de datos de test en la que se insertan valores previamente y teniendo en cuenta que sabemos esos valores, dentro de los diferentes test lo que vamos a realizar es probar todos los endpoints leyendo, añadiendo, modificando y borrando datos y comprobando con los valores que ya sabemos de nuestra base de datos de test.

Un ejemplo, de test, muy sencillo que comprueba si la petición GET de la REST API funciona correctamente es el siguiente:

```
@Test
public void getAllPlayers() {
  assertEquals(4, playerController.allPlayers().getList().size());
}
```

En nuestra base de datos de test tenemos introducidos inicialmente 4 valores, luego la comprobación de este test será que cuando realicemos una petición GET nos devuelva una lista con 4 valores.


Otro test que se realiza es el de la petición POST, la cual crear una nueva entrada en la tabla correspondiente, el código sería el siguiente:

```
@Test
public void newPlayer() {
	Player player = new Player();
	player.setEmail("test@gmail.com");
	player.setName("Nombre Test");

	playerController.newPlayer(player);
	assertEquals(5, playerController.allPlayers().getList().size());

	List<Player> list = (List<Player>) playerController.allPlayers().getList();
	assertEquals(player.getEmail(), list.get(list.size()-1).getEmail());
	assertEquals(player.getName(), list.get(list.size()-1).getName());
}
```

En este caso lo que se realiza es creamos un nuevo objeto del tipo de la base de datos y le damos valor a todos sus atributos. Lo siguiente sería guardar dicho valor en la base de datos haciendo uso del endpoint respectivo y comprobamos que si realizamos la petición GET para traernos la lista de entradas en la base de datos tenga un tamaño una unidad mayor, es decir, la tabla aumenta en una entrada debido al guardado anteriormente usado. Lo siguiente que se comprobaría es que los datos guardados son los correctos, para ello como tenemos la lista con todos los valores de la tabla, cogemos el ultimo valor y comprobamos que los valores se corresponden a los introducidos inicialmente. En cuanto a la petición PUT es exactamente igual que POST a diferencia que se comprueba antes del guardado que el id que se pasa como argumento al endpoint exista previamente en la base de datos.

Finalmente, cuando realizamos la petición DELETE lo que se comprueba es que la tabla has disminuido su tamaño en una unidad, justo al contrario que POST,
como ejemplo:

```
@Test
public void deletePlayer() {
	List<Player> list = (List<Player>) playerController.allPlayers().getList();
	Player player = list.get(list.size()-1);
	int tam = list.size();

	playerController.deletePlayer(player.getId());

	assertEquals(tam - 1, playerController.allPlayers().getList().size());
}
```

Como vemos primero se realiza la petición GET para traernos todos los valores de la tabla, nos quedamos con el ultimo y el tamaño en ese momento de la tabla, seguidamente se llama al endpoint DELETE y se comprueba al final que el tamaño (inicialmente guardado) ha disminuido en una unidad.

Esto se lleva a cabo en todos los endpoints, pongo como ejemplo uno de ellos debido a que los de las diferentes clases tiene poca diferencia, diferencia que puede ser debida a la cantidad de atributos que tenga la tabla a la que el endpoint se dirige.

#### Bibliografía
[Testing en Spring Boot con JUnit 4\5. Mockito, MockMvc, REST Assured, bases de datos embebidas](https://danielme.com/2018/11/26/testing-en-spring-boot-con-junit-45-mockito-mockmvc-rest-assured-bases-de-datos-embebidas/)
