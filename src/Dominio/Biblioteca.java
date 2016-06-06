package Dominio;

import biblioteca.Util;
import dataaccess.BibliotecaDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase tipo biblioteca, este sera el manejador de os items prestamos,
 * devoluciones y validaciones
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */

public class Biblioteca {
    public List<Prestamo> prestamos = null;
    public List<Reservacion> reservaciones = null;
    
    /**
     * Crea los prestamos y las reservaciones en una lista vacia para no meter
     * basura en la base de datos
     */
    public Biblioteca(){
        prestamos = new ArrayList<>();
        reservaciones = new ArrayList<>();
    }
    
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        BibliotecaDAOImpl bibliotecaDAO = new BibliotecaDAOImpl();
        try{
            items = bibliotecaDAO.buscarItem(identificador);
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return items;
    }
    
    public boolean verificarMatricula(String identificadorAlumno) throws SQLException{
        return Util.verificarIdentificadorAlumno(identificadorAlumno);
    }
    
    public boolean verificarItem(String identificadorItem) throws SQLException{
        return Util.verificarIdentificadorItem(identificadorItem);
    }
    
    public String generarPrestamo(String identificadorItem) throws SQLException{
        List items = new ArrayList<>();
        String identificadorPrestamo = "";
        try{
            items = buscarItem(identificadorItem);
            Prestamo prestamo =  new Prestamo((Item) items.get(0));          
            identificadorPrestamo = prestamo.getIdentificadorPrestamo();
            prestamos.add(prestamo);
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return identificadorPrestamo;
    }
    
    public String generarReservacion(Item item) throws SQLException{
        Reservacion reservacion = new Reservacion(item);
        reservacion.setIdentificadorUsuario("identificadora1");
        reservacion.generarIdentificador();
        String identificador = reservacion.getIdentificadorReservacion();
        reservaciones.add(reservacion);
        return identificador;
    }
    
    public String verFechaFinPrestamo(String identificadorPrestamo){
        Prestamo prestamo = null;
        for(int i=0;i<prestamos.size();i++) {
            if (prestamos.get(i).getIdentificadorPrestamo().equals(identificadorPrestamo))
                prestamo  =  prestamos.get(i);
        }
        return prestamo.getFechaCaducidadNormal();
    }
    
    public Date verFechaFinReservacion(String identificador){
        Reservacion reservacion = null;
        for(int i=0;i<reservaciones.size();i++) {
            if (reservaciones.get(i).getIdentificadorReservacion().equals(identificador))
                reservacion  =  reservaciones.get(i);
        }
        return reservacion.getFechaLimite();
    }
    
    public int realizarPrestamo(String identificadorPrestamo, String identificadorAlumno) throws SQLException{
        int estadoPrestamo = 0;
        Prestamo prestamo = null;
        for(int i=0;i<prestamos.size();i++) {
            if (prestamos.get(i).getIdentificadorPrestamo().equals(identificadorPrestamo))
                prestamo  =  prestamos.get(i);
        }
        try{
            boolean estadoMatricula = prestamo.setMatriculaUsuario(identificadorAlumno);
            if (estadoMatricula)
                estadoPrestamo = prestamo.realizarPrestamo();
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return estadoPrestamo;
    }
    
    public int realizarReservacion(String identificador) throws SQLException{
        Reservacion reservacion =  null;
        for(int i=0;i<reservaciones.size();i++) {
            if (reservaciones.get(i).getIdentificadorReservacion().equals(identificador))
                  reservacion =  reservaciones.get(i);
        }
        int estadoReservacion = reservacion.realizarReservacion();
        return estadoReservacion;
    }
    
    public List<Item> getItems()throws SQLException{
        List<Item> items = new ArrayList<>();
        BibliotecaDAOImpl bibliotecaDAO = new BibliotecaDAOImpl();
        try{
            items = bibliotecaDAO.getItems();
        } catch (SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return items;
    }
}
