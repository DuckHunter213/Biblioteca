package interfaz;

import dominio.Item;

/**
 * Interfaz visual que sirve para mostrar la interfaz de un ítem.
 * se eligió separar del resto del código pues propociona la capacidad de generarlas 
 * dinamicamente.
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class VistaPreviaItem extends javax.swing.JPanel {
    private static int identificador;
    
    public int getIdentificador(){
        return identificador;
    }
    /**
     * Crea una nueva instancia de VistaPreviaItem
     * @param item El ítem válido del cual obtendrá la información.
     * @param identificador un identificador del objeto para que la clase
     * empleadora puede manipularlos.
     */
    public VistaPreviaItem(Item item, int identificador) {
        initComponents();
        setVisible(true);
        this.identificador = identificador;
        this.setVisible(true);
        //se agrega un identificador al botón para poder usar el actionEvent y distinguir de quien se trata
        this.autorCampo.setText("Autor: " + item.getAutor());
        this.tipoItemCampo.setText("Tipo: " + item.getCategoria());
        this.nombreItemCampo.setText("Título: " + item.getTitulo());
        this.elegirItemBoton.setText("ver "+identificador);
    }

    /**
     * Controla la posición de los elementos visuales
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        footer = new javax.swing.JPanel();
        elegirItemBoton = new javax.swing.JButton();
        datosItemPanel = new javax.swing.JPanel();
        autorCampo = new javax.swing.JLabel();
        tipoItemCampo = new javax.swing.JLabel();
        nombreItemCampo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(198, 222, 245));

        footer.setBackground(new java.awt.Color(176, 197, 207));

        elegirItemBoton.setText("ver a detalle");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(elegirItemBoton))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addComponent(elegirItemBoton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        datosItemPanel.setBackground(new java.awt.Color(230, 230, 230));

        autorCampo.setText("Autor: ");

        tipoItemCampo.setText("Tipo:");

        nombreItemCampo.setText("Título:");

        javax.swing.GroupLayout datosItemPanelLayout = new javax.swing.GroupLayout(datosItemPanel);
        datosItemPanel.setLayout(datosItemPanelLayout);
        datosItemPanelLayout.setHorizontalGroup(
            datosItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(autorCampo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(tipoItemCampo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreItemCampo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        datosItemPanelLayout.setVerticalGroup(
            datosItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(autorCampo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tipoItemCampo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreItemCampo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(datosItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datosItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autorCampo;
    private javax.swing.JPanel datosItemPanel;
    public javax.swing.JButton elegirItemBoton;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nombreItemCampo;
    private javax.swing.JLabel tipoItemCampo;
    // End of variables declaration//GEN-END:variables
}
