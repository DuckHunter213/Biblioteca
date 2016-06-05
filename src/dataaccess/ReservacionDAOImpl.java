package dataaccess;

import Dominio.Item;
import biblioteca.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase de la implementacion de la clase del DAO reservacón
 * @author Francisco Gerardo Mares Solano
 * @since 24/05/2016
 */
public class ReservacionDAOImpl implements ReservacionDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public ReservacionDAOImpl(){
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
    public int reservarItem(Item item, String identificadorUsuario) throws SQLException{
        int resultadoDeAgregacion = 0;
        try{
            connection = CONEXION.obtenerConexion();
            if (Util.ItemEstadoDisponibilidad(item.getIdentificador())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO reservados VALUES (?,?,?)");
                sentenciaSQL.setString(1, Util.generadorDeIdentificador());
                sentenciaSQL.setString(2, identificadorUsuario);
                sentenciaSQL.setString(3, item.getIdentificador());
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
    
}
