package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Consulta;
import logica.Pedido;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-15T22:33:16")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile ListAttribute<Empleado, Consulta> listaConsulta;
    public static volatile SingularAttribute<Empleado, String> apellidos;
    public static volatile SingularAttribute<Empleado, Date> fechaNacimiento;
    public static volatile SingularAttribute<Empleado, Date> fechaRegistro;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, String> nombres;
    public static volatile SingularAttribute<Empleado, String> password;
    public static volatile SingularAttribute<Empleado, Integer> idEmpleado;
    public static volatile ListAttribute<Empleado, Pedido> listaPedido;
    public static volatile SingularAttribute<Empleado, String> correo;
    public static volatile SingularAttribute<Empleado, String> genero;
    public static volatile SingularAttribute<Empleado, String> cargo;
    public static volatile SingularAttribute<Empleado, String> telefono;
    public static volatile SingularAttribute<Empleado, String> dni;

}