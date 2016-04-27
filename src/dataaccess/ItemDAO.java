package dataaccess;

import Dominio.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public interface ItemDAO {
    
    
    public List<Item> buscarItem(String codigoBarras, ArrayList listaItems, String tipoItem, String filtroBusqueda) throws SQLException;
    public boolean actualizarItem();
    public boolean eliminarItem();
    public boolean agregarItem(Item item);
    public List<dataaccess.ItemDAO> regresarTodo();
}