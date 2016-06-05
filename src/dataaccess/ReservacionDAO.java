package dataaccess;

import Dominio.Item;
import java.sql.SQLException;

/**
 *
 * @author Francisco Gerardo Mares Solano
 */
public interface ReservacionDAO {
    /**
     * Quita el item buscado previamente de una reservación
     * @param identificador es el parametro por el cual sera quitado el item de
     * una reservación
     * @return regresa una retroalimentación //TODO
     * @throws java.sql.SQLException
     */
    public int quitarItemDeReservacion(String identificador) throws SQLException;

    /**
     * Reserva el item pasado se asocia a una matricula de usuario y se genera
     * en un folio que es la referecia de esa reservación
     * @param item item que sera reservado 
     * @param identificadorUsuario 
     * @return Regresa una retroalimentación al usuario //TODO
     * @throws java.sql.SQLException
     */
    public int reservarItem(Item item, String identificadorUsuario) throws SQLException;

}
