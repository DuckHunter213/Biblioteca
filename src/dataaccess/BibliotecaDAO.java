package dataaccess;

import dominio.Item;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para generar el patrón DAO esta se encarga de buscar los ítems en
 * la base de datos y también se encarga de ver el tiempo que pueden ser
 * prestados los items
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public interface BibliotecaDAO{

    /**
     * Este método realiza una consulta en la base de datos para buscar un ítem
     *
     * @param identificador identificador con el que sera buscado el item
     * @return Regresa un ArrayList con los items encontrados. En caso de no
     * encontrar ninguno regresa la lista vacia
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error.
     */
    public List<Item> buscarItem(String identificador) throws SQLException;

    /**
     * Método que consulta en la base de datos, el tiempo de préstamo que puede
     * tener un ítem
     *
     * @param identificador Identificador del ítem del cual se obtendrá su
     * tiempo de prestamo.
     * @return se regrea un entero el cual expresa la cantidad de días que va a
     * poder ser prestado el item
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error
     */
    public int getTiempoPrestamoDeItem(String identificador) throws SQLException;

}
