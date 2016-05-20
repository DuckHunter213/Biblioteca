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
	`folioReservación` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(10) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificador` VARCHAR(0),
	CONSTRAINT `PK_Reservados` PRIMARY KEY (`folioReservación`)
)
;

CREATE TABLE `Multas`
(
	`montoMulta` VARCHAR(10) NOT NULL,
	`folioMulta` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificador` VARCHAR(0),
	CONSTRAINT `PK_Multas` PRIMARY KEY (`folioMulta`)
)
;

CREATE TABLE `Devolucion`
(
	`fechaDevolucion` DATE NOT NULL,
	`folioDevolucion` VARCHAR(10) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`identificador` VARCHAR(0),
	CONSTRAINT `PK_Devolucion` PRIMARY KEY (`folioDevolucion`)
)
;

CREATE TABLE `Prestamos`
(
	`fechaPrestamo` DATE NOT NULL,
	`folioPrestamo` VARCHAR(10) NOT NULL,
	`identificadorItem` VARCHAR(10) NOT NULL,
	`identificadorUsuario` VARCHAR(15) NOT NULL,
	`fechaFinPrestamo` DATE NOT NULL,
	`identificador` VARCHAR(10) NOT NULL,
	CONSTRAINT `PK_Prestamos` PRIMARY KEY (`folioPrestamo`)
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
	`folioPrestamo` VARCHAR(0),
	CONSTRAINT `PK_Usuarios` PRIMARY KEY (`identificador`)
)
;

CREATE TABLE `Items`
(
	`tiempoPrestamo` DATE NOT NULL,
	`autor` VARCHAR(50),
	`titulo` VARCHAR(150) NOT NULL,
	`identificador` VARCHAR(10) NOT NULL,
	`fechaAdquisicion` DATE,
	`costoMulta` FLOAT(24) NOT NULL,
	`fechaPublicacion` DATE,
	`categoria` VARCHAR(50) NOT NULL,
	`folioPrestamo` VARCHAR(0),
	`folioReservación` VARCHAR(0),
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

SET FOREIGN_KEY_CHECKS=1
