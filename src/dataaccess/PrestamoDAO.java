package dataaccess;

import dominio.Prestamo;
import java.sql.SQLException;

/**
 *
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public interface PrestamoDAO {
    
    /**
     *
     * @param identificador
     * @return
     * @throws java.sql.SQLException
     */
    public int quitarItemDePrestamo(String identificador) throws SQLException;

    /**
     * Funcion que enlaza un item a un usuario y se guarda un registro en la 
     * base de datos
     * @param prestamo
     * @return regresa un valor de retroalimentacion //TODO valor booleano
     * @throws java.sql.SQLException
     */
    public int prestarItem(Prestamo prestamo) throws SQLException;
}
