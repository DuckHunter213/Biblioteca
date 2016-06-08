package dominio;

import java.util.GregorianCalendar;
import biblioteca.Util;
import dataaccess.PrestamoDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Encapsula y valida todos los datos para poder realizar un préstamo
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class Prestamo{
    private Item item;
    private String matriculaUsuario;
    private Date fechaPrestamo;
    private Date fechaCaducidad;
    private String identificadorPrestamo;

    public Prestamo(Item item) throws SQLException{
        this.item = item;
        Calendar calendario = GregorianCalendar.getInstance();
        fechaPrestamo = new Date();
        fechaPrestamo.setTime(calendario.getTimeInMillis());
        this.item.setIdentificador(item.getIdentificador());
        identificadorPrestamo = Util.generadorDeIdentificador();
        try{
            //La cantidad de días que se presta, se consigue de la BD y se regresa
            calendario.add(Calendar.DAY_OF_WEEK_IN_MONTH, item.getTiempoPrestamo());
            fechaCaducidad = new Date();
            fechaCaducidad.setTime(calendario.getTimeInMillis());
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" Get´s & Set´s ">

    /**
     * @return Regresa la fecha en que se registró el préstamo en formato Long.
     */
    public Long getFechaPrestamo(){
        return this.fechaPrestamo.getTime();
    }

    /**
     * @return Regresa la fecha  en que caduca el préstamo en formato Long
     */
    public Long getFechaCaducidad(){
        return this.fechaCaducidad.getTime();
    }

    /**
     * @return Regresa la fecha de caducidad del préstamo en formato String
     */
    public String getFechaCaducidadNormal(){
        return this.fechaCaducidad.toString();
    }

    /**
     * @return Regresa el identificador del préstamo como un String de 15 caracteres
     */
    public String getIdentificadorPrestamo(){
        return this.identificadorPrestamo;
    }

    /**
     * Guarda el identificador del usuario validando si es correcto
     * @param identificadorUsuario Un String de 15 caracteres que será validado si existe en la base de datos.
     * @return Regresa true si el identificador es válido y pudo ser guardado y false si no es posible
     * @throws SQLException Si no es posible comprobar 
     */
    public boolean setIdentificadorUsuario(String matriculaUsuario) throws SQLException{
        boolean estadoSetMatricula = false;
        if (Util.verificarIdentificadorUsuario(matriculaUsuario)){
            this.matriculaUsuario = matriculaUsuario;
            estadoSetMatricula = true;
        }
        return estadoSetMatricula;
    }

    /**
     * @return Regresa un String de 15 caracteres 
     */
    public String getIdentificadorUsuario(){
        return this.matriculaUsuario;
    }

    /**
     * @param item Guarda un ítem verificado previamente
     */
    public void setItem(Item item){
        this.item = item;
    }

    /**
     *
     * @return
     */
    public String getIdentificadorItem(){
        return this.item.getIdentificador();
    }
    //</editor-fold>

    /**
     * Registra el préstamo en el base de datos haciendo validaciones internas
     * @return Estado del préstamo, regresa -1 si la matrícula no existe, 0 si el préstamo no es posible y un 1 si la inserción fue adecuada
     * @throws SQLException Si no hay conexión con la base de datos, lanza una SQLException
     */
    public int realizarPrestamo() throws SQLException{
        PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl();
        int estadoPrestamo = 0;
        try{
            estadoPrestamo = prestamoDAO.guardarRegistroDePrestamo(this);
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoPrestamo;
    }

}
