package dataaccess;

import dominio.Prestamo;
import java.sql.SQLException;

/**
 * Interface con las generalidades para poder registrar un préstamo en la base de datos.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public interface PrestamoDAO{

    /**
     * Función que retira el préstamo de la base de datos, es decir, que va a
     * concluir la validez de ese registro, éste estará asosiado a una devolución.
     *
     * @param identificador Se pasa el identificador del préstamo del cual va a
     * ser retirado de la base de datos.
     * @return Retorna un entero, 0 índicando que la acción no fue realiza y un entero positivo si no hubo problemas con la transacción
     * Se implementó un valor de retorno int por la compatibilidad de la comprobación de las
     * demás clases de la capa y para poder dar una mejor retroalimentación en conjunto.
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar con
     * la base de datos o al tener un error.
     */
    public int quitarPrestamoDeBaseDeDatos(String identificador) throws SQLException;

    /**
     * Método que realiza el registro del préstamo en la base de datos
     *
     * @param prestamo Es un objeto de tipo préstamo valido que se va a guardar
     * en la base de datos.
     * @return  regresa 0 si es inválida la transacción y un entero positivo si es válida.
     * Se implementó un valor de retorno int por la compatibilidad de la comprobación de las
     * demás clases de la capa y para poder dar una mejor retroalimentación en conjunto.
     * @throws java.sql.SQLException Lanza SQLException al no poder conectar
     * con la base de datos o al tener un error.
     */
    public int guardarRegistroDePrestamo(Prestamo prestamo) throws SQLException;

}
