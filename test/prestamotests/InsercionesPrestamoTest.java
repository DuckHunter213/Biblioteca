package prestamotests;

import Dominio.Item;
import Dominio.Libro;
import dataaccess.ItemDAOImpl;
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
 * Descripción:           Contiene pruebas para registrar registros de la base de datos,
 *                        todas las pruebas son referentes a los items (ItemDAOImpl).
 */
public class InsercionesPrestamoTest {    
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles
    Item item = new Libro();
    String identificadorAlumno = "IDENTIFICADORA6";
    String identificadorItemErroneo = "identif004";
    String identificadorItem = "identif006";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO= 10;
    ItemDAOImpl instance = new ItemDAOImpl();
    int resultado;
    //</editor-fold>
    
    public InsercionesPrestamoTest() {
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
    }
    
    @After
    public void tearDown() throws SQLException {
        instance.quitarItemDePrestamo(identificadorItem);
    }
    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas">
    
    @Test
    public void testPrestarItemExitoso() throws SQLException {
        int expResult = 1;
        int result = instance.prestarItem(item, identificadorAlumno);
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testPrestarItemFallidoId() throws SQLException {
        int expResult = 0;
        item.setIdentificador(identificadorItemErroneo);
        int result = instance.prestarItem(item, identificadorAlumno);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
