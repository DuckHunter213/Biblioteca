/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.ResultSet;
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
public class ItemDAOImplTest {
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
    
    public ItemDAOImplTest() {
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
        
        item2.setIdentificador("S140111131");
        item2.setTitulo("100 galletas de waffles");
        item2.setAutor("Pedro");
        item2.setCostoMulta(10);
        item2.setFechaAdquisicion(2016,0,13);
        item2.setFechaPublicación(2012,0,13);
        item2.setTiempoPrestamo(10);
        items2.add(item2);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testBuscarItem() throws Exception {
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscarItemInexistente() throws Exception {
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(identificador);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testReservarItemFallido() {
        int expResult = 0;
        int result = instance.reservarItem(item, matriculaErronea,folioPrestamo);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testReservarItemExitoso() {
        int expResult = 1;
        int result = instance.reservarItem(item, matricula,folioPrestamo);
        assertEquals(expResult, result);
    }

    /** 
     * Test of eliminarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testquitarItemDeReservacionExitoso() {
        int expResult = 1;
        int result = instance.quitarItemDeReservacion(folioPrestamo);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testquitarItemDeReservacionFallido() {
        int expResult = 0;
        int result = instance.quitarItemDeReservacion(folioErroneo);
        assertEquals(expResult, result);
    }

    /**
     * Test of regresarTodo method, of class ItemDAOImpl.
     */
    @Test
    public void testRegresarTodo() {
        List<Item> expResult = items;
        List<Item> result = instance.regresarTodo();
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
     * Test of prestarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testPrestarItemFallido() {
        int expResult = 0;
        int result = instance.prestarItem(item2, matriculaErronea,folioPrestamo,folioDevolucion);
        assertEquals(expResult, result);
    }

    /**
     * Test of quitarItemDePrestamo method, of class ItemDAOImpl.
     */
    @Test
    public void testQuitarItemDePrestamoExitoso() {
        int expResult = 1;
        int result = instance.quitarItemDePrestamo(folioPrestamo);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of quitarItemDePrestamo method, of class ItemDAOImpl.
     */
    @Test
    public void testQuitarItemDePrestamoFallido() {
        int expResult = 0;
        int result = instance.quitarItemDePrestamo(folioErroneo);
        assertEquals(expResult, result);
    }
    
}