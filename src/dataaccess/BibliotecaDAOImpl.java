package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Es la implementación del patrón DAO para el item,sus métodos son genéricos
 * para el interface ItemDAO y mediante polimorfismo resuelve las
 * funcinalidades necesarias.
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class BibliotecaDAOImpl implements BibliotecaDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public BibliotecaDAOImpl(){
        CONEXION = new Conexion();
    }

    /**
     * Clase generica que es ocupada por todas las clases que puedan manejar
     * items
     * @param identificador 
     * @return Se regresa una lista de items en caso de no encontrar ninguno
     * regresara la lista vacia 
     * @throws SQLException
     */
    public List<Item> buscarItems(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT * FROM Items WHERE identificador = ?");            
            sentenciaSQL.setString(1, identificador);
            resultados = sentenciaSQL.executeQuery();
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            }             
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return items;
    }
    
    /**
     * Seteo de items segun su categoria aplica para la implementación
     */
    private Item capturarItem(Item item) throws SQLException{
        if (resultados != null){
            String categoria = resultados.getString("categoria");
            if (categoria.equals("Libro"))
                item = new Libro();
            item.setIdentificador(resultados.getString("identificador"));
            item.setTitulo(resultados.getString("titulo"));
            item.setAutor(resultados.getString("autor"));
            item.setTiempoPrestamo(resultados.getInt("tiempoPrestamo"));
            item.setCostoMulta(resultados.getInt("costoMulta"));
        }
        return item;
    }
    
    
    public int getTiempoPrestamoDeItem(String identificador) throws SQLException {
        int tiempoPrestamo = 0;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT * FROM items WHERE identificador = ?");
            sentenciaSQL.setString(1,identificador);
            resultados = sentenciaSQL.executeQuery();
            
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
            } 
            tiempoPrestamo = item.getTiempoPrestamo();
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return tiempoPrestamo;
    }
}