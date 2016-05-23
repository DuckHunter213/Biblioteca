/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import biblioteca.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author gerar
 */
public class prestamoDAOImpl implements prestamoDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public prestamoDAOImpl(){
        CONEXION = new Conexion();
    }
    
    /**
     *
     * @param item
     * @param identificadorUsuario
     * @param matricula
     * @return
     */
    @Override
    public int prestarItem(Item item, String identificadorUsuario) throws SQLException{
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
            if (Util.ItemEstadoDisponibilidad(item.getIdentificador())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?,?)");
                sentenciaSQL.setDate(1, fechaPrestamoMili);
                sentenciaSQL.setString(2, Util.generadorDeIdentificador());
                sentenciaSQL.setString(3, item.getIdentificador());
                sentenciaSQL.setString(4, identificadorUsuario);
                sentenciaSQL.setDate(5, fechaFinPrestamoMili);
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
            PreparedStatement sentenciaSQL  = connection.prepareStatement("DELETE FROM prestamos WHERE identificadorItem = ?");
            sentenciaSQL.setString(1,identificadorItem);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
        
}
