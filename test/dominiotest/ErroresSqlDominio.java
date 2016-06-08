/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiotest;

import biblioteca.Util;
import dataaccess.PrestamoDAOImpl;
import dominio.Biblioteca;
import dominio.Item;
import dominio.Prestamo;
import dominio.Reservacion;
import static interfaz.PrestamoInterfaz.biblioteca;
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
public class ErroresSqlDominio{
    Item item =  new Item();
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorUsuarioErroneo = "IDENTIFICADORA15";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    String nombreAutor = "Wiegers";
    String nombreLibro = "Teardrops";
    String categoria = "Libro";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Prestamo prestamo;
    Reservacion reservacion;
    Biblioteca biblioteca = new Biblioteca();
    int resultado;
    
    public ErroresSqlDominio(){
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    @Before
    public void setUp() throws SQLException{
        prestamo = new Prestamo(item);
        reservacion = new Reservacion(item);
    }
    
    @After
    public void tearDown(){
    }

    
    @Test
    public void testRealizarPrestamo() throws SQLException{
        int expResult = 0;
        int result = prestamo.realizarPrestamo();
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testSetIdentificadorUsuarioPrestamo() throws SQLException{
        boolean expResult = false;
        boolean result = prestamo.setIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetItemPrestamo(){
        boolean expResult = false;
        boolean result = prestamo.setItem(item);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testSetItemReserva(){
        boolean expResult = false;
        boolean result = reservacion.setItem(item);
        assertEquals(expResult, result);        
    }
    
    @Test (expected = SQLException.class)
    public void testSetIdentificadorUsuarioReserva() throws SQLException{
        boolean expResult = false;
        boolean result = reservacion.setIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRealizarReservacion() throws Exception{
        int expResult = 0;
        int result = reservacion.realizarReservacion();
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testBuscarItemExitoso() throws Exception{
        List<Item> items = new ArrayList<>();
        items.add(item);
        List<Item> expResult = items;
        List<Item> result = biblioteca.buscarItem(identificadorItem);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerificarIdentificadorUsuarioFallido() throws Exception{
        String identificadorAlumno = "";
        boolean expResult = false;
        boolean result = biblioteca.verificarIdentificadorUsuario(identificadorAlumno);
        assertEquals(expResult, result);
    }
   
    
    
    
    @Test (expected = SQLException.class)
    public void testItemEstadoDisponibilidadExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.itemEstadoDisponibilidad(identificadorItem);
        assertEquals(expResult, result);
    }

    /**
     * Test of revisarLimitePrestamos method, of class Util.
     */
    @Test (expected = SQLException.class)
    public void testRevisarLimitePrestamosExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.revisarLimitePrestamos(identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorUsuario method, of class Util.
     */
    @Test (expected = SQLException.class)
    public void testVerificarIdentificadorUsuarioExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.verificarIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorItem method, of class Util.
     */
    @Test
    public void testVerificarIdentificadorItemExitoso() throws Exception{
        boolean expResult = false;
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test (expected = SQLException.class)
    public void testVerificarIdentificadorItemFallido() throws Exception{
        boolean expResult = true;
        item.setIdentificador(identificadorItemErroneo);
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    

}
