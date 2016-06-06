/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import biblioteca.Util;
import dataaccess.ReservacionDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author gerar
 */
public class Reservacion {
    private Item item;
    private String identificadorUsuario;
    private Date fechaLimite;
    private String identificadorReservacion;

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
    
    public Reservacion(Item item) throws SQLException{
        this.item = item;
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.add(Calendar.DAY_OF_WEEK_IN_MONTH, 10);
        fechaLimite = new Date(); 
        fechaLimite.setTime(calendario.getTimeInMillis());
    }
    public String generarIdentificador(){
        identificadorReservacion = Util.generadorDeIdentificador();
        return identificadorReservacion;
    }
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
