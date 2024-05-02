

package logica;
import java.util.Date;

public class Consulta {
    
    int idConsulta, idEmpleado, idCliente;
    String tipoConsulta, descripcionCosulta, estadoConsulta;
    Date fechaConsulta;
    
    public Consulta(){
        
    }

    public Consulta(int idConsulta, int idEmpleado, int idCliente, String tipoConsulta, String descripcionCosulta, String estadoConsulta, Date fechaConsulta) {
        this.idConsulta = idConsulta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.tipoConsulta = tipoConsulta;
        this.descripcionCosulta = descripcionCosulta;
        this.estadoConsulta = estadoConsulta;
        this.fechaConsulta = fechaConsulta;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
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

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getDescripcionCosulta() {
        return descripcionCosulta;
    }

    public void setDescripcionCosulta(String descripcionCosulta) {
        this.descripcionCosulta = descripcionCosulta;
    }

    public String getEstadoConsulta() {
        return estadoConsulta;
    }

    public void setEstadoConsulta(String estadoConsulta) {
        this.estadoConsulta = estadoConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
    
}
