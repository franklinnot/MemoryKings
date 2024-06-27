
package logica;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Mewing {
    
    Cliente cliente = new Cliente();
    List<Consulta> listaConsulta = new ArrayList<>(); // este quizas no lo use, pero lo dejo como guiño guiño
    List<DetallePedido> listaDetalles = new ArrayList<>();

    public Mewing() {
        
    }
    
    public Mewing(Cliente cliente) {
        this.cliente = cliente;
        this.listaDetalles = new ArrayList<>();
        this.listaConsulta = new ArrayList<>();
    }

    
    // este metodo se llama solo cuando se agrega un nuevo producto al carrito
    public void addProducto(Producto producto){
        List<DetallePedido> listaDetalles = new ArrayList<>();
        // la lista de detalles del carrito de esta instancia
        listaDetalles = this.listaDetalles; 
        
        DetallePedido dtl = new DetallePedido();
        
        // aqui obtenemos al detalle que almacena ese producto
        dtl = DetallePedido.obtenerDetalle(listaDetalles, producto.getIdProducto());
        // si ese producto no fue antes agregado o si aun no ha agregado nada, entonces creamos un nuevo detalle y lo agregamos
        if (listaDetalles.isEmpty() || dtl == null){ // si el carrito esta vacio o no encuentra coincidencias
            DetallePedido nuevo_detalle = new DetallePedido();
            nuevo_detalle.setCantidad(1);
            nuevo_detalle.setProducto(producto);
            listaDetalles.add(nuevo_detalle);
            System.out.println("Se agregó un nuevo producto al carrito");        
        }
        // si ese producto ya esta en el carrito, entonces solo modificamos la cantidad de ese detalle que hace referencia a 
        // ese producto
        else{
            for (DetallePedido detalle : listaDetalles){
                if (detalle.getProducto().getIdProducto() == producto.getIdProducto()){
                    detalle.cantidad += 1;
                    System.out.println("Se agregó producto ya existente al carrito");
                    break;
                }
            }
        }
        // esto solo se hace por comprobar que todo haya salido correctamente e imprimir la accion
        System.out.println("El producto: " + producto.getNombre() + " se agregó al carrito."); 
        
        // actualizamos la lista de detalles de esta instancia
        this.setListaDetalles(listaDetalles);
        
    }
    
    // este metodo se llama solo cuando se elimina un producto del carrito
    public void removeProducto(Producto del_product){ 
        List<DetallePedido> listaDetalles = new ArrayList<>();
        listaDetalles = this.listaDetalles;
        
        if (!listaDetalles.isEmpty()){
            for (DetallePedido dtl : listaDetalles){
                if(dtl.getProducto().getIdProducto() == del_product.getIdProducto()){
                    if (dtl.cantidad <= 1){
                        listaDetalles.remove(dtl);
                    }
                    else{
                        dtl.cantidad -= 1;
                    }
                    break;
                }
        }
        }
        this.listaDetalles = listaDetalles;
    }
    
    
    
    public int cantidadProductos(){
        List<DetallePedido> listaDetalles = new ArrayList<>();
        listaDetalles = this.listaDetalles;
        
        int cantidad = 0;
        for (DetallePedido detalle : listaDetalles){
            cantidad += detalle.getCantidad();
        }
        return cantidad;
    }
    
    public float costoTotal(){
        List<DetallePedido> listaDetalles = new ArrayList<>();
        listaDetalles = this.listaDetalles;
        
        float cantidad = 0;
        for (DetallePedido detalle : listaDetalles){
            cantidad += detalle.getCantidad() * detalle.getProducto().getPrecio();
        }
        return cantidad;
    }
    
    public static float costoTotal(List<DetallePedido> listaDetalles){     
        float cantidad = 0;
        for (DetallePedido detalle : listaDetalles){
            cantidad += detalle.getCantidad() * detalle.getProducto().getPrecio();
        }
        return cantidad;
    } 
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetallePedido> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
    
    
    
}



