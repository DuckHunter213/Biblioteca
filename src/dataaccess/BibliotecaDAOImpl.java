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
 * Es la implementación del patrón DAO para el item, sus métodos son genéricos
 * para el interface ItemDAO y mediante polimorfismo resuelve las funcinalidades
 * generales del acceso a la base de datos.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class BibliotecaDAOImpl implements BibliotecaDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;

    /**
     * El constructor, inicia una instacia de Connection para acceder a la base
     * de datos
     */
    public BibliotecaDAOImpl(){
        CONEXION = new Conexion();
    }

    @Override
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM Items WHERE identificador = ?");
            sentenciaSQL.setString(1, identificador);
            resultados = sentenciaSQL.executeQuery();
            Item item = null;
            while (resultados.next()){
                item = capturarItem(item);
                items.add(item);
            }
            if (items.isEmpty()){
                return (List<Item>) item;
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return items;
    }

    /**
     * Método encargado de verificar de que el alumno exista y sea valido en la
     * base de datos
     *
     * @param identificadorUsuario Indentificador del usuario a verificar si
     * existen y es valido en la base de datos. Debe ser una cadena de 15
     * caracteres.
     * @return Regresa el estado del usuario donde si es false el usuario no
     * existe o no es valido y true en caso de ser valido y existir.
     * @throws SQLException Lanza una SQLException en caso de tener problemas en
     * la base de datos o no poder conectar a la misma.
     */
    public boolean verificarAlumno(String identificadorUsuario) throws SQLException{
        boolean estado = false;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM usuarios WHERE identificador = ?");
            sentenciaSQL.setString(1, identificadorUsuario);
            resultados = sentenciaSQL.executeQuery();
            while (resultados.next()){
                estado = true;
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return estado;
    }

    /**
     * Método encargado de verificar de que el item exista y sea valido en la
     * base de datos. Debe ser una cadena de 10 caracteres.
     *
     * @param identificadorItem Indentificador del Ítem a verificar si existen y
     * es valido en la base de datos.
     * @return Regresa el estado del ítem donde si es false el ítem no existe o
     * no es valido y true en caso de ser valido y existir.
     * @throws SQLException Lanza una SQLException en caso de tener problemas en
     * la base de datos o no poder conectar a la misma.
     */
    public boolean verificarItem(String identificadorItem) throws SQLException{
        boolean estado = false;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM items WHERE identificador = ?");
            sentenciaSQL.setString(1, identificadorItem);
            resultados = sentenciaSQL.executeQuery();
            while (resultados.next()){
                estado = true;
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return estado;
    }

    /**
     * Método para enlistar todos los ítems que estén registrados en la base de
     * datos
     *
     * @return Se regresa un ArrayList de item. En caso de no encontrar ninguno
     * regresara la lista vacia y nunca habrá lista nula.
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
            while (resultados.next()){
                item = capturarItem(item);
                items.add(item);
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return items;
    }


    /**
     * Checa si existe en la base de datos una reservación válida asociada a la matrícula
     * del usuario que quiere realizar el préstamo, si es así, el préstamo puede ser autorizado.
     *
     * @param identificadorUsuario El identificadora del usuario que busca reservar el ítem
     * @return Regresa un boolean, true si el usuario es quien pidió la reservación
     * y false si no es así.
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error.
     */
    public boolean verificarPosiblePrestamo(String identificadorUsuario) throws SQLException{
        boolean coinciden = false;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT * FROM reservados WHERE identificadorUsuario = ?");
            sentenciaSQL.setString(1, identificadorUsuario);
            resultados = sentenciaSQL.executeQuery();
            while (resultados.next()){
                coinciden = true;
            }
        }catch (SQLException ex){
            coinciden = false;
        }finally{
            CONEXION.desconecta();
        }
        return coinciden;
    }

    /**
     * Recibe un ítem vacio y le asigna los valores hallados en una consulta de
     * base de datos. Es necesario tener el ítem en un ResultSet.
     *
     * @Param El item que almacenará los datos obtenidos de la base de datos
     * @throws SQLException Lanza SQLException al no poder conectar con la base
     * de datos o al tener un error.
     */
    private Item capturarItem(Item item) throws SQLException{
        if (resultados != null){
            item = new Item();
            item.setCategoria(resultados.getString("Categoria"));
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
            sentenciaSQL.setString(1, identificador);
            resultados = sentenciaSQL.executeQuery();

            Item item = null;
            while (resultados.next()){
                item = capturarItem(item);
            }
            tiempoPrestamo = item.getTiempoPrestamo();
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return tiempoPrestamo;
    }

}
