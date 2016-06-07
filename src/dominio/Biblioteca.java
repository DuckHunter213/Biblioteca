package dominio;

import biblioteca.Util;
import dataaccess.BibliotecaDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase tipo biblioteca, a través de ésta clase se pueden realizar préstamos, reservaciones,
 * búsqueda de ítems y sus correspondientes validaciones.
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class Biblioteca {
    public List<Prestamo> prestamos = null;
    public List<Reservacion> reservaciones = null;
    
    /**
     * Una única instancia de una biblioteca es capaz de realizar múltiples
     * préstamos y reservaciones, la biblioteca podría ser adaptada para realizar múltiples
     * transacciones a la BD cuando se conecte a internet si es que estaba por ello, se decidió
     * tener un almacenamiento momentaneo en ArrayList
     */
    public Biblioteca(){
        prestamos = new ArrayList<>();
        reservaciones = new ArrayList<>();
    }
    
    /**
     * Busca cualquier ítem que esté registrado en la base de datos con el identificador pasado.
     * @param identificador se realiza una validación de 15 caracteres y una letra inicial 'I' mayúscula o minúscula.
     * @return Si el parámetro fue pasado de manera erronea o no existe en la base de datos, regresa una lista vacia.
     * Nunca regresará una lista nula y siempre será un ArrayList
     * @throws SQLException Si no existe conexión con la base de datos o algo sale mal durante la conexión con la misma,
     * se lanzará una SQLException con el correspondiente mensaje de error.
     */
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        BibliotecaDAOImpl bibliotecaDAO = new BibliotecaDAOImpl();
        try{
            items = bibliotecaDAO.buscarItem(identificador);
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return items;
    }
    
    /**
     * Se encarga de validar el identificador del usuario asegurándo que sea una cadena de 
     * 15 caracteres de longitud, que exista en la base de datos y que su letra inicial sea una "i" mayúscula o minúscula.
     * @param identificadorAlumno Una cadena de 15 caracteres
     * @return Si la cadena introducida es válida, regresa true, en caso contrario false.
     * @throws SQLException La verificación requiere revisar la base de datos, si no es posible el acceso a ella,
     * u ocurre un error durante la consulta, se lanza una SQLException.
     */
    public boolean verificarIdentificadorAlumno(String identificadorAlumno) throws SQLException{
        return Util.verificarIdentificadorAlumno(identificadorAlumno);
    }
    
    /**
     * Verifica el identificador del ítem asegurando que sea una cadena de 10 caracteres,
     * que exista en la base de datos y que su letra inicial sea una "i" mayúscula o minúscula.
     * @param identificadorItem Un identificador de ítem de 15 caracteres
     * @return Si la cadena introducida es válida, regresa true, en caso contrario regresa false
     * @throws SQLException La verificación requiere revisar la base de datos, si no es posible el acceso a ella,
     * u ocurre un error durante la consulta, se lanza una SQLException.
     */
    public boolean verificarItem(String identificadorItem) throws SQLException{
        return Util.verificarIdentificadorItem(identificadorItem);
    }
    
    /**
     * Crea un objeto préstamo el cual contiene un objeto ítem extraído de la base de datos,
     * además, crea un identificador de préstamo para poder ser identificado por la clase empleadora
     * @param identificadorItem recibe un identificador de algún ítem el cual debería ser previamente revisado, aun así, si
     * es erroneo, producira un SQLException.
     * @return El identificador del préstamo será generado con base en la fecha, es un String con una longitud de 15 caracteres
     * y es necesario tenerlo para poder realizar el préstamo.
     * @throws SQLException Si el identificador no coincide con ningún ítem en la base de datos o no hay conexión a la base de datos
     * se lanzará una SQLException.
     */
    public String generarPrestamo(String identificadorItem) throws SQLException{
        List items = new ArrayList<>();
        String identificadorPrestamo = "";
        try{
            items = buscarItem(identificadorItem);
            Prestamo prestamo = new Prestamo((Item) items.get(0));          
            identificadorPrestamo = prestamo.getIdentificadorPrestamo();
            prestamos.add(prestamo);
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return identificadorPrestamo;
    }
    
    /**
     * Crea un objeto reservación y le asigna un ítem con todos sus datos completos, si algún dato falta, fallará el préstamo,
     * además, crea un identificador de reservación para poder ser identificado por la clase empleadora
     * @param item recibe un ítem que debe ser previamente revisado si existe en la base de datos
     * @return El identificador de la reservación será generado con base en la fecha, es un String con una longitud de 15 caracteres
     * @throws SQLException Si algún dato del ítem es erroneo o no hay conexión a la base de datos
     * se lanzará una SQLException.
     */
    public String generarReservacion(Item item) throws SQLException{
        Reservacion reservacion = new Reservacion(item);
        reservacion.setIdentificadorUsuario("identificadora1");
        reservacion.generarIdentificador();
        String identificador = reservacion.getIdentificadorReservacion();
        reservaciones.add(reservacion);
        return identificador;
    }
    
    /**
     * Regresa la fecha en que el préstamo asociado al identificador pasado, caduca.
     * @param identificadorPrestamo Es un String de 15 caracteres asociado a un préstamo válido.
     * @return Regresa un texto con la fecha de caducidad del préstamo.
     */
    public String verFechaFinPrestamo(String identificadorPrestamo){
        Prestamo prestamo = null;
        for(int i = 0;i < prestamos.size();i++){
            if (prestamos.get(i).getIdentificadorPrestamo().equals(identificadorPrestamo))
                prestamo = prestamos.get(i);
        }
        return prestamo.getFechaCaducidadNormal();
    }
    
    /**
     * Regresa la fecha en que la reservación asociado al identificador pasado, caduca.
     * @param identificador Es un String de 15 caracteres asociado a una reservación válida.
     * @return Regresa un texto con la fecha de caducidad de la reservación.
     */
    public Date verFechaFinReservacion(String identificador){
        Reservacion reservacion = null;
        for(int i = 0;i < reservaciones.size();i++){
            if (reservaciones.get(i).getIdentificadorReservacion().equals(identificador))
                reservacion = reservaciones.get(i);
        }
        return reservacion.getFechaLimite();
    }
    
    /**
     * Incluye el préstamo en la base de datos. Requiere ejecutar el método generarPrestamo() de la clase con anterioridad para obtener
     * del identificador asociado al objeto préstamo
     * @param identificadorPrestamo Es el identificador generado por el objeto préstamo que será guardado en la base de datos
     * @param identificadorAlumno Es el identificador del alumno al cual se le asociará el préstamo, debe ser un String de 15 caracteres y existir en la base de datos
     * @return Regresar un entero de acuerdo al estado del préstamo: 0 si la acción ha fallado y cualquier otro número si fue exitoso
     * @throws SQLException Si existe un error al conectar con la base de datos, lanza una SQLException.
     */
    public int realizarPrestamo(String identificadorPrestamo, String identificadorAlumno) throws SQLException{
        int estadoPrestamo = 0;
        Prestamo prestamo = null;
        for(int i = 0;i<prestamos.size();i++){
            if (prestamos.get(i).getIdentificadorPrestamo().equals(identificadorPrestamo))
                prestamo = prestamos.get(i);
        }
        try{
            boolean estadoMatricula = prestamo.setMatriculaUsuario(identificadorAlumno);
            if (estadoMatricula)
                estadoPrestamo = prestamo.realizarPrestamo();
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return estadoPrestamo;
    }
    
    /**
     * Incluye la reservación en la base de datos. Requiere ejecutar el método generarReservación() de la clase con anterioridad para obtener
     * del identificador asociado al objeto reservación.
     * @param identificador Es el identificador de la reservación generado por el objeto.
     * @return Regresar un entero de acuerdo al estado de la reservación: 0 si la acción ha fallado y cualquier otro número si fue exitosa
     * @throws SQLException Si existe un error al conectar con la base de datos, lanza una SQLException.
     */
    public int realizarReservacion(String identificador) throws SQLException{
        Reservacion reservacion = null;
        for(int i = 0;i < reservaciones.size();i++) {
            if (reservaciones.get(i).getIdentificadorReservacion().equals(identificador))
                  reservacion = reservaciones.get(i);
        }
        int estadoReservacion = reservacion.realizarReservacion();
        return estadoReservacion;
    }
    
    /**
     * Regresa todos los ítems registrados en la base de datos.
     * @return Un ArrayList con todos los ítems encontrados, si no existe ningún regresa lista vacia, nunca lsita nula.
     * @throws SQLException Si hay un error al conectar con la base de datos, lanza una SQLException
     */
    public List<Item> getItems()throws SQLException{
        List<Item> items = new ArrayList<>();
        BibliotecaDAOImpl bibliotecaDAO = new BibliotecaDAOImpl();
        try{
            items = bibliotecaDAO.getItems();
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return items;
    }
}
