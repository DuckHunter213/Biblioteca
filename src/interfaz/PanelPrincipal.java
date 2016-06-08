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

        tituloBibliotecaCampo = new javax.swing.JLabel();
        sistemaCampo = new javax.swing.JLabel();
        panelContenedorOpciones = new javax.swing.JPanel();
        botonSeccionReservar = new javax.swing.JButton();
        botonSeccionPrestar = new javax.swing.JButton();
        panelAyuda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        tituloBibliotecaCampo.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        tituloBibliotecaCampo.setText("Universidad Veracruzana");

        sistemaCampo.setText("Sistema Bibliotecario");

        panelContenedorOpciones.setBackground(new java.awt.Color(255, 255, 255));
        panelContenedorOpciones.setForeground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout panelContenedorOpcionesLayout = new javax.swing.GroupLayout(panelContenedorOpciones);
        panelContenedorOpciones.setLayout(panelContenedorOpcionesLayout);
        panelContenedorOpcionesLayout.setHorizontalGroup(
            panelContenedorOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonSeccionReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonSeccionPrestar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        panelContenedorOpcionesLayout.setVerticalGroup(
            panelContenedorOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContenedorOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSeccionReservar)
                    .addComponent(botonSeccionPrestar))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        panelAyuda.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelAyudaLayout = new javax.swing.GroupLayout(panelAyuda);
        panelAyuda.setLayout(panelAyudaLayout);
        panelAyudaLayout.setHorizontalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAyudaLayout.setVerticalGroup(
            panelAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblioteca/Logo120x120.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tituloBibliotecaCampo)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(sistemaCampo)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelContenedorOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloBibliotecaCampo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sistemaCampo)
                        .addGap(18, 18, 18)
                        .addComponent(panelContenedorOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(panelAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelAyuda;
    private javax.swing.JPanel panelContenedorOpciones;
    private javax.swing.JLabel sistemaCampo;
    private javax.swing.JLabel tituloBibliotecaCampo;
    // End of variables declaration//GEN-END:variables
}
