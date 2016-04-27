package Dominio;

import dataaccess.Conexion;
import java.util.Date;
import java.util.List;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public abstract class Item {
    private String autor;
    private String titulo;
    private String codigoBarras;
    private String identificador;
    private Date fechaAdquisicion;
    private Date fechaPublicación;
    private NumberFormat costoMulta;
    private Date tiempoPrestamo;
    
    public List<Item> buscarItem(String codigoBarras, ArrayList listaItems, String tipoItem, String filtroBusqueda){
        return null;
    }
    public boolean actualizarItem(){return false;}
    public boolean eliminarItem(){return false;}
    
    private boolean existeItem(ArrayList<Item> items){
        return false;
    }
    public boolean agregarItem(Item item){
        return true;
    }
    public List<dataaccess.ItemDAO> regresarTodo(){return null;}
}
