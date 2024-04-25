

package logica;
import java.sql.Date;

public class Pedido {
    
    int idPedido, idEmpleado,idCliente, idDistribuidor;
    String estadoPedido, metodoPago;
    Date fechaPedido, fechaEnvio, fechaEntrega;
    
    public Pedido(){
        
    }

    public Pedido(int idPedido, int idEmpleado, int idCliente, int idDistribuidor, String estadoPedido, String metodoPago, Date fechaPedido, Date fechaEnvio, Date fechaEntrega) {
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idDistribuidor = idDistribuidor;
        this.estadoPedido = estadoPedido;
        this.metodoPago = metodoPago;
        this.fechaPedido = fechaPedido;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
}
