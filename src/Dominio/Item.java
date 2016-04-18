package Dominio;

import java.util.List;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public interface Item {
    public List<dataaccess.ItemDAO> buscarItem();
    public boolean actualizarItem();
    public boolean eliminarItem();
    public boolean agregarItem(Item item);
    public List<dataaccess.ItemDAO> regresarTodo();
}
