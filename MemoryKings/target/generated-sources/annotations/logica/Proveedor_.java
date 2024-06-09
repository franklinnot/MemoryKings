package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Producto;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T18:32:00")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> ruc;
    public static volatile SingularAttribute<Proveedor, String> razonSocial;
    public static volatile SingularAttribute<Proveedor, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedor, String> correo;
    public static volatile ListAttribute<Proveedor, Producto> listaProducto;
    public static volatile SingularAttribute<Proveedor, String> direccion;
    public static volatile SingularAttribute<Proveedor, String> nombreComercial;
    public static volatile SingularAttribute<Proveedor, String> telefono;

}