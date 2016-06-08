package dataaccess;

import biblioteca.Util;
import dominio.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementación de PrestamoDAO para poder realizar accesos y transacciones en la base de datos
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class PrestamoDAOImpl implements PrestamoDAO{
    private final Conexion CONEXION;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;

    /**
     * Inicia una instancia de Connection para realizar las consultas a la base de datos
     */
    public PrestamoDAOImpl(){
        CONEXION = new Conexion();
    }

    @Override
    public int guardarRegistroDePrestamo(Prestamo prestamo) throws SQLException{
        int resultadoDeAgregacion = 0;
        java.sql.Date fechaPrestamoMili = new java.sql.Date(prestamo.getFechaPrestamo());
        java.sql.Date fechaFinPrestamoMili = new java.sql.Date(prestamo.getFechaCaducidad());

        try{
            connection = CONEXION.obtenerConexion();
            if (Util.itemEstadoDisponibilidad(prestamo.getIdentificadorItem())){
                if (Util.revisarLimitePrestamos(prestamo.getIdentificadorUsuario())){
                    if (Util.verificarIdentificadorItem(prestamo.getIdentificadorItem())){
                        PreparedStatement sentenciaSQL = connection.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?,?)");
                        sentenciaSQL.setDate(1, fechaPrestamoMili);
                        sentenciaSQL.setString(2, prestamo.getIdentificadorPrestamo());
                        sentenciaSQL.setString(3, prestamo.getIdentificadorItem());
                        sentenciaSQL.setString(4, prestamo.getIdentificadorUsuario());
                        sentenciaSQL.setDate(5, fechaFinPrestamoMili);
                        resultadoDeAgregacion = sentenciaSQL.executeUpdate();
                    }
                }else{
                    resultadoDeAgregacion = -1;
                }
            }else{
                resultadoDeAgregacion = 0;
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return resultadoDeAgregacion;
    }

    @Override
    public int quitarPrestamoDeBD(String identificadorItem) throws SQLException{
        int resultadoDeLaEliminacion = 0;
        try{
            connection = CONEXION.obtenerConexion();
            PreparedStatement sentenciaSQL = connection.prepareStatement("DELETE FROM prestamos WHERE identificadorItem = ?");
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
