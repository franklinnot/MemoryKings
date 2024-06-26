package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.DetallePedido;
import logica.Distribuidor;
import logica.Empleado;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-20T00:17:16")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, String> metodoPago;
    public static volatile SingularAttribute<Pedido, Cliente> cliente;
    public static volatile SingularAttribute<Pedido, Date> fechaEnvio;
    public static volatile SingularAttribute<Pedido, Empleado> empleado;
    public static volatile ListAttribute<Pedido, DetallePedido> listaDetalle;
    public static volatile SingularAttribute<Pedido, String> estadoPedido;
    public static volatile SingularAttribute<Pedido, String> direccion;
    public static volatile SingularAttribute<Pedido, Float> costoTotal;
    public static volatile SingularAttribute<Pedido, Date> fechaPedido;
    public static volatile SingularAttribute<Pedido, Integer> idPedido;
    public static volatile SingularAttribute<Pedido, String> telefono;
    public static volatile SingularAttribute<Pedido, Distribuidor> distribuidor;

}