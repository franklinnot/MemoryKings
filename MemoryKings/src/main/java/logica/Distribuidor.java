
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
public class Distribuidor implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idDistribuidor;
    @OneToMany(mappedBy="distribuidor")
    private LinkedList<Pedido> listaPedido;
    @Basic
    String ruc, correo, direccion, nombreComercial, razonSocial, telefono;

    public Distribuidor() {
    }

    public Distribuidor(String telefono, String ruc, String correo, String direccion, String nombreComercial, String razonSocial) {
        this.telefono = telefono;
        this.ruc = ruc;
        this.correo = correo;
        this.direccion = direccion;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
    }

    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public LinkedList<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(LinkedList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    
    
    
}
