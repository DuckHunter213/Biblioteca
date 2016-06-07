package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de hacer conexiones a la base de datos de igual manera
 * es la encargada de liberar los recursos de la conexión
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 18/04/2016
 */
public class Conexion {
    //Los datos fueron puestos en bruto para prubas, en siguientes versiones
    //se asignaran los datos mediante la interfaz
    private Connection conexion;
    private final String DB = "jdbc:mysql://127.0.0.1/Biblioteca";
    private final String USUARIO = "root";
    private final String CONTRASENA = "";

    /**
     * Funcion que encapsula la conexión, se nombro asi por que es más 
     * fácil leer el código
     * @return Se regresa el estado de la conexion exitoso, de otro modo
     * arrojara la excepción
     * @throws SQLException Se lanza esta excepción al no poder establecer
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
     * @throws java.sql.SQLException Lanza la excepcion en caso de fallar en la
     * base de datos
     */
    public void desconecta() throws SQLException {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
            throw new SQLException("No hubo conección con la base de datos: " + ex.getMessage());
            }
        }
    }
}
