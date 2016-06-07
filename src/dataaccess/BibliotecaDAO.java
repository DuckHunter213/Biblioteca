package dataaccess;

import dominio.Item;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para generar el patrón DAO esta se encarga de buscar los items en
 * la base de datos y también se encarga de ver el tiempo que pueden ser
 * prestados los items
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public interface BibliotecaDAO{

    /**
     * Funcion que busca en todas las clases que puedan manejar items
     *
     * @param identificador identificador con el que sera buscado el item
     * @return Regresa una lista con los items encontrados en dado caso de no
     * encontrar ninguno regresa la lista vacia
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error.
     */
    public List<Item> buscarItem(String identificador) throws SQLException;

    /**
     * Función que regresa el tiempo que podran ser prestado los items
     *
     * @param identificador Identificador a cual se le sacara el tiempo de
     * prestamo ya que todos los ítems tienen un valor deferente de prestamo
     * @return se regrea la cantidad de dias que va a poder ser prestado el item
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error
     */
    public int getTiempoPrestamoDeItem(String identificador) throws SQLException;

}
