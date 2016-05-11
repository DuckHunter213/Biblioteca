/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
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
public class ConexionTest {
    
    public ConexionTest() {
        conexion = new Conexion();
    }
    
    private final Conexion conexion;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void pruebaConexionExitosa() throws SQLException {
        Connection connection = conexion.obtenerConexion();
        assertNotNull(connection);
    }

    @Test
    public void pruebaDesconectaExitoso() throws SQLException {
        Connection connection = conexion.obtenerConexion();
        conexion.desconecta();
        assertTrue(connection.isClosed());
    }
    
}
