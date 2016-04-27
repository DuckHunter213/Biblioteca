/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Dominio.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public List<Item> buscarItem(String codigoBarras, ArrayList listaItems, String tipoItem, String filtroBusqueda) throws SQLException{
        List<Item> items = new ArrayList<>();
        try{
            connection = conexion.obtenerConexion();
            consulta = connection.createStatement();
            resultados = consulta.executeQuery("select * from tabla where nombre like ? ");
            while(resultados.next()){
                ItemDAO item = new ItemDAOImpl();
                item.setCodigoBarras(resultados.getCodigoBarras());
                items.add((Item) item);
            }
        }
        catch (SQLException ex){
            //TODO logger.getLogger;
        }
        return null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemDAO> regresarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
