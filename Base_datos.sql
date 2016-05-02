mysql -u root

CREATE DATABASE `Biblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;

use Biblioteca;

create table libro(
	numeroPaginas int(3),
	tiempoPrestamo datetime,
	numeroEdicion varchar(100),
	autor varchar(100),
	titulo varchar(100) not null,
	codigoBarras varchar(300) not null,
	identificador varchar(50) not null primary key,
	fechaAdquisicion datetime ,
	costoMulta int(4) not null,
	fechaPublicacion datetime 
);

create table Usuario(
	primary key matricula varchar(10),
	varchar estado(),
	varchar nombre(),
	date fechaRegistro(),
	date periodoValidez()
);


drop database Biblioteca;


create database item(
	autor varchar(100),
	titulo varchar(100),
	codigoBarras varchar(300),
	primary key identificador varchar(50),
	fechaAdquisicion date(),
	costoMulta int(),
	fechaPublicacion date()
);