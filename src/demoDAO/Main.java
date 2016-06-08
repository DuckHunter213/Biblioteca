package demoDAO;

import interfaz.PanelPrincipal;

/**
 * Clase de menu principal encargada de conectar a la capa de diseño con el
 * usuario
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 * @since 06/06/2016
 */
public class Main{

    public static void main(String[] args){
        PanelPrincipal principal = new PanelPrincipal();
        principal.setVisible(true);
    }

}
