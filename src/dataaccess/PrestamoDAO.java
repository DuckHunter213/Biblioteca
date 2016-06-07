package dataaccess;

import dominio.Prestamo;
import java.sql.SQLException;

/**
 * Implementación de la clase de prestamoDAO
 *
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public interface PrestamoDAO{

    /**
     * Función que quita el prestamo de la base de datos es decir que va a
     * deshabilitar el prestamo, este estará asosiado a una devolución.
     *
     * @param identificador Se pasa el identificador del perstamo del cual va a
     * ser retirado de la base de datos
     * @return
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar con
     * la base de datos o al tener un error.
     */
    public int quitarPrestamoDeBaseDeDatos(String identificador) throws SQLException;

    /**
     * Función que enlaza un item a un usuario y se guarda un registro en la
     * base de datos.
     *
     * @param prestamo Es un objeto de tipo prestamo valido que se va a guardar
     * en la base de datos.
     * @return regresa 0 si es falso y cualquier otro numero si es valido,
     * se implemento int para la compatibilidad de la comprobación de las
     * demás clases de la capa.
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar
     * con la base de datos o al tener un error.
     */
    public int guardarRegistroDePrestamo(Prestamo prestamo) throws SQLException;

}
