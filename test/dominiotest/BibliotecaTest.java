/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiotest;

import dominio.Biblioteca;
import dominio.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class BibliotecaTest{
    Item item =  new Item();
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    String nombreAutor = "joy Beatty";
    String nombreLibro = "Planning elicitation";
    String categoria = "Libro";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Biblioteca biblioteca = new Biblioteca();
    
    public BibliotecaTest() throws SQLException{
        item.setAutor(nombreAutor);
        item.setCategoria(categoria);
        item.setTitulo(nombreLibro);
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setFechaAdquisicion(2013, 00, 13);
        item.setFechaPublicación(2010, 8, 15);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    @Before
    public void setUp(){
    }
    
    @After
    public void tearDown(){
    }

    /**
     * Test of buscarItem method, of class Biblioteca.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarItemExitoso() throws Exception{
        List<Item> items = new ArrayList<>();
        items.add(item);
        List<Item> expResult = items;
        List<Item> result = biblioteca.buscarItem(identificadorItem);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscarItemFallido() throws Exception{
        item.setIdentificador(identificadorItemErroneo);
        List<Item> items = new ArrayList<>();
        items.add(item);
        List<Item> expResult = items;
        List<Item> result = biblioteca.buscarItem(identificadorItem);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorUsuario method, of class Biblioteca.
     */
    @Test
    public void testVerificarIdentificadorUsuarioFallido() throws Exception{
        String identificadorAlumno = "";
        boolean expResult = false;
        boolean result = biblioteca.verificarIdentificadorUsuario(identificadorAlumno);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerificarIdentificadorUsuarioExitoso() throws Exception{
        String identificadorAlumno = "IDENTIFICADORA1";
        boolean expResult = true;
        boolean result = biblioteca.verificarIdentificadorUsuario(identificadorAlumno);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarItem method, of class Biblioteca.
     */
    @Test
    public void testVerificarItemExitoso() throws Exception{
        boolean expResult = true;
        boolean result = biblioteca.verificarItem(identificadorItem);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerificarItemFallido() throws Exception{
        boolean expResult = false;
        boolean result = biblioteca.verificarItem(identificadorItemErroneo);
        assertEquals(expResult, result);
    }

    /**
     * Test of realizarPrestamo method, of class Biblioteca.
     */
    @Test
    public void testRealizarPrestamo() throws Exception{
        System.out.println("realizarPrestamo");
        String identificadorPrestamo = "";
        String identificadorAlumno = "";
        Biblioteca instance = new Biblioteca();
        int expResult = 0;
        int result = instance.realizarPrestamo(identificadorPrestamo, identificadorAlumno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of realizarReservacion method, of class Biblioteca.
     */
    @Test
    public void testRealizarReservacion() throws Exception{
        System.out.println("realizarReservacion");
        String identificador = "";
        Biblioteca instance = new Biblioteca();
        int expResult = 0;
        int result = instance.realizarReservacion(identificador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class Biblioteca.
     */
    @Test
    public void testGetItems() throws Exception{
        System.out.println("getItems");
        Biblioteca instance = new Biblioteca();
        List<Item> expResult = null;
        List<Item> result = instance.getItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
