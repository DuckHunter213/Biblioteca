package dominio;

import biblioteca.Util;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Clase abstracta por facilidad de extener la funcionalidad; contiene atributos
 * generales para cualquier clase de item que podría haber y facilita las
 * operaciones del sistema por medio de polimorfismo.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class Item{
    private String autor;
    private String titulo;
    private String identificador;
    private String categoria;
    private final Calendar fechaAdquisicion = Calendar.getInstance();
    private final Calendar fechaPublicación = Calendar.getInstance();
    private final NumberFormat costoMulta = NumberFormat.getInstance();
    private int tiempoPrestamo;

    //<editor-fold defaultstate="collapsed" desc=" Get´s & Set´s ">
    /**
     * Obtiene al autor del ítem.
     *
     * @return Regresa el nombre del autor completo
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Obtiene la categoría del ítem
     *
     * @return Regresa la categoria a la que pertecene el ítem
     */
    public String getCategoria(){
        return categoria;
    }

    /**
     * @param categoria Una cadena de texto con la categoría del ítem
     * @return 
     */
    public boolean setCategoria(String categoria){
        boolean estadoAgregacion = false;
        if (categoria.length() >= 2){
            this.categoria = categoria;
            estadoAgregacion = true;
        }
        return estadoAgregacion;
    }

    /**
     * Establece el nombre del autor para el ítem.
     *
     * @param autor Un String con el nombre completo del autor o autores
     * separados por comas.
     * @return Regresa true si pudo agregar el valor y false sino.
     */
    public boolean setAutor(String autor){
        boolean estadoAgregacion = false;
        if (autor.length() >= 2){
            estadoAgregacion = true;
            this.autor = autor;
        }
        return estadoAgregacion;
    }

    /**
     * Obtiene el título del libro
     *
     * @return Obtiene el título del libro competo. El máximo de caracteres que
     * puede almacenar son 255.
     */
    public String getTitulo(){
        return titulo;
    }

    /**
     * Almacena el título del ítem
     *
     * @param titulo Recibe una cadena de longitud máxima de 255 caracteres.
     * @return Regresa true si pudo agregar el valor y false sino.
     */
    public boolean setTitulo(String titulo){
        boolean estadoAgregacion = false;
        if (titulo.length() >= 2){
            estadoAgregacion = true;
            this.titulo = titulo;
        }
        return estadoAgregacion;
    }

    /**
     * Regresa el identificador del ítem
     *
     * @return Retorna el identificador del ítem el cual es una cadena de 10
     * caracteres.
     */
    public String getIdentificador(){
        return identificador;
    }

    /**
     * @param identificador Recibe el identificador del ítem, el cual debe ser
     * una cadena de 10 caracteres de longitud y debe existir un ítem con ese
     * identificador en la base de datos.
     * @return Regresa un booleano indicando si fue posible guardar el
     * identificador; Es true si el identificador coincide con alguno de la base
     * de datos y false si no cumple esos criterios.
     * @throws java.sql.SQLException Si no es posible conectar con la base de
     * datos, se lanza una SQLException.
     */
    public boolean setIdentificador(String identificador) throws SQLException{
        boolean estadoIdentificador = false;
        if (Util.verificarIdentificadorItem(identificador)){
            this.identificador = identificador;
            estadoIdentificador = true;
        }
        return estadoIdentificador;
    }

    /**
     * @return Regresa la fecha en que fue adquirido el préstamo, su formato es
     * el que trae por defecto la clase Calendar
     */
    public Calendar getFechaAdquisicion(){
        return fechaAdquisicion;
    }

    /**
     * Guarda la fecha que se le pasa.
     *
     * @param anio el año que se desea agregar con formato YYYY
     * @param mes El mes que se desea agregar con formato MM, comienza con el
     * mes 0 y llega a 11, siendo Enero igual a 0
     * @param dia El día que se desea agregar con formato DD
     */
    public void setFechaAdquisicion(int anio, int mes, int dia){
        this.fechaAdquisicion.set(2013, 0, 13);
    }

    /**
     * @return Regresa le fecha registrada en que fue guardado el ítem
     */
    public Calendar getFechaPublicación(){
        return fechaPublicación;
    }

    /**
     * Guarda la fecha de publicación del ítem
     *
     * @param anio el año que se desea agregar con formato YYYY
     * @param mes El mes que se desea agregar con formato MM, comienza con el
     * mes 0 y llega a 11, siendo Enero igual a 0
     * @param dia El día que se desea agregar con formato DD
     */
    public void setFechaPublicación(int anio, int mes, int dia){
        this.fechaAdquisicion.set(anio, mes, dia);
    }

    /**
     * @return Regresa el valor unitario de precio de multa con el formato por defecto de NumberFormat
     */
    public NumberFormat getCostoMulta(){
        return costoMulta;
    }

    /**
     * @param precio Asigna el valor de multa en enteros y suponiendo que es pesos, por tanto, un parámetro 10, se convertirá en 10 pesos.
     */
    public void setCostoMulta(int precio){
        this.costoMulta.format(precio);
    }

    /**
     * @return regresar la cantidad de días que el ítem podrá ser prestado en formato de entero, un valor 10, significa por tanto, 10 días
     * @throws SQLException Si hay un error al consultar la base de datos, se lanzará una SQLException.
     */
    public int getTiempoPrestamo() throws SQLException{
        return tiempoPrestamo;
    }

    /**
     * @param dias Recibe un entero indicando la cantidad de días que el ítem será prestado.
     * @return Regresa true si pudo agregar el valor y false sino.
     */
    public boolean setTiempoPrestamo(int dias){
        boolean estadoAgregacion = false;
        if (tiempoPrestamo > 0){
            this.tiempoPrestamo = dias;
            estadoAgregacion = true;
        }
        return estadoAgregacion;
    }
    //</editor-fold>

    /**
     * Sobreescribe el hashCode de la clase para poder comparar objetos de ésta clase
     *
     * @return regresa el hashCode generado por el objeto
     */
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    /**
     * Sobreescribe el método equals para que al comprar 2 objetos de la misma
     * clase, compare los atributos deseados para determinar si son objeto iguales
     * o no.
     *
     * @param obj Recibe el objeto que será comparado con la instancia que convoca
     * al método
     * @return Regresa un booleano, true si son iguales los objetos y false si son
     * diferentes.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        Item other = (Item) obj;
        if (identificador == null){
            if (other.identificador != null){
                return false;
            }
        }else if (!identificador.equals(other.identificador)){
            return false;
        }
        if (titulo == null){
            if (other.titulo != null){
                return false;
            }
        }else if (!titulo.equals(other.titulo)){
            return false;
        }
        return true;
    }

}
