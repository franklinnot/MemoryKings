
package logica;
import java.io.Serializable;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idCliente;
    @Basic
    long telefono;
    int dni;  
    String nombres, apellidos, correo, direccion, genero, password; // Importante
    String metodoPagoPref, estadoCuenta; // no necesario para el contructor
    @Temporal(TemporalType.DATE)
    Date fechaNacimiento;
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaRegistro; // no necesario para el contructor
    
    public Cliente(){
        
    }
    

    // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
    public Cliente(int dni, String nombres, String apellidos, String correo, String password, long telefono, String direccion, String genero, Date fechaNacimiento) {
        // BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());
        this.telefono = telefono;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
   
}
