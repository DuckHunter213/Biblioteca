package interfaz;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Interfaz visual que sirve de menú principal para usar el sistema.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class PanelPrincipal extends javax.swing.JFrame{

    /**
     * Creaun nuevo pánel principal
     */
    public PanelPrincipal(){
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Principal");
    }

    /**
     * La posición de los elementos visuales es establecida aquí y generada dinámicamente por netbeans.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloSeccionCampo = new javax.swing.JLabel();
        botonSeccionReservar = new javax.swing.JButton();
        botonSeccionPrestar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloSeccionCampo.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        tituloSeccionCampo.setText("Biblioteca \"The happy book\"");

        botonSeccionReservar.setText("Reservar Item");
        botonSeccionReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeccionReservarActionPerformed(evt);
            }
        });

        botonSeccionPrestar.setText("Prestar Item");
        botonSeccionPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeccionPrestarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(tituloSeccionCampo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonSeccionReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonSeccionPrestar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tituloSeccionCampo)
                .addGap(33, 33, 33)
                .addComponent(botonSeccionReservar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSeccionPrestar)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSeccionReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeccionReservarActionPerformed
        SeleccionarItemInterfaz reservacion = null;
        try{
            reservacion = new SeleccionarItemInterfaz();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Lo lamentamos, la opción no está disponible por ahora", "Ocurrió un problema", JOptionPane.WARNING_MESSAGE);
        }
        reservacion.setVisible(true);
        reservacion.setTitle("Seleccione un item");
        dispose();
    }//GEN-LAST:event_botonSeccionReservarActionPerformed

    private void botonSeccionPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeccionPrestarActionPerformed
        PrestamoInterfaz prestamo = new PrestamoInterfaz();
        prestamo.setVisible(true);
        prestamo.setTitle("Prestar Item");
        dispose();
    }//GEN-LAST:event_botonSeccionPrestarActionPerformed

    /**
     * @param args Lista de argumentos que podría recibir la interfaz
     */
    public static void main(String args[]){
        /*
         * Establece el Look and feel (aspecto visual) de la interfaz, es generado por Netbeans
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
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (InstantiationException ex){
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IllegalAccessException ex){
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(PanelPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new PanelPrincipal().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSeccionPrestar;
    private javax.swing.JButton botonSeccionReservar;
    private javax.swing.JLabel tituloSeccionCampo;
    // End of variables declaration//GEN-END:variables
}
