
package Dominio;

import java.text.NumberFormat;
import sun.security.util.Password;


/**
 *
 * @author Luis Fernando Gomez Alejandre
 * @author Francisco Gerardo Mares Solano
 */
public class Usuario {
    private Password contrasenia;
    private String domicilio;
    private String matricula;
    private String nombre;
    private String numeroTelefonico;
    private String tipoDeUsuario;
    private int numeroDePrestamosActivos;
    private NumberFormat multaAcumulada;
    

    /**
     * @return the contrasenia
     */
    public Password getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(Password contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the numeroTelefonico
     */
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * @param numeroTelefonico the numeroTelefonico to set
     */
    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    /**
     * @return the tipoDeUsuario
     */
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    /**
     * @param tipoDeUsuario the tipoDeUsuario to set
     */
    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    /**
     * @return the numeroDePrestamosActivos
     */
    public int getNumeroDePrestamosActivos() {
        return numeroDePrestamosActivos;
    }

    /**
     * @param numeroDePrestamosActivos the numeroDePrestamosActivos to set
     */
    public void setNumeroDePrestamosActivos(int numeroDePrestamosActivos) {
        this.numeroDePrestamosActivos = numeroDePrestamosActivos;
    }
    
    
    
}
