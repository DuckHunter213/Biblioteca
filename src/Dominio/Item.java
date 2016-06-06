package Dominio;

import dataaccess.BibliotecaDAOImpl;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Clase abstracta por facilidad de extener la funcionalidad contiene atributos
 * generales para cualquier clase de item que podría haber y facilita las
 * operaciones del sistema por medio de polimorfismo
 * 
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */

public abstract class Item {
    private String autor;
    private String titulo;
    private String identificador;
    private Calendar fechaAdquisicion = Calendar.getInstance();
    private Calendar fechaPublicación = Calendar.getInstance();
    private NumberFormat costoMulta = NumberFormat.getInstance();
    private int tiempoPrestamo;
    
    //<editor-fold defaultstate="collapsed" desc=" Get´s & Set´s ">
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return El identificador con formato
     * "016" 3 ultimos digitos del año
     * "05" mes "20" dia "06" hora(formato 24 horas) "05" segundo
     * Salida: 01605200607
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador El identificador con formato
     * "016" 3 ultimos digitos del año
     * "05" mes "20" dia "06" hora(formato 24 horas) "07" segundo
     * Salida: 01605200607
     * @return 
     */
    public boolean setIdentificador(String identificador) {
        boolean estadoIdentificador = false;
        if (validacionIdentificadorItem(identificador)){
            this.identificador = identificador;
            estadoIdentificador = true;
        }
        return estadoIdentificador;
    }

    public Calendar getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(int anio, int mes, int dia) {
        this.fechaAdquisicion.set(2013,0,13);
    }

    public Calendar getFechaPublicación() {
        return fechaPublicación;
    }

    public void setFechaPublicación(int anio, int mes, int dia) {
        this.fechaAdquisicion.set(anio, mes, dia);
    }

    /**
     * @return Regresa el valor unitario de precio de multa
     */
    public NumberFormat getCostoMulta() {
        return costoMulta;
    }

    /**
     * @param precio Asigna el valor de multa
     */
    public void setCostoMulta(int precio) {
        this.costoMulta.format(precio);
    }

    public int getTiempoPrestamo() throws SQLException{
        int tiempoPrestamo = 0;
        if(identificador.length() == 9 && (identificador.toLowerCase()).startsWith("s") ){
            BibliotecaDAOImpl bibliotecaAuxiliar = new BibliotecaDAOImpl();
            try{
                tiempoPrestamo = bibliotecaAuxiliar.getTiempoPrestamoDeItem(identificador);
            }catch(SQLException ex){
                throw new SQLException("Hubo un error con la BD: " + ex.getMessage());                
            }
        }
        return tiempoPrestamo;
    }
    
    public void setTiempoPrestamo(int dias) {
        this.tiempoPrestamo = dias;
    }
    //</editor-fold>
    
    private boolean validacionIdentificadorItem(String identificadorItem){
        boolean identificadorValido=false;
        try{
            if (identificadorItem.length() == 9 && (identificadorItem.toLowerCase()).startsWith("i") ){
                identificadorValido = true;
            }
        }catch(NullPointerException ex){
            throw new NullPointerException("El identificador no es válido: " + ex.getMessage());            
        }
        return true;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
	result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Item other = (Item) obj;
	if (identificador == null) {
		if (other.identificador != null)
			return false;
	} else if (!identificador.equals(other.identificador))
		return false;
	if (titulo == null) {
		if (other.titulo != null)
			return false;
	} else if (!titulo.equals(other.titulo))
		return false;
	return true;
    }
}
