package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.Empleado;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-18T01:35:54")
@StaticMetamodel(Consulta.class)
public class Consulta_ { 

    public static volatile SingularAttribute<Consulta, String> descripcion;
    public static volatile SingularAttribute<Consulta, Cliente> cliente;
    public static volatile SingularAttribute<Consulta, Empleado> empleado;
    public static volatile SingularAttribute<Consulta, String> tipoConsulta;
    public static volatile SingularAttribute<Consulta, Date> fechaRegistro;
    public static volatile SingularAttribute<Consulta, Boolean> estadoConsulta;
    public static volatile SingularAttribute<Consulta, Integer> idConsulta;
    public static volatile SingularAttribute<Consulta, Date> fechaRespuesta;

}