package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Categoria;
import logica.DetallePedido;
import logica.ImagenProducto;
import logica.Proveedor;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T18:32:00")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, String> marca;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile ListAttribute<Producto, DetallePedido> listaDetalle;
    public static volatile SingularAttribute<Producto, Categoria> categoria;
    public static volatile SingularAttribute<Producto, Proveedor> proveedor;
    public static volatile ListAttribute<Producto, ImagenProducto> listaImagen;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, String> nombre;

}