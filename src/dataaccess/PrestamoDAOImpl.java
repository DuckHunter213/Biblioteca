package dataaccess;

import Dominio.Item;
import biblioteca.Util;
import Dominio.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Francisco Gerardo Mares Solano
 */
public class PrestamoDAOImpl implements PrestamoDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public PrestamoDAOImpl(){
        CONEXION = new Conexion();
    }
    
    /**
     *
     * @param prestamo
     * @param identificadorUsuario
     * @return
     */
    @Override
    public int prestarItem(Prestamo prestamo) throws SQLException{
        int resultadoDeAgregacion = 0;
        java.sql.Date fechaPrestamoMili = new java.sql.Date(prestamo.getFechaPrestamo());
        java.sql.Date fechaFinPrestamoMili = new java.sql.Date(prestamo.getFechaCaducidad());
        
        try{
            connection = CONEXION.obtenerConexion();
            if (Util.ItemEstadoDisponibilidad(prestamo.getIdentificadorItem())== true){
                PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?,?)");
                sentenciaSQL.setDate(1, fechaPrestamoMili);
                sentenciaSQL.setString(2, prestamo.getIdentificadorPrestamo());
                sentenciaSQL.setString(3, prestamo.getIdentificadorItem());
                sentenciaSQL.setString(4, prestamo.getMatriculaUsuario());
                sentenciaSQL.setDate(5, fechaFinPrestamoMili);
                resultadoDeAgregacion = sentenciaSQL.executeUpdate();           
            }else{
                resultadoDeAgregacion = 0;
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
