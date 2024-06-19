
package logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    @OneToMany(mappedBy="empleado")
    private LinkedList<Pedido> listaPedido;
    @Basic
    String dni, nombres, apellidos, correo, direccion, genero, password, cargo, telefono; // Importante
    @Temporal(TemporalType.DATE)
    Date fechaNacimiento;
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaRegistro; // no necesario para el contructor

    public Empleado() {
        
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        // Crear un objeto Date para la fecha actual
        Date fechaActual = new Date();   
        // Obtener el desplazamiento de la zona horaria de Perú en milisegundos
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
        // Ajustar la fecha actual para la zona horaria de Perú
        this.fechaRegistro = new Date(fechaActual.getTime() + desplazamientoPeru);
        
    }

    public Empleado(String telefono, String dni, String nombres, String apellidos, String correo, String direccion, String genero, String password, String cargo, Date fechaNacimiento) {
        this.telefono = telefono;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.cargo = cargo;
        this.fechaNacimiento = fechaNacimiento;

        
        
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        // Crear un objeto Date para la fecha actual
        Date fechaActual = new Date();   
        // Obtener el desplazamiento de la zona horaria de Perú en milisegundos
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
        // Ajustar la fecha actual para la zona horaria de Perú
        this.fechaRegistro = new Date(fechaActual.getTime() + desplazamientoPeru);
        
        
    }

    public LinkedList<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(LinkedList<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    // 
    public static Empleado obtenerEmpleado(List<Empleado> listaEmpleados, int idEmpleado) {
        int izquierda = 0;
        int derecha = listaEmpleados.size() - 1;
        listaEmpleados.sort(Comparator.comparingInt(emp -> emp.getIdEmpleado()));
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Empleado empleado = listaEmpleados.get(medio);

            if (empleado.getIdEmpleado() == idEmpleado) {
                return empleado;
            } else if (empleado.getIdEmpleado() < idEmpleado) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }
    
    public static Empleado elegirEmpleado(String cargo) {
        ControladoraLogica ctrl_logica = new ControladoraLogica();

        List<Empleado> listaEmpleados = ctrl_logica.traerEmpleados();
        List<Pedido> listaPedidos = ctrl_logica.traerPedidos();

        List<Empleado> empleados_aptos = new ArrayList<>();

        // Filtrar empleados por el cargo buscado
        for (Empleado empleado : listaEmpleados) {
            if (empleado != null && cargo.equals(empleado.getCargo())) {
                System.out.println("El empleado con el id " + empleado.getIdEmpleado() + " es apto");
                empleados_aptos.add(empleado);
            }
        }

        // Si no hay pedidos, elegir cualquier empleado apto
        if (listaPedidos == null || listaPedidos.isEmpty()) {
            if (!empleados_aptos.isEmpty()) {
                Empleado emp = empleados_aptos.get(0); // Elegir el primer empleado apto
                System.out.println("El empleado elegido fue " + emp.getNombres());
                return emp;
            } else {
                return null; // No hay empleados aptos
            }
        }

        // Buscar empleado con menos pedidos pendientes
        Empleado emp = null;
        int cantidad_anterior = Integer.MAX_VALUE; // Inicializar con un valor alto

        for (Empleado empleado : empleados_aptos) {
            int cantidad_pedidos = 0;
            for (Pedido pedido : listaPedidos) {
                if (pedido.getEmpleado() != null && empleado.getIdEmpleado() == pedido.getEmpleado().getIdEmpleado() && "Procesando pedido".equals(pedido.getEstadoPedido())) {
                    cantidad_pedidos++;
                }
            }

            if (cantidad_pedidos < cantidad_anterior) {
                cantidad_anterior = cantidad_pedidos;
                emp = empleado;
            }
        }

        if (emp != null) {
            System.out.println("El empleado elegido fue " + emp.getNombres() + "con el id " + emp.getIdEmpleado());
        }

        return emp;
    }

    
}
