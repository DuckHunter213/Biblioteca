delimiter //
create procedure consulta(IN folio varchar(15))
	begin
	select * from prestamo where folioPrestamo = folio:
	select * from resservado where folioReservacion = folio;
	end
//

delimiter ;

delimiter //
create procedure borrarDatoDeTabla(IN tabla varchar(20). IN folio varchar(15))
	begin
	if tabla == 'prestamo' then
	DELETE * from prestamo where folioPrestamo = folioPrestamo:
	else
	DELETE * from resservado where folioReservacion = folioReservacion;
	end if
	end
//

delimiter ;

delimiter //
create procedure insertarDatoDeTabla(IN tabla varchar(20). IN folio varchar(15))
	begin
	if tabla == 'prestamo' then
INSERT INTO `prestamos` VALUES (
	'2016-05-20',
	'0160520234746',
	'identif004',
	'IDENTIFICADORA6',
	
	else

INSERT INTO `devolucion` VALUES (
	'2016-05-20',
	'0160520234746',
	'identif004',
	'IDENTIFICADORA6',
	
	end if
	end
//

delimiter ;

delimiter //
create procedure prestamo(
	IN fechaFinPrestamo date,
	IN identificadorItem varchar(10),
	IN identificadorUsuario varchar(10),
	IN folioPrestamo varchar(15)
	IN accion varchar(10)
)
	begin
	if (accion == borrarPrestamo) then
	DELETE from prestamos where folioPrestamo == 
	end
//