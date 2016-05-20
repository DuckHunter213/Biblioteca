package Dominio;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Nombre del programa:    Biblioteca
 * Nombres:                @author Luis Fernando Gomez Alejandre
 *                         @author Francisco Gerardo Mares Solano
 * Fecha:                  @since 20/05/2016
 * Descripción:            Clase prestamo es generada cada ves que se piden
 *                         items
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
