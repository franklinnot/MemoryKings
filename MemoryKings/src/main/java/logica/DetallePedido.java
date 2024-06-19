
package logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DetallePedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idDetallePedido;
    
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Producto producto;
    
    @Basic
    int cantidad;  

    public DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    // este metodo se usa para ver los detalles del pedido a la hora de agregar o eliminar productos
    // teniendo en cuenta que cada detalle se refiere a un producto en especifico
    public static DetallePedido obtenerDetalle(List<DetallePedido> listaDetalles, int idProducto) {
        for (DetallePedido dtl : listaDetalles){
            if (dtl.getProducto().getIdProducto() == idProducto){
                return dtl;
            }
        }
        return null;
    }
    
    // este metodo es util para conseguir todos los detalels de un  pedido
    public static List<DetallePedido> obtenerDetalle(int idProducto) {
        ControladoraLogica ctrl_logica = new ControladoraLogica();
        List<DetallePedido> listaDetalles = ctrl_logica.traerDetallePedidos();
        List<DetallePedido> detallePedido = new ArrayList<>();
        for (DetallePedido dtl : listaDetalles){
            if (dtl.getProducto().getIdProducto() == idProducto){
                detallePedido.add(dtl);
            }
        }
        return detallePedido;
    }
    
}
