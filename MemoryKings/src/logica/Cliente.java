

package logica;
import java.sql.Date;

public class Cliente extends Persona {
    
    int idCliente, idConsulta, idPedido;
    String metodoPagoPref, estadoCuenta;
    Date fechaRegistro;
    
    public Cliente(){
        
    }

    public Cliente(int idCliente, int idConsulta, int idPedido,String metodoPagoPref, String estadoCuenta, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.idConsulta = idConsulta;
        this.idPedido = idPedido;
        this.metodoPagoPref = metodoPagoPref;
        this.estadoCuenta = estadoCuenta;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    
    public String getMetodoPagoPref() {
        return metodoPagoPref;
    }

    public void setMetodoPagoPref(String metodoPagoPref) {
        this.metodoPagoPref = metodoPagoPref;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
