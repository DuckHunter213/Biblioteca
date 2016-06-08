package dominiotest;

import dominio.Item;
import dominio.Prestamo;
import dataaccess.PrestamoDAO;
import dataaccess.PrestamoDAOImpl;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrestamoTest{
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles ">
    Item item = new Item();
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorItem = "identif005";
    String identificadorItemErroneo = "identif105";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Prestamo prestamo;
    int resultado;

    public PrestamoTest(){
    }

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
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario(identificadorUsuario);
    }

    @After
    public void tearDown() throws SQLException{
        PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
        prestamoDAO.quitarPrestamoDeBD(identificadorItem);
    }

    @Test
    public void testSetItemExitoso(){
        boolean expResult = true;
        boolean result = prestamo.setItem(item);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testSetItemFallido() throws SQLException{
        item.setIdentificador(identificadorItemErroneo);
        prestamo.setItem(item);
        boolean expResult = true;
        boolean result = prestamo.setItem(item);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testSetIdentificadorUsuarioExitoso() throws SQLException{
        boolean expResult = true;
        boolean result = prestamo.setIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdentificadorUsuarioFallidoNulo() throws SQLException{
        boolean expResult = false;
        boolean result = prestamo.setIdentificadorUsuario("");
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdentificadorUsuarioFallidoFalso() throws SQLException{
        boolean expResult = false;
        boolean result = prestamo.setIdentificadorUsuario("IDENTIFICADORA16");
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMatriculaUsuario(){
        String expResult = "IDENTIFICADORA5";
        String result = prestamo.getIdentificadorUsuario();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIdentificadorItem(){
        String expResult = "identif005";
        String result = prestamo.getIdentificadorItem();
        assertEquals(expResult, result);
    }

    @Test
    public void testRealizarPrestamoExitoso() throws SQLException{
        int expResult = 1;
        int result = prestamo.realizarPrestamo();
        assertEquals(expResult, result);
    }

    @Test
    public void testRealizarPrestamoFallidoIdItem() throws Exception{
        item = new Item();
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        item.setIdentificador(identificadorItemErroneo);
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario(identificadorUsuario);
        int expResult = 0;
        int result = prestamo.realizarPrestamo();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRealizarPrestamoFallidoIdUsuario() throws Exception{
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario("identificadora9");
        int expResult = 0;
        int result = prestamo.realizarPrestamo();
        assertEquals(expResult, result);
    }

}
