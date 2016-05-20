package dataaccess;

import Dominio.Item;
import Dominio.Libro;
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

public class ItemDAOImplTestInsertarDatos {
    
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles">   
    Item item = new Libro();
    List<Item> items = new ArrayList<>();
    String matricula = "S140111131";
    String matriculaErronea = "bloodDrops";
    String identificador = "S140111132";
    String folioPrestamo = "teardrops";
    String folioDevolucion = "Dark horse";
    String folioErroneo = "sandDrops";
    Item item2 = new Libro();
    List<Item> items2 = new ArrayList<>();
    ItemDAOImpl instance = new ItemDAOImpl();
    int resultado;
    //</editor-fold>
    
    public ItemDAOImplTestInsertarDatos() {
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
        item.setFechaAdquisicion(2016,0,13);
        item.setFechaPublicación(2012,0,13);
        item.setTiempoPrestamo(10);        
        items.add(item);
    }
    
    @After
    public void tearDown() {
        resultado = instance.quitarItemDePrestamo(item.getIdentificador());
        resultado = instance.quitarItemDeReservacion(item.getIdentificador());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapse" desc="Pruebas">    
    @Test
    public void testReservarItemExitoso() throws Exception {
        int expResult = 1;
        int result = instance.reservarItem(item, matricula);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPrestarItemExitoso() {
        int expResult = 1;
        int result = instance.prestarItem(item, matricula);
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testReservarItemFallidoSQL() throws Exception {
        int expResult = 0;
        int result = instance.reservarItem(item, matriculaErronea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPrestarItemFallido() {
        int expResult = 0;
        int result = instance.prestarItem(item2, matriculaErronea);
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testReservarItemFallidoRepetido() throws Exception {
        int expResult = 1;
        resultado = instance.reservarItem(item, matricula);
        int result =  instance.reservarItem(item, matricula);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
