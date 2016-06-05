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
    public Biblioteca(){
        
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
    public boolean realizarPrestamo(String identificadorItem, String identificadorAlumno) throws SQLException{
        List items = new ArrayList<>();
        try{
            items = buscarItem(identificadorItem);
            Prestamo prestamo =  new Prestamo((Item) items.get(0));
            prestamo.setMatriculaUsuario(identificadorAlumno);
            prestamo.realizarPrestamo();
        }catch(SQLException ex){
            throw new SQLException("Hubo un error con la BD: " + ex.getMessage());            
        }
        return true;
    }
    
}
