package conexiontests;

import dataaccess.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de conexion a la base de datos
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 20/05/2016
 */
public class ConexionTest{
    private final Conexion conexion;

    public ConexionTest(){
        conexion = new Conexion();
    }

    //<editor-fold defaultstate="collapse" desc="Opciones de la prueba">
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

    //</editor-fold>
    //<editor-fold defaultstate="collapse" desc="Pruebas">

    @Test
    public void pruebaConexionExitosa() throws SQLException{
        Connection connection = conexion.obtenerConexion();
        assertNotNull(connection);
    }

    @Test
    public void pruebaDesconectaExitoso() throws SQLException{
        Connection connection = conexion.obtenerConexion();
        conexion.desconecta();
        assertTrue(connection.isClosed());
    }
    //</editor-fold>

}
