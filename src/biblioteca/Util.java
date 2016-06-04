package biblioteca;

import dataaccess.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static boolean ItemEstadoDisponibilidad(String identificador) throws SQLException{
        boolean existeItem = true;
        Connection connection2;
        Conexion conexion2 = new Conexion();
        try{
            connection2 = conexion2.obtenerConexion();
            PreparedStatement itemNoDisponible = connection2.prepareStatement("call consultaDisponibilidad (?)");
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
    
    public static String generadorDeIdentificador() throws SQLException{
        Date fecha = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("yyyMMddHHmmss");
        String identificadorGenerado = formateadorDeFecha.format(fecha);
        identificadorGenerado = (String) identificadorGenerado.subSequence(1, identificadorGenerado.length());
        return identificadorGenerado;
    }
    
}
