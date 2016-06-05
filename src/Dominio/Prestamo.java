package Dominio;

import java.util.GregorianCalendar;
import biblioteca.Util;
import dataaccess.BibliotecaDAOImpl;
import dataaccess.PrestamoDAOImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase prestamo es generada cada ves que se piden items
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class Prestamo {
    private Item item;
    private String matriculaUsuario;
    private Date fechaPrestamo;
    private Date fechaCaducidad;
    private String identificadorPrestamo;
    
    public Prestamo(Item item) throws SQLException{ //Lanzar un NullPointerException
        this.item = new Libro();
        Calendar calendario = GregorianCalendar.getInstance();
        fechaPrestamo = new Date(); 
        fechaPrestamo.setTime(calendario.getTimeInMillis());
        this.item.setIdentificador(item.getIdentificador());
        try{
            identificadorPrestamo = Util.generadorDeIdentificador();
            //La cantidad de días que se presta, se consigue de la BD y se regresa
            calendario.add(Calendar.DAY_OF_WEEK_IN_MONTH, item.getTiempoPrestamo());
            fechaCaducidad = new Date();
            fechaCaducidad.setTime(calendario.getTimeInMillis());
        }catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());      
        }
    }
    
    public Long getFechaPrestamo(){
        return this.fechaPrestamo.getTime();
    }
    
    public Long getFechaCaducidad(){
        return this.fechaCaducidad.getTime();
    }
    
    public String getIdentificadorPrestamo(){
        return this.identificadorPrestamo;
    }
    
    public boolean setMatriculaUsuario(String matriculaUsuario){
        boolean estadoSetMatricula=false;
        if (Util.verificarIdentificadorAlumno(matriculaUsuario)){
            this.matriculaUsuario  = matriculaUsuario;
            estadoSetMatricula = true;
        }
        return estadoSetMatricula;
    }
    
    public String getMatriculaUsuario(){
        return this.matriculaUsuario;
    }
    
    public void setItem(Item item){
        this.item = item;
    }
    
    public String getIdentificadorItem(){
        return this.item.getIdentificador();
    }
    
    public int realizarPrestamo() throws SQLException{
        PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl();
        int estadoPrestamo = 0;
        try{
            estadoPrestamo = prestamoDAO.prestarItem(this);            
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoPrestamo;
    }
}
