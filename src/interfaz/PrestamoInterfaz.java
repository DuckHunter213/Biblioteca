package interfaz;

import dominio.Biblioteca;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Interfaz visual que sirve para agregar préstamos a la base de datos
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class PrestamoInterfaz extends javax.swing.JFrame{
    public static String identificadorPrestamo = "";
    public static Biblioteca biblioteca;
    public static final int MATRICULA_INVALIDA = 3;
    public static final int ITEM_INVALIDO = 2;
    public static final int PRESTAMO_EXITOSO = 1;
    public static final int ITEM_NO_DISPONIBLE = 0;
    public static final int PRESTAMO_FUERA_DE_LIMITE = -1;
    public static final int ACCION_CANCELADA = -2;

    /**
     * Crea una nueva instancia de PrestamoInterfaz
     */
    public PrestamoInterfaz(){
        biblioteca = new Biblioteca();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Prestamo");
    }

    /**
     * Éste método es llamado desde el constructor y se genera automaticamente 
     * por Netbeans, contiene el posicionamiento e inicialización de los 
     * atributos generadors por el IDE.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        matriculaAlumnoCampo = new javax.swing.JLabel();
        identificadorItemCampo = new javax.swing.JLabel();
        matriculaAlumnoTexto = new javax.swing.JTextField();
        identificadorItemTexto = new javax.swing.JTextField();
        footerPanel = new javax.swing.JPanel();
        botonRegresar = new javax.swing.JButton();
        botonAceptarPrestamo = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        matriculaAlumnoCampo.setText("Identificador usuario");

        identificadorItemCampo.setText("Identificador del Item");

        matriculaAlumnoTexto.setText("identificador del usuario");
        matriculaAlumnoTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                matriculaAlumnoTextoFocusGained(evt);
            }
        });

        identificadorItemTexto.setText("identificador del ítem");
        identificadorItemTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                identificadorItemTextoFocusGained(evt);
            }
        });

        footerPanel.setBackground(new java.awt.Color(230, 230, 230));

        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        botonAceptarPrestamo.setText("Aceptar");
        botonAceptarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addComponent(botonRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptarPrestamo))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botonRegresar)
                .addComponent(botonAceptarPrestamo))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(footerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(identificadorItemCampo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(matriculaAlumnoCampo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(identificadorItemTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matriculaAlumnoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matriculaAlumnoCampo)
                    .addComponent(matriculaAlumnoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identificadorItemCampo)
                    .addComponent(identificadorItemTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(footerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        PanelPrincipal principal = new PanelPrincipal();
        principal.setVisible(true);
        dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonAceptarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarPrestamoActionPerformed
        try{
            String identificadorItem = identificadorItemTexto.getText();
            String identificadorAlumno = matriculaAlumnoTexto.getText();
            int estadoPrestamo = 0;
            estadoPrestamo = realizarPrestamo(identificadorItem, identificadorAlumno);
            switch (estadoPrestamo){
                case MATRICULA_INVALIDA:
                    JOptionPane.showMessageDialog(null, "La matricula introducide es inválida o no existe ese usuario", "Datos erroneos", JOptionPane.WARNING_MESSAGE);
                    break;
                case ITEM_INVALIDO:
                    JOptionPane.showMessageDialog(null, "El identificador del item es inválido o no existe", "Datos erroneos", JOptionPane.WARNING_MESSAGE);
                    break;
                case PRESTAMO_EXITOSO:
                    JOptionPane.showMessageDialog(null, "El préstamo se ha realizado con éxito.\n Caduca en la fecha " + biblioteca.verFechaFinPrestamo(identificadorPrestamo), "Préstamo correcto", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case ITEM_NO_DISPONIBLE:
                    JOptionPane.showMessageDialog(null, "El item no está disponible", "Ocurrió un problema", JOptionPane.WARNING_MESSAGE);
                    break;
                case PRESTAMO_FUERA_DE_LIMITE:
                    JOptionPane.showMessageDialog(null, "El usuario superó su límite de préstamos", "Ocurrió un problema", JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    break;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Lo lamentamos, no podemos conectar con la Base de Datos", "Ocurrió un problema", JOptionPane.WARNING_MESSAGE);
        }catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Lo lamentamos, el item no está registrado", "Ocurrió un problema", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botonAceptarPrestamoActionPerformed

    private void matriculaAlumnoTextoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_matriculaAlumnoTextoFocusGained
        matriculaAlumnoTexto.setText("");
    }//GEN-LAST:event_matriculaAlumnoTextoFocusGained

    private void identificadorItemTextoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_identificadorItemTextoFocusGained
        identificadorItemTexto.setText("");
    }//GEN-LAST:event_identificadorItemTextoFocusGained

    private static int realizarPrestamo(String identificadorItem, String identificadorUsuario) throws SQLException{
        int estadoPrestamo = ACCION_CANCELADA;
        if (biblioteca.verificarIdentificadorUsuario(identificadorUsuario)){
            if (biblioteca.verificarItem(identificadorItem)){
                int confirmacionPrestamo = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
                if (confirmacionPrestamo == 0){
                    try{
                        identificadorPrestamo = biblioteca.generarPrestamo(identificadorItem);
                        estadoPrestamo = biblioteca.realizarPrestamo(identificadorPrestamo, identificadorUsuario);
                    }catch (SQLException ex){
                        throw new SQLException("Hubo un error con la BD: " + ex.getMessage());
                    }
                }else{
                    estadoPrestamo = ACCION_CANCELADA;
                }
            }else{
                estadoPrestamo = ITEM_INVALIDO;
            }
        }else{
            estadoPrestamo = MATRICULA_INVALIDA;
        }
        return estadoPrestamo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /*
         * Configura el aspecto visual de la interfaz
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
            java.util.logging.Logger.getLogger(PrestamoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (InstantiationException ex){
            java.util.logging.Logger.getLogger(PrestamoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IllegalAccessException ex){
            java.util.logging.Logger.getLogger(PrestamoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(PrestamoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(() -> {
            new PrestamoInterfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptarPrestamo;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JLabel identificadorItemCampo;
    private javax.swing.JTextField identificadorItemTexto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel matriculaAlumnoCampo;
    private javax.swing.JTextField matriculaAlumnoTexto;
    // End of variables declaration//GEN-END:variables
}
