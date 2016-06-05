package biblioteca;

import dataaccess.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Nombre del programa:   Biblioteca
 * Nombres:               @author Luis Fernando Gomez Alejandre
 *                        @author Francisco Gerardo Mares Solano
 * Fecha:                 @since 23/05/2016
 * Descripción:           Es la implementación del patrón DAO para el item,
 *                        sus métodos son genéricos para el interface ItemDAO
 *                        y mediante polimorfismo resuelve las funcinalidades necesarias.
 */
public class Util {
    
    public static boolean itemEstadoDisponibilidad(String identificador) throws SQLException{
        boolean disponibilidadItem = true;
        Conexion CONEXION;
        Connection connection;
        Statement consulta;
        ResultSet resultadoDisponibilidad;
        CONEXION = new Conexion();
        
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement itemNoDisponible = connection.prepareStatement("call consultaDisponibilidad (?)");
            itemNoDisponible.setString(1, identificador);
            resultadoDisponibilidad = itemNoDisponible.executeQuery();              
            while (resultadoDisponibilidad.next()){
                disponibilidadItem = false;
            }
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return disponibilidadItem;
    }    
    
    public static boolean revisarLimitePrestamos(String identificador) throws SQLException{
        boolean disponibilidadUsuario = true;
        Conexion CONEXION;
        Connection connection;
        Statement consulta;
        ResultSet resultadoDisponibilidad;
        CONEXION = new Conexion();
        
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement itemNoDisponible = connection.prepareStatement("select identificadorUsuario from prestamos where identificadorUsuario = ?");
            itemNoDisponible.setString(1, identificador);
            resultadoDisponibilidad = itemNoDisponible.executeQuery();
            int contadorPrestamos = 0;
            while (resultadoDisponibilidad.next()){
                contadorPrestamos += 1;
                if (contadorPrestamos==3){
                    disponibilidadUsuario = false;
                    break;
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return disponibilidadUsuario;
    }
    
    public static String generadorDeIdentificador() throws SQLException{
        Date fecha = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("yyyMMddHHmmss");
        String identificadorGenerado = formateadorDeFecha.format(fecha);
        identificadorGenerado = (String) identificadorGenerado.subSequence(1, identificadorGenerado.length());
        return identificadorGenerado;
    }
    public static boolean verificarIdentificadorAlumno(String identificador) {        
        return identificador.length() == 15 && (identificador.toLowerCase()).startsWith("i");
    }
    public static boolean verificarIdentificadorItem(String identificador){
        return identificador.length() == 10 && (identificador.toLowerCase()).startsWith("i");
    }
    
}
