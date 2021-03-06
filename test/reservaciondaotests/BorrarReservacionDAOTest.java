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
 * Pruebas para los métodos de reservacionDAOImpl
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class BorrarReservacionDAOTest{
    //<editor-fold defaultstate="collapse" desc="Definicion de variables">
    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    ReservacionDAOImpl instance = new ReservacionDAOImpl();
    int resultado;
    //</editor-fold>

    public BorrarReservacionDAOTest(){
    }

    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp() throws Exception{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        Reservacion reservacion = new Reservacion(item);
        reservacion.generarIdentificador();
        reservacion.setIdentificadorUsuario(identificadorAlumno);
        reservacion.realizarReservacion();
        instance.guardarRegistroReservacion(reservacion);
    }

    @After
    public void tearDown() throws SQLException{
    }

    //</editor-fold>    
    //<editor-fold defaultstate="collapse" desc="Pruebas de unidad">    
    @Test
    public void testquitarItemDeReservacionExitoso() throws SQLException{
        int expResult = 1;
        int result = instance.quitarReservacionDeBD(item.getIdentificador());
        assertEquals(expResult, result);
    }

    @Test
    public void testquitarItemDeReservacionFallido() throws SQLException{
        int expResult = 0;
        instance.quitarReservacionDeBD(item.getIdentificador());
        item.setIdentificador(identificadorItemErroneo);
        int result = instance.quitarReservacionDeBD(item.getIdentificador());
        assertEquals(expResult, result);
    }
    //</editor-fold>

}
