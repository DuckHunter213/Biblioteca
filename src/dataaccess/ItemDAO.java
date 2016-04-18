package dataaccess;

import java.util.List;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public interface ItemDAO {
    public List<ItemDAO> buscarItem();
    public boolean actualizarItem();
    public boolean eliminarItem();
    public List<ItemDAO> regresarTodo();
}