# Documentación API REST
La documentación de la API REST puede encontrarse accediendo a la siguiente dirección cuando el proyecto este ejecutandose, ya que se generará actualizada e incluso con la posibilidad de hacer uso de ella:
```
http://localhost:8080/swagger-ui.html
```

Para ello lo que he usado es [Swagger](https://swagger.io/) que es una herramienta muy util para describir, producir, consumir y visualizar una API REST. Además una utilidad muy buena de esta herramienta es que siempre estará actualizado junto con el código.

Además, una vez desplegado correctamente la aplicación, se puede hacer uso de un endpoint que devolverá si se ha desplegado correctamente este endpoint se trata de realizar un GET a:

```
http://localhost:8080/status
```
Este devolverá si se ha desplegado correctamente o no, la respuesta que devuelve es la siguiente:
```
// 20191025165321
// http://localhost:8080/status

{
  "status": "OK"
}
```

Destacar finalmente, que se han tratado errores posibles dentro de las propias peticiones, es decir, si por ejemplo queremos añadir un elemento en el que un parametro no puede existir ya en la base de datos, la respuesta a la petición devolverá un código de error y el por que se ha producido, para que así el usuario sepá el motivo del error y no sea un simple código.

Por ejemplo, al realizar la llamada al endpoint **GET /api/track** la respuesta que nos devuelve es:

```
// 20191025165557
// http://localhost:8080/api/track

{
  "success": true,
  "message": "List of tracks got.",
  "list": [
    {
      "id": 1,
      "trackNumber": 1,
      "working": true
    }
  ]
}
```

Como podemos observar se devuelve **success** que significa si se ha realizar o no correctamente, un **message** con una pequeña aclaración y finalmente una lista con los resultado, en caso de que por ejemplo en este caso, no existan valores en la base de datos devolvería lo siguiente:

```
// 20191025165744
// http://localhost:8080/api/track

{
  "success": false,
  "message": "Not exits tracks in database, you can test add one track.",
  "list": [

  ]
}
```

Todas las llamadas a los endpoints devolverán siempre si se han realizado correctamente o no, un mesaje aclaratorio y los resultados en el caso en el que tenga que devolverlo.
