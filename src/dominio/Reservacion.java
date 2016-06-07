package dominio;

import biblioteca.Util;
import dataaccess.ReservacionDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Encapsula y valida los datos para hacer una reservación válida e informar a las clases empleadoras
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
     * @return Regresa un identificador tipo String de 15 caracteres  para el usuario
     */
    public String getIdentificadorUsuario(){
        return identificadorUsuario;
    }

    /**
     * @param identificador Recibe una cadena de 15 caracteres para el usuario
     */
    public void setIdentificadorUsuario(String identificador){
        this.identificadorUsuario = identificador;
    }

    /**
     * @return Retorna una fecha con el formato por defecto de Date
     */
    public Date getFechaLimite(){
        return fechaLimite;
    }

    /**
     * @return Regresa una fecha con formato para poder ser guardado en una base de datos
     */
    public Long getFechaLimiteBD(){
        return fechaLimite.getTime();
    }

    /**
     * @return Regresa un String de 15 caracteres para identificar la reservación
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
    //</editor-fold>

    /**
     * @param item Recibe un ítem válido y configura la fecha límite para pedirlo
     * @throws SQLException Si hay un error al conectar con la base de datos, regresa una SQLexception
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
     * @return Regresa el identificador generado por el sistema, un String de 15 caracteres
     */
    public String generarIdentificador(){
        identificadorReservacion = Util.generadorDeIdentificador();
        return identificadorReservacion;
    }

    /**
     * @return Regresa un entero de acuerdo al estado de la reservación, 0 si no es posible y un entero positivo si lo fue
     * @throws SQLException Si hay problemas al conectar con la base de datos, regresa una SQLException
     */
    public int realizarReservacion() throws SQLException{
        ReservacionDAOImpl reservacionDAO = new ReservacionDAOImpl();
        int estadoReservacion = 0;
        try{
            estadoReservacion = reservacionDAO.reservarItem(this);
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoReservacion;
    }

}
