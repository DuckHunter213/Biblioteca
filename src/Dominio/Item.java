package Dominio;

import java.util.List;
import java.text.NumberFormat;
import java.util.ArrayList;
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
    
    public List<Item> buscarItem(String codigoBarras, ArrayList listaItems, String tipoItem, String filtroBusqueda){return null;}
    public boolean actualizarItem(){return false;}
    public boolean eliminarItem(){return false;}
    
    private boolean existeItem(ArrayList<Item> items){return false;}
    public boolean agregarItem(Item item){return true;}
    public List<dataaccess.ItemDAO> regresarTodo(){return null;}
    
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
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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

    public int getTiempoPrestamo() {
        return tiempoPrestamo;
    }
    
    public void setTiempoPrestamo(int dias) {
        this.tiempoPrestamo = dias;
    }
    
    //</editor-fold>
    
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
