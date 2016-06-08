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
 * Pruebas de unidad cuando falla la conexión a la base de datos
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class ConexionTestFallida{
    private final Conexion conexion;

    public ConexionTestFallida(){
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
    @Test(expected = SQLException.class)
    public void pruebaConexionExitosa() throws SQLException{
        Connection connection = conexion.obtenerConexion();
        assertNotNull(connection);
    }

    @Test(expected = SQLException.class)
    public void pruebaDesconectaExitoso() throws SQLException{
        Connection connection = conexion.obtenerConexion();
        conexion.desconecta();
        assertTrue(connection.isClosed());
    }
    //</editor-fold>

}
