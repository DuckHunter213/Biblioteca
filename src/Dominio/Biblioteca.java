/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import biblioteca.Util;
import dataaccess.BibliotecaDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerar
 */
public class Biblioteca {
    public List<Prestamo> prestamos = null;
    
    public Biblioteca(){
        prestamos = new ArrayList<>();
    }
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        BibliotecaDAOImpl bibliotecaDAO = new BibliotecaDAOImpl();
        try{
            items = bibliotecaDAO.buscarItems(identificador);
        } catch (SQLException ex) {
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
        }
        return items;
    }
    
    public boolean verificarMatricula(String identificadorAlumno){
        return Util.verificarIdentificadorAlumno(identificadorAlumno);
    }
    public boolean verificarItem(String identificadorItem){
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
    public String verFechaFinPrestamo(String identificadorPrestamo){
        Prestamo prestamo = null;
        for(int i=0;i<prestamos.size();i++) {
            if (prestamos.get(i).getIdentificadorPrestamo().equals(identificadorPrestamo))
                prestamo  =  prestamos.get(i);
        }
        return prestamo.getFechaCaducidadNormal();
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
