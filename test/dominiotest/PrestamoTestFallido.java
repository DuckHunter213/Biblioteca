package dominiotest;

import dominio.Item;
import dominio.Prestamo;
import dataaccess.PrestamoDAOImpl;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 */
public class PrestamoTestFallido{

    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItem = "identif010";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Prestamo prestamo;
    PrestamoDAOImpl instance = new PrestamoDAOImpl();
    int resultado;

    public PrestamoTestFallido(){
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp() throws SQLException{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario(identificadorAlumno);
    }

    @Test(expected = SQLException.class)
    public void testRealizarPrestamoFallido() throws SQLException{
        int expResult = 0;
        item.setIdentificador(identificadorItem);
        prestamo.setItem(item);
        int result = prestamo.realizarPrestamo();
        assertEquals(expResult, result);
    }

}
