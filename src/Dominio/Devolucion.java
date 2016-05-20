package Dominio;

/**
 * Es el objeto del dominio es la devolución de los items este siempre debe 
 * estar asociado a un prestamo
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class Devolucion {
    private Item item;
    private Usuario usuario;
    private Prestamo itemPrestado;
    private String identificadorDevolucion;
}
