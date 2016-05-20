package dataaccess;

import Dominio.Item;
import java.sql.SQLException;
import java.util.List;



/**
 * Nombre del programa:   Biblioteca
 * Nombres:               @author Luis Fernando Gomez Alejandre
 *                        @author Francisco Gerardo Mares Solano
 * Fecha:                 @since 20/05/2016
 * Descripción:           Interface para generar el patrón DAO para guardar item en la base de datos
 *                        se decidió usar una interface para el uso de polimorfismo.
 */

public interface ItemDAO {
    
    /**
     * Funcion que busca en todos los items
     * @param identificador identi 
     * @return Regresa una lista con los items encontrados en dado caso de no 
     * encontrar ninguno regresa la lista vacia
     * @throws SQLException Salta la excepcion en //TODO
     */
    public List<Item> buscarItem(String identificador) throws SQLException;

    /**
     * Quita el item buscado previamente de una reservación
     * @param identificador es el parametro por el cual sera quitado el item de
     * una reservación
     * @return regresa una retroalimentación //TODO
     */
    public int quitarItemDeReservacion(String identificador);

    /**
     * Reserva el item pasado se asocia a una matricula de usuario y se genera
     * en un folio que es la referecia de esa reservación
     * @param item item que sera reservado
     * @param matricula matricula que estara asociado a un usuario
     * @param folio Se genera un folio automatico para referenciar el ticket de
     * reservación 
     * @return Regresa una retroalimentación al usuario //TODO
     * @throws Exception //TODO
     */
    public int reservarItem(Item item, String matricula) throws Exception;

    /**
     *
     * @param identificador
     * @return
     */
    public int quitarItemDePrestamo(String identificador);

    /**
     * Funcion que enlaza un item a un usuario y se guarda un registro en la 
     * base de datos
     * @param item item a prestar al usuario
     * @param matricula usuario al que se le prestara el item
     * @return regresa un valor de retroalimentacion //TODO valor booleano
     */
    public int prestarItem(Item item, String matricula);

    /**
     * Funcion que regresa todos los items existentes de la base de datos
     * @return regresa una lista con todos los items existentes en la base de
     * datos
     */
    public List<Item> regresarTodo();
}