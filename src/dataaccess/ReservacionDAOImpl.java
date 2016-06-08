package dataaccess;

import dominio.Reservacion;
import biblioteca.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Interface con las generalidades para poder registrar una reservación en la
 * base de datos.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class ReservacionDAOImpl implements ReservacionDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;

    /**
     * Inicia una instancia de Connection para realizar las consultas a la base
     * de datos
     */
    public ReservacionDAOImpl(){
        CONEXION = new Conexion();
    }

    @Override
    public int guardarRegistroReservacion(Reservacion reservacion) throws SQLException{
        int resultadoDeAgregacion = 0;
        java.sql.Date fechaPrestamoMili = new java.sql.Date(reservacion.getFechaLimiteBD());
        try{
            connection = CONEXION.obtenerConexion();
            if (Util.itemEstadoDisponibilidad(reservacion.getIdentificadorItem())){
                if (Util.verificarIdentificadorItem(reservacion.getIdentificadorItem())){
                    PreparedStatement sentenciaSQL = connection.prepareStatement("INSERT INTO reservados VALUES (?,?,?,?)");
                    sentenciaSQL.setDate(1, fechaPrestamoMili);
                    sentenciaSQL.setString(2, reservacion.getIdentificadorReservacion());
                    sentenciaSQL.setString(3, reservacion.getIdentificadorUsuario());
                    sentenciaSQL.setString(4, reservacion.getIdentificadorItem());
                    resultadoDeAgregacion = sentenciaSQL.executeUpdate();
                }
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeAgregacion;
    }

    @Override
    public int quitarReservacionDeBD(String identificadorItem) throws SQLException{
        int resultadoDeLaEliminacion = 0;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("DELETE FROM reservados WHERE identificadorItem = ?");
            sentenciaSQL.setString(1, identificadorItem);
            resultadoDeLaEliminacion = sentenciaSQL.executeUpdate();

        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeLaEliminacion;
    }

}
