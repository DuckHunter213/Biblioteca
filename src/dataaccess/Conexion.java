package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class Conexion {

    private Connection conexion;
    private final String DB = "jdbc:mysql://127.0.0.1/Biblioteca2_0";
    private final String USUARIO = "root";
    private final String CONTRASENA = "";

    public Connection obtenerConexion() throws SQLException {
        conecta();
        return conexion;
    }

    private void conecta() throws SQLException {
        conexion = DriverManager.getConnection(DB, USUARIO, CONTRASENA);
    }

    // Se maneja la exepcion desde aqui para no interrumpir el flujo de las capas
    // de arriba así la excepcion se manda a logger directamente
    public void desconecta() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
