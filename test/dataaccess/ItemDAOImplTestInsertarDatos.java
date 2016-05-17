/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author gerar
 */
public class ItemDAOImplTestInsertarDatos {
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
    
    public ItemDAOImplTestInsertarDatos() {
    }
    
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
        resultado = instance.quitarItemDePrestamo(folioPrestamo);
        resultado = instance.quitarItemDeReservacion(folioPrestamo);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
        
    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testReservarItemExitoso() throws Exception {
        int expResult = 1;
        int result = instance.reservarItem(item, matricula,folioPrestamo);
        assertEquals(expResult, result);
    } 

    /**
     * Test of prestarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testPrestarItemExitoso() {
        int expResult = 1;
        int result = instance.prestarItem(item, matricula,folioPrestamo,folioDevolucion);
        assertEquals(expResult, result);
    }
    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     * @throws java.lang.Exception
     */
    @Test (expected = SQLException.class)
    public void testReservarItemFallidoSQL() throws Exception {
        int expResult = 0;
        int result = instance.reservarItem(item, matriculaErronea,folioPrestamo);
        assertEquals(expResult, result);
    }

    /**
     * Test of prestarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testPrestarItemFallido() {
        int expResult = 0;
        int result = instance.prestarItem(item2, matriculaErronea,folioPrestamo,folioDevolucion);
        assertEquals(expResult, result);
    }
   
    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     * @throws java.lang.Exception
     */
    @Test (expected = SQLException.class)
    public void testReservarItemFallidoRepetido() throws Exception {
        int expResult = 1;
        int result = instance.reservarItem(item, matricula,folioPrestamo);
        assertEquals(expResult, result);
    }
}
