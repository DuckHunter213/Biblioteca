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
 * Clase de utilerias donde se encontraran muchas funciones útiles en múltiples
 * funcionalidades del sistema provee funcionas básicas de validación de datos y
 * generación de identificadores.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class Util{

    /**
     * Regresa el estado de la disponibilidad del item
     *
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
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return disponibilidadItem;
    }

    /**
     * Se encarga de revisar si el usuario superó su límite de préstamos.
     *
     * @param identificador indetificador del usuario del cual se quiere saber
     * si es apto para realiza más préstamos o no
     * @return regresa el estado true si es que todavia puede pedir prestado los
     * libros y false si superó su límite
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
                if (contadorPrestamos == 3){
                    disponibilidadUsuario = false;
                    break;
                }
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }finally{
            CONEXION.desconecta();
        }
        return disponibilidadUsuario;
    }

    /**
     * Esta clase genera un identificador que tiene formato YYYMMDDHHmmSS se
     * (año, mes, día, hora, minuto y segundo). Se eligió ésta estructura pues
     * garantiza que sean datos únicos e imposibles de recrear debido a su
     * exactitud, además, otorga información extra de su registro
     *
     * @return se regresa un String del identificador ya generado
     */
    public static String generadorDeIdentificador(){
        Date fecha = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("yyyMMddHHmmss");
        String identificadorGenerado = formateadorDeFecha.format(fecha);
        identificadorGenerado = (String) identificadorGenerado.subSequence(1, identificadorGenerado.length());
        return identificadorGenerado;
    }

    /**
     * Verifica si existe el usuario y si es valido en la base de datos. El modo
     * de comprobación inicial es es verificar la longitud para disminuir
     * chequeos en la base de datos, y que empiece con el caracter "i" como
     * indicio de que podría ser válido.
     *
     * @param identificador indentificador del usuario para corroborar que esté
     * registrado.
     * @return se regresa el estado del usuario false en caso de que no exista o
     * no sea válido y true si es correcto.
     * @throws SQLException se lanza la exepción si tiene error al conectar a la
     * base de datos
     */
    public static boolean verificarIdentificadorUsuario(String identificador) throws SQLException{
        boolean estado = false;
        try{
            if (estado = identificador.length() == 15 && (identificador.toLowerCase()).startsWith("i")){
                BibliotecaDAOImpl biblioteca = new BibliotecaDAOImpl();
                estado = biblioteca.verificarAlumno(identificador);
            }
        }catch (NullPointerException ex){
            estado = false;
        }
        return estado;
    }

    /**
     * Verifica la existencia del ítem y que sea válido en la base de datos. El
     * modo de comprobación inicial es es verificar la longitud para disminuir
     * chequeos en la base de datos, y que empiece con el caracter "i" como
     * indicio de que podría ser válido.
     *
     * @param identificador identificador del item. Un String de 10 caracteres
     * @return regresa el booleano false en caso de no existir o de no ser
     * valido y true si es correcto.
     * @throws SQLException lanza la exepcion si no se puede conectar a la base
     * de datos
     */
    public static boolean verificarIdentificadorItem(String identificador) throws SQLException{
        boolean estado = false;
        try{
            if ((identificador.length() == 10) && (identificador.toLowerCase()).startsWith("i")){
                BibliotecaDAOImpl biblioteca = new BibliotecaDAOImpl();
                estado = biblioteca.verificarItem(identificador);
            }
        }catch (NullPointerException ex){
            estado = false;
        }
        return estado;
    }

}
