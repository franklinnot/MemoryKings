
package logica;
import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idCategoria;
    
    @OneToMany(mappedBy="categoria")
    private LinkedList<Producto> listaProducto;
    
    @Basic
    String nombre;  

    public Categoria() {
    }

    public Categoria(LinkedList<Producto> listaProducto, String nombre, String descripcion) {
        this.listaProducto = listaProducto;
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LinkedList<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(LinkedList<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
