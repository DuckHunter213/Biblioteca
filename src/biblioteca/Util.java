package biblioteca;

import dataaccess.BibliotecaDAOImpl;
import dataaccess.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase de utilerias donde se encontraran muchas funciones utiles
 * en ayuda y complemento de las demas clases
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class Util {
    
    /**
     * Regresa el estado de la disponibilidad del item
     * @param identificador el identificador del item a checar
     * @return se regresa el estado del item true para decir que esta disponible
     * y false para decir que no lo esta
     * @throws SQLException Se lanza la execpcion al momento de no poder
     * conectar con la base de datos
     */
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
    
    /**
     *
     * @param identificador indetificador del usuario al que se le quiere saber
     * si puede pedir prestado otro libro más
     * @return regresa el estado del usuario verdadero si es que todavia puede
     * pedri prestado los libros
     * @throws SQLException Lanza la exepcion si no puede conectar a la base de
     * datos
     */
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
    
    /**
     * Esta clase genera un identificador que tiene formato YYYMMDDHHmmss se 
     * garantiza de que los identificadores sean unicos
     * @return se regresa un identificador ya generado-
     */
    public static String generadorDeIdentificador(){
        Date fecha = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("yyyMMddHHmmss");
        String identificadorGenerado = formateadorDeFecha.format(fecha);
        identificadorGenerado = (String) identificadorGenerado.subSequence(1, identificadorGenerado.length());
        return identificadorGenerado;
    }
    
    /**
     * Verifica si existe y es valido un usuario de un alumno por medio de su
     * identificador
     * @param identificador indentificador del alumno para corroborar que este
     * registrado el usuario 
     * @return se regresa el estado del usuario falso en caso de que no exista 
     * o no sea valido
     * @throws SQLException se lanza la exepción si tiene error al conectar a 
     * la base de datos
     */
    public static boolean verificarIdentificadorAlumno(String identificador) throws SQLException {
        boolean estado = false;
        BibliotecaDAOImpl biblioteca = new BibliotecaDAOImpl();
        if (biblioteca.verificarAlumno(identificador)){
            estado = identificador.length() == 15 && (identificador.toLowerCase()).startsWith("i");
        }
        return estado;
    }
    
    /**
     * Verifica la existencia del item y de que sea valido por medio del 
     * identificador
     * @param identificador identificador del item 
     * @return regresa el estado del item, falso en caso de no existir o de no
     * ser valido y verdadero en caso de existir y ser valido
     * @throws SQLException lanza la exepcion si no se puede conectar a la
     * base de datos
     */
    public static boolean verificarIdentificadorItem(String identificador) throws SQLException{
        boolean estado =  false;
        BibliotecaDAOImpl biblioteca = new BibliotecaDAOImpl();
        if (biblioteca.verificarItem(identificador)){
            estado = identificador.length() == 10 && (identificador.toLowerCase()).startsWith("i");
        }
        return estado;
    }
}
