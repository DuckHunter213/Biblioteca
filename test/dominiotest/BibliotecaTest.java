package dominiotest;

import dataaccess.PrestamoDAOImpl;
import dataaccess.ReservacionDAOImpl;
import dominio.Biblioteca;
import dominio.Item;
import dominio.Prestamo;
import dominio.Reservacion;
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
 * Pruebas para los métodos de la clase biblioteca
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class BibliotecaTest{
    Item item = new Item();
    String identificadorItemErroneo = "identif010";
    String identificadorUsuario = "IDENTIFICADORA5";
    String identificadorItem = "identif005";
    String nombreAutor = "joy Beatty";
    String nombreLibro = "Planning elicitation";
    String categoria = "Libro";
    Prestamo prestamo;
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Reservacion reservacion;
    int resultado;
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
    public void setUp() throws SQLException{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        reservacion = new Reservacion(item);
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario(identificadorUsuario);
    }

    @After
    public void tearDown() throws SQLException{
        PrestamoDAOImpl prestamo = new PrestamoDAOImpl();
        prestamo.quitarPrestamoDeBD(identificadorItem);
        ReservacionDAOImpl reservacion = new ReservacionDAOImpl();
        reservacion.quitarReservacionDeBD(identificadorItem);
    }

    /**
     * Test of buscarItem method, of class Biblioteca.
     *
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
        String idPrestamo = biblioteca.generarPrestamo(identificadorItem);
        int expResult = 1;
        int result = biblioteca.realizarPrestamo(idPrestamo, identificadorUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of realizarReservacion method, of class Biblioteca.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRealizarReservacion() throws Exception{
        String id = biblioteca.generarReservacion(item);
        int expResult = 1;
        int result = biblioteca.realizarReservacion(id);
        assertEquals(expResult, result);
    }

}
