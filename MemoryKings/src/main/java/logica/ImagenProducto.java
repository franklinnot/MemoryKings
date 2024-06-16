
package logica;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ImagenProducto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idImagenProducto;

    @ManyToOne
    private Producto producto;
    
    @Lob
    @Basic
    private byte[] imagen;


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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public static ImagenProducto obtenerImagen(List<ImagenProducto> listaImagenes, int idProducto) {
        int izquierda = 0;
        int derecha = listaImagenes.size() - 1;
        listaImagenes.sort(Comparator.comparingInt(imagen -> imagen.getProducto().getIdProducto()));
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            ImagenProducto imagen = listaImagenes.get(medio);

            if (imagen.getProducto().getIdProducto() == idProducto) {
                return imagen;
            } else if (imagen.getProducto().getIdProducto() < idProducto) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }  
    
          
}
