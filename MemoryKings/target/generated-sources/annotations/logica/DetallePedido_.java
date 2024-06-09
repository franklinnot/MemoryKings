package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Pedido;
import logica.Producto;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T18:32:00")
@StaticMetamodel(DetallePedido.class)
public class DetallePedido_ { 

    public static volatile SingularAttribute<DetallePedido, Pedido> pedido;
    public static volatile SingularAttribute<DetallePedido, Producto> producto;
    public static volatile SingularAttribute<DetallePedido, Integer> cantidad;
    public static volatile SingularAttribute<DetallePedido, Integer> idDetallePedido;

}