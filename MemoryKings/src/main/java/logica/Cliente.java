

package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Cliente implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idCliente;
    
    @Basic
    int dni;
    String metodoPagoPref, estadoCuenta, nombres, apellidoPaterno, apellidoMaterno, correo, direccion, genero;
    
    @Temporal(TemporalType.DATE)
    Date fechaNacimiento;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaRegistro;
    
    public Cliente(){
        
    }

    public Cliente(int idCliente, int dni, String metodoPagoPref, String estadoCuenta, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String direccion, String genero, Date fechaRegistro, Date fechaNacimiento) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.metodoPagoPref = metodoPagoPref;
        this.estadoCuenta = estadoCuenta;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.fechaRegistro = fechaRegistro;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMetodoPagoPref() {
        return metodoPagoPref;
    }

    public void setMetodoPagoPref(String metodoPagoPref) {
        this.metodoPagoPref = metodoPagoPref;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

   
    
    
   
}
