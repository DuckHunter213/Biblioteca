package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Nombre del programa:   Biblioteca
 * Nombres:               @author Luis Fernando Gomez Alejandre
 *                        @author Francisco Gerardo Mares Solano
 * Fecha:                 @since 20/05/2016
 * Descripción:           Contiene pruebas generales de busqueda de registros de la base de datos,
 *                        todas las pruebas son referentes a los items (ItemDAOImpl).
 */
public class ItemDAOImplTest {    
    //<editor-fold defaultstate="collapse" desc="Definicion de variables">
    Item item = new Libro();
    List<Item> items = new ArrayList<>();
    String matricula = "S140111131";
    String matriculaErronea = "bloodDrops";
    String identificador = "S140111132";
    String folioPrestamo = "teardrops";
    String folioDevolucion = "Dark horse";
    ItemDAOImpl instance = new ItemDAOImpl();
    //</editor-fold>
    
    public ItemDAOImplTest() {
    }
    
    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {  
        item.setIdentificador("S140111132");
        item.setTitulo("Como volver a comer lo que ya comiste");
        item.setAutor("Waffles");
        item.setCostoMulta(10);
        item.setFechaAdquisicion(2016, 0, 13);
        item.setFechaPublicación(2012, 0, 13);
        item.setTiempoPrestamo(10);        
        items.add(item);  
    }
    
    @After
    public void tearDown() {
    }
    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas">
    @Test
    public void testBuscarItem() throws Exception {
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscarItemInexistente() throws Exception {
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(identificador);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRegresarTodo() {
        List<Item> expResult = items;
        List<Item> result = instance.regresarTodo();
        assertEquals(expResult, result);
    }
    //</editor-fold>
}