package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Producto;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T18:32:00")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile ListAttribute<Categoria, Producto> listaProducto;
    public static volatile SingularAttribute<Categoria, Integer> idCategoria;
    public static volatile SingularAttribute<Categoria, String> nombre;

}