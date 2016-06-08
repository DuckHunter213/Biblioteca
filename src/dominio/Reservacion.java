package dominio;

import biblioteca.Util;
import dataaccess.ReservacionDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Encapsula y valida los datos para hacer una reservación válida e informar a
 * las clases empleadoras
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class Reservacion{
    private Item item;
    private String identificadorUsuario;
    private Date fechaLimite;
    private String identificadorReservacion;

    //<editor-fold defaultstate="collapsed" desc=" Get´s & Set´s ">
    /**
     * @return Regresa un identificador tipo String de 15 caracteres para el
     * usuario
     */
    public String getIdentificadorUsuario(){
        return identificadorUsuario;
    }

    /**
     * @param identificadorUsuario
     * @return
     * @throws java.sql.SQLException
     */
    public boolean setIdentificadorUsuario(String identificadorUsuario) throws SQLException{
        boolean estadoSetIdentificador = false;
        try{
            if (Util.verificarIdentificadorUsuario(identificadorUsuario)){
                this.identificadorUsuario = identificadorUsuario;
                estadoSetIdentificador = true;
            }
        }catch (NullPointerException ex){
            estadoSetIdentificador = false;
        }
        return estadoSetIdentificador;
    }

    /**
     * @return Retorna una fecha con el formato por defecto de la clase Date
     */
    public Date getFechaLimite(){
        return fechaLimite;
    }

    /**
     * @return Regresa una fecha con formato para poder ser guardado en una base
     * de datos
     */
    public Long getFechaLimiteBD(){
        return fechaLimite.getTime();
    }

    /**
     * @return Regresa un String de 15 caracteres para identificar la
     * reservación
     */
    public String getIdentificadorReservacion(){
        return identificadorReservacion;
    }

    /**
     * @return Regresa el identificador del ítem, es una cadena de 10 caracteres
     */
    public String getIdentificadorItem(){
        return item.getIdentificador();
    }

    /**
     * @param item Guarda un ítem verificado previamente
     * @return Regresa un boolean, true si es correcto y false sino.
     */
    public boolean setItem(Item item){
        boolean estadoItem = false;
        try{
            if (Util.verificarIdentificadorItem(item.getIdentificador())){
                this.item = item;
                estadoItem = true;
            }
        }catch (SQLException ex){
            return estadoItem;
        }
        return estadoItem;
    }

    //</editor-fold>
    /**
     * @param item Recibe un ítem válido y configura la fecha límite para
     * pedirlo
     * @throws SQLException Si hay un error al conectar con la base de datos,
     * regresa una SQLexception
     */
    public Reservacion(Item item) throws SQLException{
        this.item = item;
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.add(Calendar.DAY_OF_WEEK_IN_MONTH, 10);
        fechaLimite = new Date();
        fechaLimite.setTime(calendario.getTimeInMillis());
    }

    /**
     * genera un identificador unico para las reservaciones
     *
     * @return Regresa el identificador generado por el sistema, un String de 15
     * caracteres
     */
    public String generarIdentificador(){
        identificadorReservacion = Util.generadorDeIdentificador();
        return identificadorReservacion;
    }

    /**
     * Registra la reservación en la base de datos.
     *
     * @return Regresa un entero de acuerdo al estado de la reservación, 0 si no
     * es posible y un entero positivo si lo fue
     * @throws SQLException Si hay problemas al conectar con la base de datos,
     * regresa una SQLException
     */
    public int realizarReservacion() throws SQLException{
        ReservacionDAOImpl reservacionDAO = new ReservacionDAOImpl();
        int estadoReservacion = 0;
        try{
            if (Util.verificarIdentificadorUsuario(identificadorUsuario) && Util.verificarIdentificadorItem(item.getIdentificador())){
                estadoReservacion = reservacionDAO.guardarRegistroReservacion(this);
            }else{
                estadoReservacion = 0;
            }
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoReservacion;
    }

}
