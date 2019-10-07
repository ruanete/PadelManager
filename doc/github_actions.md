# Configuración GitHub Actions
Para realizar la integración continua, además de realizarlo por médio de **Travis CI**, he utilizado [GitHub Actions](https://github.com/features/actions) un servicio que es propio de **GitHub** lo que facilita mucho la integración con el repositorio, además, tiene una forma similar de configurar el fichero de configuración similar a **Travis CI** y como anteriormente había configurado dicho servicio este ha sido replicarlo. El archivo de configuración que he usado es el siguiente:

```
name: PadelManager

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    services:
      mysql:
        image: mysql:8.0
        env:
          MYSQL_ROOT_PASSWORD: root
        ports:
            - 3306
        options: --health-cmd="mysqladmin ping" --health-interval=10s --health-timeout=5s --health-retries=3
    steps:
    - uses: actions/checkout@v1
    - name: Set up JDK 1.8
      uses: actions/setup-java@v1
      with:
        java-version: 1.8
    - name: Verify MySQL connection from host and create databases
      run: |
        mysql -h 127.0.0.1 --port 3306 -u root -proot -e "SHOW DATABASES;"
        mysql -h 127.0.0.1 --port 3306 -u root -proot < test/PadelManagerDatabaseGitHubActions.sql
        mysql -h 127.0.0.1 --port 3306 -u root -proot -e "SHOW DATABASES;"
    - name: Build with Maven
      run: |
        cd padelmanager
        mvn clean install
```

En este fichero de configuración al igual que con **Travis CI** se ha configurado el servicio de **MySQL**, **Java** y la ejecución del propio proyecto. Lo que voy a destacar a diferencia de **Travis CI** es que al principio del fichero aparece la sentencia **"on: [push]"**, sentencia que le dice a GitHub Actions que realice todo el proceso siempre y cuando se realice un **push** en el respositorio, esto es configurable a cualquier tipo de evento. Otra curiosidad de este fichero de configuración es el uso de **- name:** sentencia que realiza el procedimiento siguiente **run** en un paso de ejecución, esto simplemente puede servir para que el usuario final sepa en que paso del procedimiento ha podido falla o cuales son aquellos que se han ejecutado sin problema.

#### Bibliografía
1. [Github actions: Using mysql service throws access denied for user 'root'@'localhost'](https://stackoverflow.com/questions/58222386/github-actions-using-mysql-service-throws-access-denied-for-user-rootlocalh)
2. [GitHub Actions CI/CD with Yarn, Composer, phpcs, phpunit and built branches](https://bigbite.net/insights/github-actions-ci-cd-with-yarn-composer-phpcs-phpunit-and-built-branches/)
