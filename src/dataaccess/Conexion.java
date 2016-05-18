package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de tipo conexión, es la encargada de proveer conexión y desconexión
 * de la base de datos 
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class Conexion {
    
    private Connection conexion;
    private final String DB = "jdbc:mysql://127.0.0.1/Biblioteca2_0";
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
    public void desconecta() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                // Se maneja la exepcion desde aqui para no interrumpir el flujo de las capas
                // de arriba así la excepcion se manda a logger directamente
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
