BEGIN;
DELETE FROM reservation;
DELETE FROM rel_match_player;
DELETE FROM matches;
DELETE FROM player;
DELETE FROM track;
 
INSERT INTO `track` (`id`, `track_number`, `working`) VALUES
    (1, 1, 1),
    (2, 2, 0),
    (3, 3, 1);

INSERT INTO `player` (`id`,`email`,`name`) VALUES
	(1,'Nombre de la persona 1','email1@correo.es'),
	(2,'Nombre de la persona 2','email2@correo.es'),
	(3,'Nombre de la persona 3','email3@correo.es'),
	(4,'Nombre de la persona 4','email4@correo.es');
	
INSERT INTO `matches` (`id`,`setsp12`,`setsp34`) VALUES
	(1,2,3),
	(2,3,2),
	(3,3,0);

INSERT INTO `rel_match_player` (`match_id`,`player_id`) VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(2,2),
	(2,3),
	(2,4),
	(2,1),
	(3,3),
	(3,4),
	(3,1),
	(3,2);

INSERT INTO `reservation` (`id`,`check_in_date`,`check_out_date`,`price`,`match_id`,`track_id`) VALUES
	(1,'2019-10-06T11:00:00','2019-10-06T12:30:00',20.0,1,1),
	(2,'2019-10-07T11:00:00','2019-10-07T12:30:00',15.0,2,2),
	(3,'2019-10-08T11:00:00','2019-10-08T12:30:00',18.0,3,3);

COMMIT;
