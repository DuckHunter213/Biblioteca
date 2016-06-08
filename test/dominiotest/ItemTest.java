package dominiotest;

import dominio.Item;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas para los métodos de la clase test
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class ItemTest{
    Item item = new Item();
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    String nombreAutor = "Wiegers";
    String nombreLibro = "Teardrops";
    String categoria = "Libro";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;

    public ItemTest(){
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp() throws SQLException{
        item.setAutor(nombreAutor);
        item.setCategoria(categoria);
        item.setTitulo(nombreLibro);
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setFechaAdquisicion(2013, 00, 13);
        item.setFechaPublicación(2010, 8, 15);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
    }

    @After
    public void tearDown(){
    }

    /**
     * Test of getAutor method, of class Item.
     */
    @Test
    public void testGetAutor(){
        String expResult = "Wiegers";
        String result = item.getAutor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoria method, of class Item.
     */
    @Test
    public void testGetCategoria(){
        String expResult = "Libro";
        String result = item.getCategoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategoria method, of class Item.
     */
    @Test
    public void testSetCategoriaExitosa(){
        boolean expResult = true;
        boolean result = item.setCategoria("Revista");
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategoria method, of class Item.
     */
    @Test
    public void testSetCategoriaFallido(){
        boolean expResult = false;
        boolean result = item.setCategoria("");
        assertEquals(expResult, result);
    }

    /**
     * Test of setAutor method, of class Item.
     */
    @Test
    public void testSetAutorExitoso(){
        boolean expResult = true;
        boolean result = item.setAutor("Carls");
        assertEquals(expResult, result);
    }

    /**
     * Test of setAutor method, of class Item.
     */
    @Test
    public void testSetAutorFallido(){
        boolean expResult = false;
        boolean result = item.setAutor("");
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitulo method, of class Item.
     */
    @Test
    public void testGetTitulo(){
        String expResult = "Teardrops";
        String result = item.getTitulo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitulo method, of class Item.
     */
    @Test
    public void testSetTituloExistos(){
        boolean expResult = true;
        boolean result = item.setTitulo("100 piedras");
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitulo method, of class Item.
     */
    @Test
    public void testSetTituloFallido(){
        boolean expResult = false;
        boolean result = item.setTitulo("");
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdentificador method, of class Item.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetIdentificador() throws SQLException{
        String expResult = "identif005";
        String result = item.getIdentificador();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentificador method, of class Item.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetIdentificadorExitoso() throws Exception{
        boolean expResult = true;
        boolean result = item.setTitulo("identif006");
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentificador method, of class Item.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetIdentificadorFallido() throws Exception{
        boolean expResult = false;
        boolean result = item.setTitulo("");
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Item.
     */
    @Test
    public void testEqualsExitoso(){
        Item item2 = item;
        boolean expResult = true;
        boolean result = item.equals(item2);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Item.
     */
    @Test
    public void testEqualsFallido(){
        Item item2 = null;
        boolean expResult = false;
        boolean result = item.equals(item2);
        assertEquals(expResult, result);
    }

}
