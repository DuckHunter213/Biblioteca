package bibliotecatest;

import biblioteca.Util;
import dominio.Item;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de la clase de utilidades, donde cacha los errores SQL
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class UtilErroresSQL{
    Item item = new Item();
    String identificadorItemErroneo = "identif010";
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorUsuarioErroneo = "IDENTIFICADORA15";
    String identificadorItem = "identif005";
    String nombreAutor = "Wiegers";
    String nombreLibro = "Teardrops";
    String categoria = "Libro";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;

    public UtilErroresSQL(){
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp() throws SQLException{
    }

    @After
    public void tearDown(){
    }

    /**
     * Test of itemEstadoDisponibilidad method, of class Util.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = SQLException.class)
    public void testItemEstadoDisponibilidadExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.itemEstadoDisponibilidad(identificadorItem);
        assertEquals(expResult, result);
    }

    /**
     * Test of revisarLimitePrestamos method, of class Util.
     */
    @Test(expected = SQLException.class)
    public void testRevisarLimitePrestamosExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.revisarLimitePrestamos(identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorUsuario method, of class Util.
     */
    @Test(expected = SQLException.class)
    public void testVerificarIdentificadorUsuarioExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.verificarIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorItem method, of class Util.
     */
    @Test
    public void testVerificarIdentificadorItemExitoso() throws Exception{
        boolean expResult = false;
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }

    @Test(expected = SQLException.class)
    public void testVerificarIdentificadorItemFallido() throws Exception{
        boolean expResult = true;
        item.setIdentificador(identificadorItemErroneo);
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }

}
