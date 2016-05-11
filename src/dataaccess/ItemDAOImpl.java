/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import Dominio.Libro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class ItemDAOImpl implements ItemDAO{
    private Conexion conexion;
    private Connection connection;
    private Statement consulta;
    private ResultSet resultados;
    
    public ItemDAOImpl(){
        conexion = new Conexion();
    }
    @Override
    public List<Item> buscarItem(String identificador) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("SELECT titulo, identificador, categoria FROM Items WHERE identificador = ?");            
            sentenciaSQL.setString(1,identificador);
            resultados=sentenciaSQL.executeQuery();
            
            Item item = null;
            while(resultados.next()){
                item = capturarItem(item);
                items.add(item);
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return items;
    }

    @Override
    public boolean actualizarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarItem(Item item) {
        boolean resultadoDeAgregacion = false;
        try{
            connection = conexion.obtenerConexion();
            PreparedStatement sentenciaSQL  = connection.prepareStatement("INSERT INTO biblioteca.Items VALUES ('100', '2016-05-03 00:00:00','10', 'Carlos fuentes','Libro','100 años de soledad','7890132456','S14011606','2016-05-03 00:00:00','6','2016-05-01 00:00:00')");
            
            ResultSet nada = sentenciaSQL.executeQuery();
            resultadoDeAgregacion =true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.desconecta();
        }
        return resultadoDeAgregacion;
    }

    @Override
    public List<ItemDAO> regresarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Item capturarItem(Item item) throws SQLException{
        if (resultados != null){
            String categoria = resultados.getString("categoria");
            if (categoria.equals("Libro"))
                item = new Libro();
            else
                item = new Libro();
            item.setIdentificador(resultados.getString("identificador"));
            item.setTitulo(resultados.getString("Titulo"));            
        }
        return item;
    }
    
}
