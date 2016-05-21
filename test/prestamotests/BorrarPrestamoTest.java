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
 * Descripción:           Contiene pruebas para borrar registros de la base de datos,
 *                        todas las pruebas son referentes a los items (ItemDAOImpl).
 */
public class BorrarPrestamoTest {    
    //<editor-fold defaultstate="collapse" desc="Definicion de variables">
    Item item = new Libro();
    String identificadorAlumno = "IDENTIFICADORA6";
    String identificadorItemErroneo = "identif005";
    String identificadorItem = "identif006";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO= 10;
    ItemDAOImpl instance = new ItemDAOImpl();
    int resultado;
    //</editor-fold>
    
    public BorrarPrestamoTest() {
    }
    
    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before 
    public void setUp() throws Exception{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        instance.prestarItem(item, identificadorAlumno);
    }
    
    @After
    public void tearDown() throws SQLException{
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapse" desc="Pruebas de unidad">
    @Test
    public void testQuitarItemDePrestamoExitoso() throws SQLException {
        int expResult = 1;
        int result = instance.quitarItemDePrestamo(item.getIdentificador());
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testQuitarItemDePrestamoFallido() throws SQLException {
        int expResult = 0;
        instance.quitarItemDePrestamo(item.getIdentificador());
        item.setIdentificador(identificadorItemErroneo);
        int result = instance.quitarItemDePrestamo(item.getIdentificador());
        assertEquals(expResult, result);
    }
    //</editor-fold>
}