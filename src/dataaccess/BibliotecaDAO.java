package dataaccess;

import Dominio.Item;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para generar el patrón DAO para guardar item en la base de datos
 * se decidió usar una interface para el uso de polimorfismo.
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */

public interface BibliotecaDAO {
    
    /**
     * Funcion que busca en todas las clases que puedan manejar items
     * @param identificador identi 
     * @return Regresa una lista con los items encontrados en dado caso de no 
     * encontrar ninguno regresa la lista vacia
     * @throws SQLException Salta la excepcion en //TODO
     */
    public List<Item> buscarItems(String identificador) throws SQLException;
    public int getTiempoPrestamoDeItem(String identificador) throws SQLException;
}