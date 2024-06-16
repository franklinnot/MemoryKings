
package logica;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idProducto;
    
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Proveedor proveedor;
    @OneToMany(mappedBy="producto")
    private LinkedList<DetallePedido> listaDetalle;
    @OneToMany(mappedBy="producto")
    private LinkedList<ImagenProducto> listaImagen;
    
    @Basic
    String nombre, descripcion, marca;
    float precio;

    public Producto() {
        
    }
    
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LinkedList<DetallePedido> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(LinkedList<DetallePedido> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public LinkedList<ImagenProducto> getListaImagen() {
        return listaImagen;
    }

    public void setListaImagen(LinkedList<ImagenProducto> listaImagen) {
        this.listaImagen = listaImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
    public static Producto obtenerProducto(List<Producto> listaProductos, int idProducto) {
        int izquierda = 0;
        int derecha = listaProductos.size() - 1;
        listaProductos.sort(Comparator.comparingInt(prd -> prd.getIdProducto()));
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Producto producto = listaProductos.get(medio);

            if (producto.getIdProducto() == idProducto) {
                return producto;
            } else if (producto.getIdProducto() < idProducto) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }
    
    
    
}
