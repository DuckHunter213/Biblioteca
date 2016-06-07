package prestamotests;

import dominio.Item;
import dominio.Prestamo;
import dataaccess.PrestamoDAOImpl;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Contiene pruebas para registrar registros de la base de datos, todas las
 * pruebas son referentes a la clase prestamo
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class InsercionesPrestamoTest{
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles ">
    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Prestamo prestamo;
    PrestamoDAOImpl instance = new PrestamoDAOImpl();
    int resultado;
    //</editor-fold>

    public InsercionesPrestamoTest(){
    }

    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
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

    @After
    public void tearDown() throws SQLException{
        instance.quitarPrestamoDeBaseDeDatos(identificadorItem);
    }
    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas">

    @Test
    public void testPrestarItemExitoso() throws SQLException{
        int expResult = 1;
        int result = instance.guardarRegistroDePrestamo(prestamo);
        assertEquals(expResult, result);
    }

    @Test(expected = SQLException.class)
    public void testPrestarItemFallidoId() throws SQLException{
        int expResult = 0;
        item.setIdentificador(identificadorItemErroneo);
        prestamo.setItem(item);
        int result = instance.guardarRegistroDePrestamo(prestamo);
        assertEquals(expResult, result);
    }

    @Test
    public void testPrestarItemFallidoRepetido() throws SQLException{
        int expResult = 0;
        instance.guardarRegistroDePrestamo(prestamo);
        int result = instance.guardarRegistroDePrestamo(prestamo);
        assertEquals(expResult, result);
    }
    //</editor-fold>

}
