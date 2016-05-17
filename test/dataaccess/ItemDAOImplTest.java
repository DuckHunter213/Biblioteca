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
           
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscarItem method, of class ItemDAOImpl.
     * @throws java.lang.Exception
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
     * Test of regresarTodo method, of class ItemDAOImpl.
     */
    @Test
    public void testRegresarTodo() {
        List<Item> expResult = items;
        List<Item> result = instance.regresarTodo();
        assertEquals(expResult, result);
    }
}