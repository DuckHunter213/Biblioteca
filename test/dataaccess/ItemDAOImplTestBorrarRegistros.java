/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author gerar
 */
public class ItemDAOImplTestBorrarRegistros {
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
    
    public ItemDAOImplTestBorrarRegistros() {
    }
    
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
        resultado = instance.reservarItem(item, matricula,folioPrestamo);
        resultado = instance.prestarItem(item, matricula,folioPrestamo,folioDevolucion);
    }
    
    @After
    public void tearDown(){
        int result = instance.quitarItemDeReservacion(folioPrestamo);
        result = instance.quitarItemDePrestamo(folioPrestamo);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
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
}
