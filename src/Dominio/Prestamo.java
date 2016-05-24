package Dominio;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Clase prestamo es generada cada ves que se piden items
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class Prestamo {
    private Item item;
    private Usuario usuario;
    private GregorianCalendar fechaPrestamo;
    private GregorianCalendar fechaCaducidad;
    private String identificadorPrestamo;
    
    public Prestamo(){
        identificadorPrestamo = "";
        fechaPrestamo = new GregorianCalendar(Locale.ROOT);
    }    
    
    public boolean setUSuario(Usuario usuario){
        this.usuario = usuario;
        return true;
    }
    
    public boolean setItem(Item item){
        this.item = item;
        return true;
    }
}
