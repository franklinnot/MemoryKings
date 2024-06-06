
package logica;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImagenProducto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idImagenProducto;
    
    @ManyToOne
    private Producto producto;


    public ImagenProducto() {
    }

    public ImagenProducto(Producto producto, String descripcion) {
        this.producto = producto;
    }

    
    public int getIdImagenProducto() {
        return idImagenProducto;
    }

    public void setIdImagenProducto(int idImagenProducto) {
        this.idImagenProducto = idImagenProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    
          
}
