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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testBuscarItem() throws Exception {
        
        Item item = new Libro();
        item.setIdentificador("S140111132");
        item.setTitulo("Matatenas");
        item.setAutor("Pedro");
        item.setCostoMulta(10);
        item.setFechaAdquisicion(2016,0,13);
        item.setFechaPublicación(2012,0,13);
        item.setTiempoPrestamo(10);
        List<Item> items = new ArrayList<>();
        items.add(item);        
        
        String identificador = "S140111132";
        ItemDAOImpl instance = new ItemDAOImpl();
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(identificador);
        assertEquals(expResult, result);
    }
    @Test
    public void testBuscarItemInexistente() throws Exception {
        List<Item> items = new ArrayList();
        String identificador = "S140111131";
        ItemDAOImpl instance = new ItemDAOImpl();
        List<Item> expResult = items;
        List<Item> result = instance.buscarItem(identificador);
        assertEquals(expResult, result);
    }

    /**
     * Test of actualizarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testActualizarItem() {
        System.out.println("actualizarItem");
        ItemDAOImpl instance = new ItemDAOImpl();
        boolean expResult = false;
        boolean result = instance.actualizarItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testEliminarItem() {
        System.out.println("eliminarItem");
        ItemDAOImpl instance = new ItemDAOImpl();
        boolean expResult = false;
        boolean result = instance.eliminarItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarItem method, of class ItemDAOImpl.
     */
    @Test
    public void testAgregarItem() {
        
        Item item = new Libro();
        item.setIdentificador("S14011618");
        item.setTitulo("100 galletas de waffles");
        item.setAutor("Waffles");
        item.setCostoMulta(10);
        item.setFechaAdquisicion(2016,0,13);
        item.setFechaPublicación(2012,0,13);
        item.setTiempoPrestamo(10);
        
        System.out.println("agregarItem");
        ItemDAOImpl instance = new ItemDAOImpl();
        boolean expResult = true;
        boolean result = instance.agregarItem(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of regresarTodo method, of class ItemDAOImpl.
     */
    @Test
    public void testRegresarTodo() {
        System.out.println("regresarTodo");
        ItemDAOImpl instance = new ItemDAOImpl();
        List<ItemDAO> expResult = null;
        List<ItemDAO> result = instance.regresarTodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}