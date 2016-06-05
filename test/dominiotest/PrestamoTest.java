/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiotest;

import Dominio.Item;
import Dominio.Libro;
import Dominio.Prestamo;
import dataaccess.PrestamoDAOImpl;
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
public class PrestamoTest {
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles ">
    Item item = new Libro();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO= 10;
    Prestamo prestamo;
    PrestamoDAOImpl instance = new PrestamoDAOImpl();
    int resultado;
    
    public PrestamoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        prestamo = new Prestamo(item);
        prestamo.setMatriculaUsuario(identificadorAlumno);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setMatriculaUsuario method, of class Prestamo.
     */
    @Test
    public void testSetMatriculaUsuarioExitoso() {
        boolean expResult = true;
        boolean result = prestamo.setMatriculaUsuario(identificadorAlumno);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setMatriculaUsuario method, of class Prestamo.
     */
    @Test
    public void testSetMatriculaUsuarioFallido() {
        boolean expResult = false;
        boolean result = prestamo.setMatriculaUsuario("");
        assertEquals(expResult, result);
    }

    /**
     * Test of getMatriculaUsuario method, of class Prestamo.
     */
    @Test
    public void testGetMatriculaUsuario() {
        String expResult = "IDENTIFICADORA5";
        String result = prestamo.getMatriculaUsuario();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdentificadorItem method, of class Prestamo.
     */
    @Test
    public void testGetIdentificadorItem() {
        String expResult = "identif005";
        String result = prestamo.getIdentificadorItem();
        assertEquals(expResult, result);
    }
    
}
