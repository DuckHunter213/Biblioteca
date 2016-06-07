package dataaccess;

import dominio.Reservacion;
import java.sql.SQLException;

/**
 *
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/16
 */
public interface ReservacionDAO{
    /**
     * Quita el item buscado previamente de una reservación en dado caso que el
     * identifiacador no este asociado a un prestamo, mandara una
     * SQLException.
     *
     * @param identificador es el parametro por el cual sera quitado el item de
     * una reservación
     * @return regresa una retroalimentación //TODO
     * @throws java.sql.SQLException
     */
    public int quitarItemDeReservacion(String identificador) throws SQLException;

    /**
     * Reserva el item pasado se asocia a una matricula de usuario y se genera
     * en un folio que es la referecia de esa reservación
     *
     * @param reservacion
     * @return Regresa una retroalimentación al usuario //TODO
     * @throws java.sql.SQLException
     */
    public int reservarItem(Reservacion reservacion) throws SQLException;

}
