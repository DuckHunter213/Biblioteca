
package Dominio;

import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class Prestamo {
    private Item item;
    private Usuario usuario;
    private GregorianCalendar fechaPrestamo;
    private GregorianCalendar fechaCaducidad;
    private String identificadorPrestamo;
    
    public Prestamo(){
      identificadorPrestamo =  "Hay que setearlo";
      fechaPrestamo = new GregorianCalendar(Locale.ROOT);
    }    
    //public boolean agregarItemABaseDeDatos(){
        
    //}
    public boolean setUSuario(Usuario usuario){
        this.usuario = usuario;
        return true;
    }
    public boolean setItem(Item item){
        this.item = item;
        return true;
    }    
    
}
