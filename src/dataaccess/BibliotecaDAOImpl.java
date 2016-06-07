package dataaccess;

import dominio.Item;
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
    
    /**
     * El constructor, crea una conexion para poder entrar a la base de
     * datos
     */
    public BibliotecaDAOImpl(){
        CONEXION = new Conexion();
    }
    
    @Override
    public List<Item> buscarItem(String identificador) throws SQLException{
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
            if (items.size() == 0)
                return (List<Item>) item;
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        } finally{
            CONEXION.desconecta();
        }
        return items;
    }
    
    /**
     * Método encargado de verificar de que el alumno exista y sea valido
     * en la base de datos
     * @param identificadorUsuario Indentificador del usuario a verificar si 
     * existen y es valido en la base de datos.
     * @return Regresa el estado del usuario donde si es false el usuario no 
     * existe o no es valido y true en caso de ser valido y existir.
     * @throws SQLException Lanza una SQLException en caso de tener problemas
     * en la base de datos o no poder conectar a la misma.
     */
    public boolean verificarAlumno(String identificadorUsuario) throws SQLException{
        boolean estado = false;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM usuarios WHERE identificador = ?");            
            sentenciaSQL.setString(1, identificadorUsuario);
            resultados = sentenciaSQL.executeQuery();
            while(resultados.next()){
                estado = true;
            }
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return estado;
    }
    
    /**
     * Método encargado de verificar de que el item exista y sea valido en la
     * base de datos
     * @param identificadorItem Indentificador del Ítem a verificar si 
     * existen y es valido en la base de datos.
     * @return Regresa el estado del ítem donde si es false el ítem no 
     * existe o no es valido y true en caso de ser valido y existir.
     * @throws SQLException Lanza una SQLException en caso de tener problemas
     * en la base de datos o no poder conectar a la misma.
     */
    public boolean verificarItem(String identificadorItem) throws SQLException{
        boolean estado = false;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM items WHERE identificador = ?");            
            sentenciaSQL.setString(1, identificadorItem);
            resultados = sentenciaSQL.executeQuery();
            while(resultados.next()){
                estado = true;
            }
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return estado;
    }
    
    /**
     * Clase generica que es ocupada por todas las clases que puedan manejar
     * items 
     * @return Se regresa una lista de items en caso de no encontrar ninguno
     * regresara la lista vacia 
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error.
     */
    public List<Item> getItems() throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM Items");
            resultados = sentenciaSQL.executeQuery();
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            }             
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return items;
    }
    
    /**
     * Seteo de items segun su categoria aplica para la implementación
     * Parametro del item al cual se le va asociar la categoria,
     * Excepción al tener error en la base de datos o no poder conectar con ella
     */
    private Item capturarItem(Item item) throws SQLException{
        if (resultados != null){
            item = new Item();
            String categoria = resultados.getString("categoria");
            if (categoria.equals("Libro"))
                item.setCategoria("Libro");
            item.setIdentificador(resultados.getString("identificador"));
            item.setTitulo(resultados.getString("titulo"));
            item.setAutor(resultados.getString("autor"));
            item.setTiempoPrestamo(resultados.getInt("tiempoPrestamo"));
            item.setCostoMulta(resultados.getInt("costoMulta"));
        }
        return item;
    }
    
    @Override
    public int getTiempoPrestamoDeItem(String identificador) throws SQLException{
        int tiempoPrestamo = 0;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM items WHERE identificador = ?");
            sentenciaSQL.setString(1,identificador);
            resultados = sentenciaSQL.executeQuery();
            
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
            } 
            tiempoPrestamo = item.getTiempoPrestamo();
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return tiempoPrestamo;
    }
}