package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class ItemDAOImpl implements ItemDAO{
    private Conexion conexion;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    /**
     *
     */
    public ItemDAOImpl(){
        conexion = new Conexion();
    }

    /**
     *
     * @param identificador
     * @return
     * @throws SQLException
     */
    @Override
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT titulo, identificador, categoria FROM Items WHERE identificador = ?");            
            sentenciaSQL.setString(1,identificador);
            resultados=sentenciaSQL.executeQuery();
            
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return items;
    }
    
    /**
     *
     * @param item
     * @param matricula
     * @param folio
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public int reservarItem(Item item,String matricula, String folio) throws Exception{
        int resultadoDeAgregacion = 0;
        try{
            connection = conexion.obtenerConexion();
            if (ItemEstadoDisponibilidad(folio)== false){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO reservados VALUES (?,?,?)");
                sentenciaSQL.setString(1,folio);
                sentenciaSQL.setString(2,matricula);
                sentenciaSQL.setString(3,item.getIdentificador());
                resultadoDeAgregacion = sentenciaSQL.executeUpdate();         
            }
        } catch (SQLException | IllegalStateException ex) {
            ex.getCause();
        } finally{
            conexion.desconecta();
        }
        return resultadoDeAgregacion;
    }

    /**
     *
     * @param folioDeReserva
     * @return
     */
    @Override
    public int quitarItemDeReservacion(String folioDeReserva ){
        int resultadoDeLaEliminacion=0;
        try{ 
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("DELETE FROM reservados WHERE folio = ?");
            sentenciaSQL.setString(1,folioDeReserva);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
    
    /**
     *
     * @param item
     * @param matricula
     * @param folio
     * @return
     */
    @Override
    public int prestarItem(Item item,String matricula, String folioPrestamo, String folioDevolucion){
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
            connection = conexion.obtenerConexion();
            if (ItemEstadoDisponibilidad(folioPrestamo)== false){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?,?,?)");
                sentenciaSQL.setDate(1, fechaPrestamoMili);
                sentenciaSQL.setString(2, folioPrestamo);
                sentenciaSQL.setString(3, folioDevolucion);
                sentenciaSQL.setDate(4, fechaFinPrestamoMili);
                sentenciaSQL.setString(5,item.getIdentificador());
                sentenciaSQL.setString(6,matricula);
                resultadoDeAgregacion = sentenciaSQL.executeUpdate();           
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return resultadoDeAgregacion;
    }

    /**
     *
     * @param folioDePrestamo
     * @return
     */
    @Override
    public int quitarItemDePrestamo(String folioDePrestamo){
        int resultadoDeLaEliminacion=0;
        try{
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("DELETE FROM prestamos WHERE folioPrestamo = ?");
            sentenciaSQL.setString(1,folioDePrestamo);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Item> regresarTodo() {        
        List<Item> items = new ArrayList<>();
        try{
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT titulo, identificador, categoria FROM Items ");
            resultados=sentenciaSQL.executeQuery();
            
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return items;
    }
    /**
     * Seteo de items segun su categoria aplica para la implemntacio de los 
     * items
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
    private boolean ItemEstadoDisponibilidad(String folio) throws SQLException{
        boolean existeItem=false;
        Connection connection2;
        Conexion conexion2 = new Conexion();
        try{
            connection2 = conexion2.obtenerConexion();
            PreparedStatement itemEnReserva  = connection2.prepareStatement("SELECT folio FROM reservados WHERE folio = ?"); 
            PreparedStatement itemPrestado  = connection2.prepareStatement("SELECT folio FROM prestamos WHERE folio = ?");         
            itemEnReserva.setString(1,folio);
            itemPrestado.setString(1,folio);
            ResultSet resultadoReserva = itemEnReserva.executeQuery();  
            ResultSet resultadoPrestamo = itemEnReserva.executeQuery();            
            while (resultadoReserva.next() || resultadoPrestamo.next()){
                existeItem = true;
            }             
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion2.desconecta();
        }
        return existeItem;
    }
    
}
