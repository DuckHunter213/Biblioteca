package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Nombre del programa:   Biblioteca
 * Nombres:               @author Luis Fernando Gomez Alejandre
 *                        @author Francisco Gerardo Mares Solano
 * Fecha:                 @since 20/05/2016
 * Descripción:           Es la implementación del patrón DAO para el item,
 *                        sus métodos son genéricos para el interface ItemDAO
 *                        y mediante polimorfismo resuelve las funcinalidades necesarias.
 */
public class ItemDAOImpl implements ItemDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public ItemDAOImpl(){
        CONEXION = new Conexion();
    }

    /**
     * @param identificador 
     * @return Se regresa una lista de items 
     * @throws SQLException
     */
    @Override
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT titulo, identificador, categoria FROM Items WHERE identificador = ?");            
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
     * Funcion que pone un item en reservación asociado a un usuario
     * 
     * @param item Recibe un objeto de tipo item
     * @param matricula Recibe la matricula del usuario que va a pedir la reservación
     * @return retorna un valor de retroaliemntación falso o verdadero en caso
     * de no haber podido capturar el item 
     * @throws java.sql.SQLException 
     */
    @Override
    public int reservarItem(Item item, String matricula) throws SQLException{
        int resultadoDeAgregacion = 0;
        try{
            connection = CONEXION.obtenerConexion();
            if (ItemEstadoDisponibilidad(item.getIdentificador())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO reservados VALUES (?,?,?)");
                sentenciaSQL.setString(1,generadorDeIdentificador());
                sentenciaSQL.setString(2,matricula);
                sentenciaSQL.setString(3,item.getIdentificador());
                resultadoDeAgregacion = sentenciaSQL.executeUpdate();         
            }
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        } finally{
            CONEXION.desconecta();
        }
        return resultadoDeAgregacion;
    }

    /**
     * 
     * @param identificadorItem quitar un item de una reservación pasando por 
     * referencia el item
     * @return regresa valor de retroalimntación
     */
    @Override
    public int quitarItemDeReservacion(String identificadorItem ) throws SQLException{
        int resultadoDeLaEliminacion = 0;
        try{ 
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("DELETE FROM reservados WHERE identificador = ?");
            sentenciaSQL.setString(1, identificadorItem);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
    
    /**
     *
     * @param item
     * @param matricula
     * @return
     */
    @Override
    public int prestarItem(Item item, String matricula) throws SQLException{
        int resultadoDeAgregacion = 0;
        Calendar calendario = GregorianCalendar.getInstance();
        Date fechaPrestamo = new Date(); 
        fechaPrestamo.setTime(calendario.getTimeInMillis());
        calendario.add(Calendar.DAY_OF_WEEK_IN_MONTH, item.getTiempoPrestamo());
        Date fechaFinPrestamo = new Date();
        fechaFinPrestamo.setTime(calendario.getTimeInMillis());
        java.sql.Date fechaPrestamoMili = new java.sql.Date(fechaPrestamo.getTime());
        java.sql.Date fechaFinPrestamoMili = new java.sql.Date(fechaFinPrestamo.getTime());
        
        try{
            connection = CONEXION.obtenerConexion();
            if (ItemEstadoDisponibilidad(item.getIdentificador())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?,?,?)");
                sentenciaSQL.setDate(1, fechaPrestamoMili);
                sentenciaSQL.setString(2, generadorDeIdentificador());
                sentenciaSQL.setString(3, generadorDeIdentificador());
                sentenciaSQL.setDate(4, fechaFinPrestamoMili);
                sentenciaSQL.setString(5,item.getIdentificador());
                sentenciaSQL.setString(6,matricula);
                resultadoDeAgregacion = sentenciaSQL.executeUpdate();           
            }            
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeAgregacion;
    }

    /**
     *
     * @param identificadorItem item a retirar con el identificador buscado
     * @return regresa un valor de retroaliementación para el usuario
     * //TODO valor de retroalimentación
     */
    @Override
    public int quitarItemDePrestamo(String identificadorItem) throws SQLException{
        int resultadoDeLaEliminacion=0;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("DELETE FROM prestamos WHERE identificador = ?");
            sentenciaSQL.setString(1,identificadorItem);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
    
    @Override
    public List<Item> regresarTodo() throws SQLException{        
        List<Item> items = new ArrayList<>();
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT titulo, identificador, categoria FROM Items ");
            resultados = sentenciaSQL.executeQuery();
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            }
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        } finally {
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
            item.setTitulo(resultados.getString("Titulo"));            
        }
        return item;
    }
    
    private boolean ItemEstadoDisponibilidad(String identificador) throws SQLException{
        boolean existeItem = true;
        Connection connection2;
        Conexion conexion2 = new Conexion();
        try{
            connection2 = conexion2.obtenerConexion();
            PreparedStatement itemEnReserva = connection2.prepareStatement("SELECT folio FROM reservados WHERE identificador = ?"); 
            PreparedStatement itemPrestado = connection2.prepareStatement("SELECT folio FROM prestamos WHERE identificador = ?");         
            itemEnReserva.setString(1, identificador);
            itemPrestado.setString(1, identificador);
            ResultSet resultadoReserva = itemEnReserva.executeQuery();  
            ResultSet resultadoPrestamo = itemEnReserva.executeQuery();            
            while (resultadoReserva.next() || resultadoPrestamo.next()){
                existeItem = false;
            }             
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            conexion2.desconecta();
        }
        return existeItem;
    }
    
    private static String generadorDeIdentificador() throws SQLException{
        Date fecha = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("yyyMMddHHmmss");
        String identificadorGenerado = formateadorDeFecha.format(fecha);
        identificadorGenerado = (String) identificadorGenerado.subSequence(1, identificadorGenerado.length());
        return identificadorGenerado;
    }
}
