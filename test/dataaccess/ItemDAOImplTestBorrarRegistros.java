package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de borrado
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class ItemDAOImplTestBorrarRegistros {
    
    //<editor-fold defaultstate="collapse" desc="Definicion de variables">
    Item item = new Libro();
    List<Item> items = new ArrayList<>();
    String matricula = "S140111131";
    String matriculaErronea = "bloodDrops";
    String identificador = "S140111132";
    String folioPrestamo = "teardrops";
    String folioDevolucion = "Dark horse";
    String folioErroneo = "sandDrops";
    ItemDAOImpl instance = new ItemDAOImpl();
    int resultado;
    //</editor-fold>
    
    public ItemDAOImplTestBorrarRegistros() {
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
        item.setIdentificador(identificador);
        item.setTitulo("Como volver a comer lo que ya comiste");
        item.setAutor("Waffles");
        item.setCostoMulta(10);
        item.setFechaAdquisicion(2016,0,13);
        item.setFechaPublicación(2012,0,13);
        item.setTiempoPrestamo(10);
        items.add(item);
        resultado = instance.reservarItem(item, matricula);
        resultado = instance.prestarItem(item, matricula);
    }
    
    @After
    public void tearDown(){
        int result = instance.quitarItemDeReservacion(item.getIdentificador());
        result = instance.quitarItemDePrestamo(item.getIdentificador());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapse" desc="Pruebas de unidad">
    @Test
    public void testQuitarItemDePrestamoExitoso() {
        int expResult = 1;
        int result = instance.quitarItemDePrestamo(item.getIdentificador()); 
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testQuitarItemDePrestamoFallido() {
        int expResult = 0;
        int result = instance.quitarItemDePrestamo(folioErroneo);
    }
    
    @Test
    public void testquitarItemDeReservacionExitoso() {
        int expResult = 1;
        int result = instance.quitarItemDeReservacion(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testquitarItemDeReservacionFallido() {
        int expResult = 0;
        int result = instance.quitarItemDeReservacion(folioErroneo);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
