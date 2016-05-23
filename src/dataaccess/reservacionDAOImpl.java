/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gerar
 */
public class reservacionDAOImpl implements reservacionDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public reservacionDAOImpl(){
        CONEXION = new Conexion();
    }
    
    /**
     * Funcion que pone un item en reservación asociado a un usuario
     * 
     * @param item Recibe un objeto de tipo item
     * @param identificadorUsuario
     * @return retorna un valor de retroaliemntación falso o verdadero en caso
     * de no haber podido capturar el item 
     * @throws java.sql.SQLException 
     */
    @Override
    public int reservarItem(Item item, String identificadorUsuario) throws SQLException{
        int resultadoDeAgregacion = 0;
        try{
            connection = CONEXION.obtenerConexion();
            if (ItemEstadoDisponibilidad(item.getIdentificador())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO reservados VALUES (?,?,?)");
                sentenciaSQL.setString(1,generadorDeIdentificador());
                sentenciaSQL.setString(2,identificadorUsuario);
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
            PreparedStatement sentenciaSQL = connection.prepareStatement("DELETE FROM reservados WHERE identificadorItem = ?");
            sentenciaSQL.setString(1, identificadorItem);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeLaEliminacion;
    }
    
    private boolean ItemEstadoDisponibilidad(String identificador) throws SQLException{
        boolean existeItem = true;
        Connection connection2;
        Conexion conexion2 = new Conexion();
        try{
            connection2 = conexion2.obtenerConexion();
            PreparedStatement itemNoDisponible = connection2.prepareStatement("SELECT * FROM reservados WHERE folioReservacion = ?");
            itemNoDisponible.setString(1, identificador);
            ResultSet resultadoDisponibilidad = itemNoDisponible.executeQuery();              
            while (resultadoDisponibilidad.next()){
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
