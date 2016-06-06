package Dominio;

import biblioteca.Util;
import dataaccess.ReservacionDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Francisco Gerardo Mares Solano
 */
public class Reservacion {
    private Item item;
    private String identificadorUsuario;
    private Date fechaLimite;
    private String identificadorReservacion;

    //<editor-fold defaultstate="collapsed" desc=" Get´s & Set´s ">
    public String getIdentificadorUsuario() {
        return identificadorUsuario;
    }
    
    public void setIdentificadorUsuario(String identificador){
        this.identificadorUsuario = identificador;
    }
            
    public Date getFechaLimite() {
        return fechaLimite;
    }
            
    public Long getFechaLimiteBD() {
        return fechaLimite.getTime();
    }

    public String getIdentificadorReservacion() {
        return identificadorReservacion;
    }
    
    public String getIdentificadorItem(){
        return item.getIdentificador();
    }
    //</editor-fold>
    
    /**
     *
     * @param item
     * @throws SQLException
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
     * @return Regresa el identificador generado por el sistema
     */
    public String generarIdentificador(){
        identificadorReservacion = Util.generadorDeIdentificador();
        return identificadorReservacion;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public int realizarReservacion() throws SQLException{
        ReservacionDAOImpl reservacionDAO = new ReservacionDAOImpl();
        int estadoReservacion = 0;
        try{
            estadoReservacion = reservacionDAO.reservarItem(this);          
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoReservacion;
    }
}
