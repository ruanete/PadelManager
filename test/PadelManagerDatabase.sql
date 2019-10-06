/*Change root password to use the same BD as in local*/
use mysql;
SET GLOBAL validate_password.LENGTH = 4;
SET GLOBAL validate_password.policy = 0;
SET GLOBAL validate_password.mixed_case_count = 0;
SET GLOBAL validate_password.number_count = 0;
SET GLOBAL validate_password.special_char_count = 0;
SET GLOBAL validate_password.check_user_name = 0;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';

/*Create database*/
CREATE DATABASE IF NOT EXISTS `PadelManager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE IF NOT EXISTS `PadelManagerTest` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
