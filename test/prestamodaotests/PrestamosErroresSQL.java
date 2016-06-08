package prestamodaotests;

import dataaccess.PrestamoDAOImpl;
import dominio.Item;
import dominio.Prestamo;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Pruebas para los métodos fallidos del módulo préstamo
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 07/06/2016
 */
public class PrestamosErroresSQL{
    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Prestamo prestamo;
    PrestamoDAOImpl instance = new PrestamoDAOImpl();
    int resultado;

    public PrestamosErroresSQL(){
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

    @Test(expected = SQLException.class)
    public void testPrestamoErrorSQL() throws SQLException{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        prestamo = new Prestamo(item);
        prestamo.setIdentificadorUsuario(identificadorAlumno);
        prestamo.realizarPrestamo();
    }

}
