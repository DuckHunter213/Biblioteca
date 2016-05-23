/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import java.sql.SQLException;

/**
 *
 * @author gerar
 */
public interface prestamoDAO {
    
    /**
     *
     * @param identificador
     * @return
     * @throws java.sql.SQLException
     */
    public int quitarItemDePrestamo(String identificador) throws SQLException;

    /**
     * Funcion que enlaza un item a un usuario y se guarda un registro en la 
     * base de datos
     * @param item item a prestar al usuario
     * @param identificadorUsuario
     * @return regresa un valor de retroalimentacion //TODO valor booleano
     * @throws java.sql.SQLException
     */
    public int prestarItem(Item item, String identificadorUsuario) throws SQLException;
}
