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
    
    
    public List<Item> buscarItem(String identificador) throws SQLException;
    public int quitarItemDeReservacion(String identificador);
    public int reservarItem(Item item,String matricula, String folio) throws SQLException;
    public int quitarItemDePrestamo(String identificador);
    public int prestarItem(Item item,String matricula, String folio);
    public List<Item>  regresarTodo();
}