/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaciondaotests;

import dataaccess.ReservacionDAOImpl;
import dominio.Item;
import dominio.Reservacion;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gerar
 */
public class ReservacionesErroresSQL{
    Item item = new Item();
    String identificadorAlumno = "IDENTIFICADORA5";
    String identificadorItemErroneo = "identif010";
    String identificadorItem = "identif005";
    public static final int COSTO_MULTA = 10;
    public static final int TIEMPO_PRESTAMO = 10;
    Reservacion reservacion;
    ReservacionDAOImpl instance = new ReservacionDAOImpl();
    int resultado;
    
    public ReservacionesErroresSQL(){
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

    @Test (expected = SQLException.class)
    public void testInsertarErrorSQL() throws SQLException{
        item.setIdentificador(identificadorItem);
        item.setCostoMulta(COSTO_MULTA);
        item.setTiempoPrestamo(TIEMPO_PRESTAMO);
        reservacion = new Reservacion(item);
        reservacion.generarIdentificador();
        reservacion.setIdentificadorUsuario(identificadorAlumno);
        reservacion.realizarReservacion();
    }
}
