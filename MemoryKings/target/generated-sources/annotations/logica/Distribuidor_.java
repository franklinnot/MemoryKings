package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Pedido;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:51:45")
@StaticMetamodel(Distribuidor.class)
public class Distribuidor_ { 

    public static volatile SingularAttribute<Distribuidor, String> ruc;
    public static volatile SingularAttribute<Distribuidor, String> razonSocial;
    public static volatile ListAttribute<Distribuidor, Pedido> listaPedido;
    public static volatile SingularAttribute<Distribuidor, String> correo;
    public static volatile SingularAttribute<Distribuidor, String> direccion;
    public static volatile SingularAttribute<Distribuidor, String> nombreComercial;
    public static volatile SingularAttribute<Distribuidor, String> telefono;
    public static volatile SingularAttribute<Distribuidor, Integer> idDistribuidor;

}