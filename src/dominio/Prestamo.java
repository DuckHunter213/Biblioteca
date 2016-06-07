package dominio;

import java.util.GregorianCalendar;
import biblioteca.Util;
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
    public Long getFechaPrestamo(){
        return this.fechaPrestamo.getTime();
    }
    
    public Long getFechaCaducidad(){
        return this.fechaCaducidad.getTime();
    }
    
    public String getFechaCaducidadNormal(){
        return this.fechaCaducidad.toString();
    }
    
    public String getIdentificadorPrestamo(){
        return this.identificadorPrestamo;
    }
    
    public boolean setMatriculaUsuario(String matriculaUsuario) throws SQLException{
        boolean estadoSetMatricula = false;
        if (Util.verificarIdentificadorAlumno(matriculaUsuario)){
                this.matriculaUsuario = matriculaUsuario;
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
    //</editor-fold>
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public int realizarPrestamo() throws SQLException{
        PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl();
        int estadoPrestamo = 0;
        try{
            estadoPrestamo = prestamoDAO.guardarRegistroDePrestamo(this);          
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return estadoPrestamo;
    }
}
