package reservaciondaotests;

import dominio.Item;
import dominio.Reservacion;
import dataaccess.ReservacionDAOImpl;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Nombre del programa: Biblioteca
 * Nombres: @author Luis Fernando Gomez Alejandre
 *
 * @author Francisco Gerardo Mares Solano
 * Fecha: @since 20/05/2016
 * Descripción: Contiene pruebas para registrar registros de la base de datos,
 * todas las pruebas son referentes a los items (ItemDAOImpl).
 */
public class InsertarReservacionDAOTest{
    //<editor-fold defaultstate="collapse" desc="Declaración de varaibles">
    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Reservacion reservacion;
    ReservacionDAOImpl instance = new ReservacionDAOImpl();
    int resultado;
    //</editor-fold>

    public InsertarReservacionDAOTest(){
    }

    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
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
        reservacion.setIdentificadorUsuario(identificadorAlumno);
    }

    @After
    public void tearDown() throws SQLException{
        instance.quitarReservacionDeBD(identificadorItem);
    }

    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas">    

    @Test
    public void testReservarItemExitoso() throws Exception{
        int expResult = 1;
        int result = instance.guardarRegistroReservacion(reservacion);
        assertEquals(expResult, result);
    }

    @Test
    public void testReservarItemFallidoId() throws Exception{
        item = new Item();
        item.setIdentificador(identificadorItemErroneo);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        int expResult = 0;
        reservacion.setItem(item);
        int result = instance.guardarRegistroReservacion(reservacion);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testReservarItemFallidoRepetido() throws SQLException{
        int expResult = 0;
        instance.guardarRegistroReservacion(reservacion);
        int result = instance.guardarRegistroReservacion(reservacion);
        assertEquals(expResult, result);
    }
    //</editor-fold>

}
