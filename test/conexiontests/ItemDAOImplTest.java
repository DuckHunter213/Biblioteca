package conexiontests;

import Dominio.Item;
import Dominio.Libro;
import dataaccess.ItemDAOImpl;
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
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles
    Item item = new Libro();
    List<Item> items = new ArrayList<>();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItem = "identif006";
    String titulo = "Software requirements";
    String identificador = "identif001";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO= 10;
    ItemDAOImpl instance = new ItemDAOImpl();
    int resultado;
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
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        item.setTitulo(titulo);
        item.setIdentificador(identificador);
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
        List<Item> result = instance.buscarItems(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscarItemInexistente() throws Exception {
        items.clear();
        List<Item> expResult = items;
        List<Item> result = instance.buscarItems(identificadorItem);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}