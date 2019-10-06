/*Change root password to use the same BD as in local*/
use mysql;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Ruanete1997.';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root' WITH GRANT OPTION;

/*Create database*/
CREATE DATABASE IF NOT EXISTS `PadelManager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE IF NOT EXISTS `PadelManagerTest` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
