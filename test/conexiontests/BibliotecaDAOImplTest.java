package conexiontests;

import dominio.Item;
import dataaccess.BibliotecaDAOImpl;
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
 * Pruebas de la implementación de la clase biblioteca
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */public class BibliotecaDAOImplTest{
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles">
    Item item = new Item();
    List<Item> items = new ArrayList<>();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItem = "identif006";
    String titulo = "Software requirements";
    String identificador = "identif001";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    BibliotecaDAOImpl instance = new BibliotecaDAOImpl();
    int resultado;
    //</editor-fold>

    public BibliotecaDAOImplTest(){
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
        item.setTitulo(titulo);
        item.setIdentificador(identificador);
        items.add(item);
    }

    @After
    public void tearDown(){
    }

    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas">
    @Test
    public void testBuscarItem() throws Exception{
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(item.getIdentificador());
        assertEquals(expResult, result);
    }

    @Test
    public void testBuscarItemInexistente() throws Exception{
        Item itemAux = null;
        List<Item> expResult = (List<Item>) itemAux;
        List<Item> result = instance.buscarItem(identificadorItem);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTiempoPrestamoDeItem() throws Exception{
        int expResult = 10;
        int result = instance.getTiempoPrestamoDeItem(identificador);
        assertEquals(expResult, result);
    }
    //</editor-fold>

}
