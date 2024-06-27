
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
public class Proveedor implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idProveedor;
    @OneToMany(mappedBy="proveedor")
    private LinkedList<Producto> listaProducto;
    @Basic
    String ruc, correo, direccion, nombreComercial, razonSocial, telefono;

    public Proveedor() {
    }

    public Proveedor(String ruc, String correo, String direccion, String nombreComercial, String razonSocial, String telefono) {
        this.ruc = ruc;
        this.correo = correo;
        this.direccion = direccion;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public LinkedList<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(LinkedList<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
