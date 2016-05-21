Mysql -u root

CREATE database biblioteca;

use biblioteca;

SET FOREIGN_KEY_CHECKS=0

DROP TABLE IF EXISTS `Reservados` CASCADE
;

DROP TABLE IF EXISTS `Multas` CASCADE
;

DROP TABLE IF EXISTS `Devolucion` CASCADE
;

DROP TABLE IF EXISTS `Prestamos` CASCADE
;

DROP TABLE IF EXISTS `Usuarios` CASCADE
;

DROP TABLE IF EXISTS `Items` CASCADE
;

CREATE TABLE `Reservados`
(
	`folioReservacion` VARCHAR(15) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	CONSTRAINT `PK_Reservados` PRIMARY KEY (`identificadorItem`)
)
;

CREATE TABLE `Multas`
(
	`montoMulta` VARCHAR(10) NOT NULL,
	`folioMulta` VARCHAR(15) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	CONSTRAINT `PK_Multas` PRIMARY KEY (`folioMulta`)
)
;

CREATE TABLE `Devolucion`
(
	`fechaDevolucion` DATE NOT NULL,
	`folioDevolucion` VARCHAR(15) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	CONSTRAINT `PK_Devolucion` PRIMARY KEY (`folioDevolucion`)
)
;

CREATE TABLE `Prestamos`
(
	`fechaPrestamo` DATE NOT NULL,
	`folioPrestamo` VARCHAR(15) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`fechaFinPrestamo` DATE NOT NULL,
	CONSTRAINT `PK_Prestamos` PRIMARY KEY (`identificadorItem`)
)
;

CREATE TABLE `Usuarios`
(
	`matricula` VARCHAR(10) NOT NULL,
	`estado` VARCHAR(10) NOT NULL,
	`identificador` VARCHAR(15) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`primerApellido` VARCHAR(50),
	`segundoApellido` VARCHAR(50),
	CONSTRAINT `PK_Usuarios` PRIMARY KEY (`identificador`)
)
;

CREATE TABLE `Items`
(
	`tiempoPrestamo` int NOT NULL,
	`autor` VARCHAR(50),
	`titulo` VARCHAR(150) NOT NULL,
	`identificador` VARCHAR(10) NOT NULL,
	`fechaRegistro` DATE,
	`costoMulta` FLOAT(24) NOT NULL,
	`categoria` VARCHAR(50) NOT NULL,
	CONSTRAINT `PK_Items` PRIMARY KEY (`identificador`)
)
;

ALTER TABLE `Reservados` 
 ADD CONSTRAINT `FK_Reservados_Items`
	FOREIGN KEY (`identificador`) REFERENCES `Items` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Reservados` 
 ADD CONSTRAINT `FK_Reservados_Usuarios`
	FOREIGN KEY (`identificador`) REFERENCES `Usuarios` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Multas` 
 ADD CONSTRAINT `FK_Multas_Items`
	FOREIGN KEY (`identificador`) REFERENCES `Items` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Multas` 
 ADD CONSTRAINT `FK_Multas_Usuarios`
	FOREIGN KEY (`identificador`) REFERENCES `Usuarios` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Devolucion` 
 ADD CONSTRAINT `FK_Devolucion_Items`
	FOREIGN KEY (`identificador`) REFERENCES `Items` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Devolucion` 
 ADD CONSTRAINT `FK_Devolucion_Usuarios`
	FOREIGN KEY (`identificador`) REFERENCES `Usuarios` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Prestamos` 
 ADD CONSTRAINT `FK_Prestamos_Items`
	FOREIGN KEY (`identificador`) REFERENCES `Items` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Prestamos` 
 ADD CONSTRAINT `FK_Prestamos_Usuarios`
	FOREIGN KEY (`identificador`) REFERENCES `Usuarios` (`identificador`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1;



INSERT INTO `Usuarios` VALUES(	
	'S14011614',
	'Activo',
	'IDENTIFICADORA1',
	'Waffles',
	'McCartney',
	'Warf'
);
INSERT INTO `Usuarios` VALUES(	
	'S14011615',
	'Activo',
	'IDENTIFICADORA2',
	'Galleta',
	'McCornic',
	'Warf'
);
INSERT INTO `Usuarios` VALUES(	
	'S14011616',
	'Activo',
	'IDENTIFICADORA3',
	'Cho',
	'El loco',
	'Gomez'
);
INSERT INTO `Usuarios` VALUES(	
	'S14011617',
	'Activo',
	'IDENTIFICADORA4',
	'Gerardo',
	'Mares',
	'Solano'
);
INSERT INTO `Usuarios` VALUES(	
	'S14011618',
	'Activo',
	'IDENTIFICADORA5',
	'Cho',
	'Xalaquia',
	'Tolstoi'
);



INSERT INTO `Items` VALUES (
	'10',
	'Wiegers',
	'Software requirements',
	'identif001',
	'2016-05-02',
	'10.0',
	'Libro'
);
INSERT INTO `Items` VALUES (
	'10',
	'Erich Gamma',
	'Design Pattern',
	'identif002',
	'2016-05-02',
	'10.0',
	'Libro'
);
INSERT INTO `Items` VALUES (
	'10',
	'Albin Wiley',
	'The art of software architecture',
	'identif003',
	'2016-05-02',
	'10.0',
	'Libro'
);
INSERT INTO `Items` VALUES (
	'10',
	'Somerville',
	'Software Engineering',
	'identif004',
	'2016-05-02',
	'10.0',
	'Libro'
);
INSERT INTO `Items` VALUES (
	'10',
	'joy Beatty',
	'Planning elicitation',
	'identif005',
	'2016-05-02',
	'10.0',
	'Libro'
);



INSERT INTO `reservados` VALUES (
	'0160520234746',
	'IDENTIFICADORA6',
	'identif004'
);
INSERT INTO `prestamos` VALUES (
	'2016-05-20',
	'0160520234746',
	'identif004',
	'IDENTIFICADORA6',
	'2016-05-29'
);