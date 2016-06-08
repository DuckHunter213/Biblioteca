package interfaz;

import dominio.Biblioteca;
import dominio.Item;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Interfaz visual que sirve para seleccionar un ítem. Actualmente, a partir de la selección
 * sólo se puede visualizar la información de manera incompleta y realizar una reservación si es posible.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class SeleccionarItemInterfaz extends javax.swing.JFrame implements ActionListener{
    public static JScrollPane panelItems;
    public static JPanel panelVistaItems;
    public static Biblioteca biblioteca;
    public static List<Item> items;
    public static List<VistaPreviaItem> vistasItems;

    /**
     * Crea una nueva instancia de SeleccionarItemInterfaz
     *
     * @throws java.sql.SQLException Informa al usuario que no hay conexión con la base de datos con una cuadro de diálogo.
     * Aun así, se continúa mostrando lo posible.
     */
    public SeleccionarItemInterfaz() throws SQLException{
        biblioteca = new Biblioteca();
        panelItems = new JScrollPane();
        items = new ArrayList<>();
        vistasItems = new ArrayList<>();
        panelVistaItems = new JPanel();
        panelVistaItems.setLayout(new FlowLayout());
        panelVistaItems.setPreferredSize(new Dimension(800, 800));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setTitle("Selecciona Item");
        setSize(870, 400);
        try{
            mostrarItems();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No hay conexión con la Base de Datos ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        panelItems.getViewport().setView(panelVistaItems);
        panelItems.getViewport().add(panelVistaItems);
        panelItems.setPreferredSize(new Dimension(825, 310));
        this.getContentPane().add(panelItems);
        initComponents();
        this.pack();
        validate();
    }

    private void mostrarItems() throws SQLException{
        items = biblioteca.getItems();
        VistaPreviaItem vistaItem = null;
        for (int i = 0; i < items.size(); i++){
            vistaItem = new VistaPreviaItem(items.get(i), i);
            vistasItems.add(vistaItem);
            vistaItem.elegirItemBoton.addActionListener(this);
            //panelItems.getViewport().setView(vistaItem);
            panelVistaItems.add(vistaItem);
        }
    }

    /**
     * Al hacer clic en uno de los botones relacionados a algún ítem, se generará
     * una nueva pantalla tipo ReservacionInterfaz con el ítem seleccionado.
     * @param e El evento ocurrido
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String numeroDeItem = e.getActionCommand();
        Item itemSeleccionado = null;
        for (int i = 0; i <= vistasItems.size(); i++){
            if (numeroDeItem.equals("ver " + i)){
                itemSeleccionado = items.get(i);
            }
        }
        ReservacionInterfaz reservacion = null;
        try{
            reservacion = new ReservacionInterfaz(itemSeleccionado);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No hay conexión con la Base de Datos ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        reservacion.setVisible(true);
        reservacion.setTitle("Prestar Item");
        dispose();

    }

    /**
     * Controla toda la ubicación de la interfaz gráfica, es generada por Netbeans
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        footer = new javax.swing.JPanel();
        botonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(830, 380));
        setPreferredSize(new java.awt.Dimension(830, 380));
        setResizable(false);
        setSize(new java.awt.Dimension(830, 380));
        getContentPane().setLayout(new java.awt.FlowLayout());

        footer.setBackground(new java.awt.Color(230, 230, 230));
        footer.setPreferredSize(new java.awt.Dimension(800, 30));

        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addComponent(botonRegresar)
                .addGap(0, 723, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addComponent(botonRegresar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(footer);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        PanelPrincipal principal = new PanelPrincipal();
        principal.setVisible(true);
        dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    /**
     * @param args Posibles argumentos que puede recibir
     */
    public static void main(String args[]){
        /*
         * Controla el aspecto visual de cada elemento 
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (ClassNotFoundException ex){
            java.util.logging.Logger.getLogger(SeleccionarItemInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (InstantiationException ex){
            java.util.logging.Logger.getLogger(SeleccionarItemInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IllegalAccessException ex){
            java.util.logging.Logger.getLogger(SeleccionarItemInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(SeleccionarItemInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    new SeleccionarItemInterfaz().setVisible(true);
                }catch (SQLException ex){
                    Logger.getLogger(SeleccionarItemInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar;
    private javax.swing.JPanel footer;
    // End of variables declaration//GEN-END:variables
}
