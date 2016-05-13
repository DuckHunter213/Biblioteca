package Dominio;

import dataaccess.Conexion;
import java.util.List;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public abstract class Item {
    private String autor;
    private String titulo;
    private String identificador;
    private Calendar fechaAdquisicion = Calendar.getInstance();
    private Calendar fechaPublicación = Calendar.getInstance();
    private NumberFormat costoMulta = NumberFormat.getInstance();
    private int tiempoPrestamo;
    
    public List<Item> buscarItem(String codigoBarras, ArrayList listaItems, String tipoItem, String filtroBusqueda){
        return null;
    }
    public boolean eliminarItem(String identificador){
        return false;
    }
    
    private boolean existeItem(ArrayList<Item> items){        return false;    }
    public boolean agregarItem(Item item){        return true;    }
    public List<dataaccess.ItemDAO> regresarTodo(){return null;}

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the fechaAdquisicion
     */
    public Calendar getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * @param anio
     * @param mes
     * @param dia
     */
    public void setFechaAdquisicion(int anio, int mes, int dia) {
        this.fechaAdquisicion.set(2013,0,13);
    }

    /**
     * @return the fechaPublicación
     */
    public Calendar getFechaPublicación() {
        return fechaPublicación;
    }

    /**
     * @param anio
     * @param mes
     * @param dia
     */
    public void setFechaPublicación(int anio, int mes, int dia) {
        this.fechaAdquisicion.set(anio, mes, dia);
    }

    /**
     * @return the costoMulta
     */
    public NumberFormat getCostoMulta() {
        return costoMulta;
    }

    /**
     * @param precio
     */
    public void setCostoMulta(int precio) {
        this.costoMulta.format(precio);
    }

    /**
     * @return the tiempoPrestamo
     */
    public int getTiempoPrestamo() {
        return tiempoPrestamo;
    }

    /**
     * @param tiempoPrestamo the tiempoPrestamo to set
     */
    public void setTiempoPrestamo(int dias) {
        this.tiempoPrestamo = dias;
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
