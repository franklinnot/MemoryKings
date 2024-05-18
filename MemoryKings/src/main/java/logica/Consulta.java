
package logica;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consulta implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idConsulta;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Empleado empleado;
    @Basic
    String tipoConsulta, descripcion;
    boolean estadoConsulta;
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaRegistro, fechaRespuesta; // no necesario para el contructor

    public Consulta() {
    }

    public Consulta(Cliente cliente, Empleado empleado, String tipoConsulta, String descripcion) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.tipoConsulta = tipoConsulta;
        this.descripcion = descripcion;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
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

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstadoConsulta() {
        return estadoConsulta;
    }

    public void setEstadoConsulta(boolean estadoConsulta) {
        this.estadoConsulta = estadoConsulta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
    
    
    
}
