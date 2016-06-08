package dataaccess;

import dominio.Reservacion;
import java.sql.SQLException;

/**
 * Interface con las generalidades para poder registrar una reservación en la
 * base de datos.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public interface ReservacionDAO{

    /**
     * Función que retira la reservación de la base de datos, es decir, que va a
     * concluir la validez de ese registro.
     *
     * @param identificador Se pasa el identificador de la reservación la cual
     * cual va a ser retirado de la base de datos.
     * @return Retorna un entero, 0 índicando que la acción no fue realiza y un
     * entero positivo si no hubo problemas con la transacción Se implementó un
     * valor de retorno int por la compatibilidad de la comprobación de las
     * demás clases de la capa y para poder dar una mejor retroalimentación en
     * conjunto.
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar con
     * la base de datos o al tener un error.
     */
    public int quitarReservacionDeBD(String identificador) throws SQLException;

    /**
     * Método que realiza el registro de la reservación en la base de datos
     *
     * @param reservacion Es un objeto de tipo préstamo valido que se va a
     * guardar en la base de datos.
     * @return regresa 0 si es inválida la transacción y un entero positivo si
     * es válida. Se implementó un valor de retorno int por la compatibilidad de
     * la comprobación de las demás clases de la capa y para poder dar una mejor
     * retroalimentación en conjunto.
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar con
     * la base de datos o al tener un error.
     */
    public int guardarRegistroReservacion(Reservacion reservacion) throws SQLException;

}
