package dominiotest;

import dataaccess.ReservacionDAO;
import dataaccess.ReservacionDAOImpl;
import dominio.Item;
import dominio.Reservacion;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas para los métodos del módulo reservación
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class ReservacionTest{
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles ">
    Item item = new Item();
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorItem = "identif005";
    String identificadorItemErroneo = "identif105";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Reservacion reservacion;
    int resultado;

    public ReservacionTest(){
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
        reservacion = new Reservacion(item);
        reservacion.generarIdentificador();
        reservacion.setIdentificadorUsuario(identificadorUsuario);
    }

    @After
    public void tearDown() throws SQLException{
        ReservacionDAO prestamoDAO = new ReservacionDAOImpl();
        prestamoDAO.quitarReservacionDeBD(identificadorItem);
    }

    @Test
    public void testSetItemExitoso(){
        boolean expResult = true;
        boolean result = reservacion.setItem(item);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetItemFallido() throws SQLException{
        item.setIdentificador(identificadorItemErroneo);
        reservacion.setItem(item);
        boolean expResult = true;
        boolean result = reservacion.setItem(item);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdentificadorUsuarioExitoso() throws SQLException{
        boolean expResult = true;
        boolean result = reservacion.setIdentificadorUsuario(identificadorUsuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdentificadorUsuarioFallidoNulo() throws SQLException{
        boolean expResult = false;
        boolean result = reservacion.setIdentificadorUsuario("");
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdentificadorUsuarioFallidoFalse() throws SQLException{
        boolean expResult = false;
        boolean result = reservacion.setIdentificadorUsuario("identificadora9");
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIdentificadorUsuario(){
        String expResult = "IDENTIFICADORA5";
        String result = reservacion.getIdentificadorUsuario();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIdentificadorItem(){
        String expResult = "identif005";
        String result = reservacion.getIdentificadorItem();
        assertEquals(expResult, result);
    }

    @Test
    public void testRealizarReservacionExitosa() throws Exception{
        int expResult = 1;
        int result = reservacion.realizarReservacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testRealizarPrestamoFallidoIdItem() throws Exception{
        item = new Item();
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        item.setIdentificador(identificadorItemErroneo);
        reservacion = new Reservacion(item);
        reservacion.setIdentificadorUsuario(identificadorUsuario);
        reservacion.generarIdentificador();
        int expResult = 0;
        int result = reservacion.realizarReservacion();
        assertEquals(expResult, result);
    }

    @Test
    public void testRealizarPrestamoFallidoIdUsuario() throws Exception{
        reservacion = new Reservacion(item);
        reservacion.setItem(item);
        reservacion.setIdentificadorUsuario("identificadora9");
        reservacion.generarIdentificador();
        int expResult = 0;
        int result = reservacion.realizarReservacion();
        assertEquals(expResult, result);
    }

}
