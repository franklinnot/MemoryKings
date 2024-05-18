
package logica;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empleado implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idEmpleado;
    @OneToMany(mappedBy="empleado")
    private LinkedList<Consulta> listaConsulta;
    @Basic
    long telefono;
    double salario;
    int dni, nivelPrivilegio;  
    String nombres, apellidos, correo, direccion, genero, password, cargo; // Importante
    String estadoCuenta; // no necesario para el contructor
    @Temporal(TemporalType.DATE)
    Date fechaNacimiento, fechaContrato, vencimientoContrato;
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaRegistro; // no necesario para el contructor

    public Empleado() {
    }

    public Empleado(long telefono, double salario, int dni, int nivelPrivilegio, String nombres, String apellidos, String correo, String direccion, String genero, String password, String cargo, Date fechaNacimiento, Date fechaContrato, Date vencimientoContrato) {
        this.telefono = telefono;
        this.salario = salario;
        this.dni = dni;
        this.nivelPrivilegio = nivelPrivilegio;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.cargo = cargo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
        this.vencimientoContrato = vencimientoContrato;
        
        
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        // Crear un objeto Date para la fecha actual
        Date fechaActual = new Date();   
        // Obtener el desplazamiento de la zona horaria de Perú en milisegundos
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
        // Ajustar la fecha actual para la zona horaria de Perú
        this.fechaRegistro = new Date(fechaActual.getTime() + desplazamientoPeru);
        
        
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LinkedList<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    public void setListaConsulta(LinkedList<Consulta> listaConsulta) {
        this.listaConsulta = listaConsulta;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getNivelPrivilegio() {
        return nivelPrivilegio;
    }

    public void setNivelPrivilegio(int nivelPrivilegio) {
        this.nivelPrivilegio = nivelPrivilegio;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getVencimientoContrato() {
        return vencimientoContrato;
    }

    public void setVencimientoContrato(Date vencimientoContrato) {
        this.vencimientoContrato = vencimientoContrato;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
    
}
