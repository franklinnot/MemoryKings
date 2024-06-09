
package logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Random;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idPedido;
    
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Empleado empleado;
    @ManyToOne
    private Distribuidor distribuidor;
    @OneToMany(mappedBy="pedido")
    private LinkedList<DetallePedido> listaDetalle;
    
    @Basic
    String estadoPedido, metodoPago;

    @Temporal(TemporalType.TIMESTAMP)
    Date fechaPedido, fechaEnvio; // "fechaEnvio" no es necesario para el contructor

    public Pedido() {
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        // Crear un objeto Date para la fecha actual
        Date fechaActual = new Date();   
        // Obtener el desplazamiento de la zona horaria de Perú en milisegundos
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
        // Ajustar la fecha actual para la zona horaria de Perú
        this.fechaPedido = new Date(fechaActual.getTime() + desplazamientoPeru);
    }

    public Pedido(Cliente cliente, Empleado empleado, Distribuidor distribuidor, String metodoPago) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.distribuidor = distribuidor;
        this.estadoPedido = "Pendiente";
        
        Random random = new Random();
        List<String> metodos = new ArrayList<>();
        metodos.add("Yape");
        metodos.add("BCP Banca Movil");
        metodos.add("BBVA Movil");
        metodos.add("Interbank Banca Movil");
        metodos.add("PagoEfectivo");
        metodos.add("SafetyPay");
        metodos.add("Plin");
        metodos.add("Tunki");
        int randomValue = random.nextInt(metodos.size());
        this.metodoPago = metodos.get(randomValue);
        
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        // Crear un objeto Date para la fecha actual
        Date fechaActual = new Date();   
        // Obtener el desplazamiento de la zona horaria de Perú en milisegundos
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
        // Ajustar la fecha actual para la zona horaria de Perú
        this.fechaPedido = new Date(fechaActual.getTime() + desplazamientoPeru);
              
    }

    public LinkedList<DetallePedido> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(LinkedList<DetallePedido> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
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
    
    
    
}
