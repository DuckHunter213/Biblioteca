package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Nombre del programa:   Biblioteca
 * Nombres:               @author Luis Fernando Gomez Alejandre
 *                        @author Francisco Gerardo Mares Solano
 * Fecha:                 @since 18/04/2016
 * Descripción:           Es la implementación del patrón DAO para el item,
 *                        sus métodos son genéricos para el interface ItemDAO
 *                        y mediante polimorfismo resuelve las funcinalidades necesarias.
 */

public class Conexion {
    
    private Connection conexion;
    private final String DB = "jdbc:mysql://127.0.0.1/Biblioteca";
    private final String USUARIO = "root";
    private final String CONTRASENA = "";

    /**
     *  Funcion que encapsula la conexión, se establecio asi por que es mas 
     * fácil leer el código
     * @return Se regresa el estado de la conexion exitoso, de otro modo
     * arrojara la excepción de conexión
     * @throws SQLException Se lanza esta excepcion al no poder establecer
     * conexion a la base de datos 
     */
    public Connection obtenerConexion() throws SQLException {
        conecta();
        return conexion;
    }

    private void conecta() throws SQLException {
        conexion = DriverManager.getConnection(DB, USUARIO, CONTRASENA);
    }

    /**
     *  Funcion que termina la conexion y libera el recurso de conexión
     */
    public void desconecta() throws SQLException {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                /* Se maneja la exepcion desde aqui para no interrumpir el flujo de las capas
                * de arriba así la excepcion se manda a logger directamente
                */
            throw new SQLException("No hubo conección con la base de datos: " + ex.getMessage());
            }
        }
    }
}
