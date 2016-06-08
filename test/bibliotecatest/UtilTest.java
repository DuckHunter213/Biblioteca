/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecatest;

import biblioteca.Util;
import dominio.Item;
import dominio.Prestamo;
import static dominiotest.ItemTest.COSTO_MULTA;
import static dominiotest.ItemTest.TIEMPO_PRESTAMO;
import java.sql.SQLException;
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
public class UtilTest{
    Item item =  new Item();
    String identificadorItemErroneo = "identif010";
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorUsuarioErroneo = "IDENTIFICADORA15";
    String identificadorItem = "identif005";
    String nombreAutor = "Wiegers";
    String nombreLibro = "Teardrops";
    String categoria = "Libro";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    
    public UtilTest(){
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    @Before
    public void setUp() throws SQLException{
        item.setAutor(nombreAutor);
        item.setCategoria(categoria);
        item.setTitulo(nombreLibro);
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setFechaAdquisicion(2013, 00, 13);
        item.setFechaPublicación(2010, 8, 15);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
    }
    
    @After
    public void tearDown(){
    }

    /**
     * Test of itemEstadoDisponibilidad method, of class Util.
     */
    @Test
    public void testItemEstadoDisponibilidadExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.itemEstadoDisponibilidad(identificadorItem);
        assertEquals(expResult, result);
    }

    /**
     * Test of revisarLimitePrestamos method, of class Util.
     */
    @Test
    public void testRevisarLimitePrestamosExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.revisarLimitePrestamos(identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorUsuario method, of class Util.
     */
    @Test
    public void testVerificarIdentificadorUsuarioExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.verificarIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerificarIdentificadorUsuarioFallido() throws Exception{
        boolean expResult = false;
        boolean result = Util.verificarIdentificadorUsuario(identificadorUsuarioErroneo);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarIdentificadorItem method, of class Util.
     */
    @Test
    public void testVerificarIdentificadorItemExitoso() throws Exception{
        boolean expResult = true;
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerificarIdentificadorItemFallido() throws Exception{
        boolean expResult = true;
        item.setIdentificador(identificadorItemErroneo);
        boolean result = Util.verificarIdentificadorItem(item.getIdentificador());
        assertEquals(expResult, result);
    }
    
}
