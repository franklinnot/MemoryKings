

package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;


public class ControladoraLogica {
    
    
    ControladoraPersistencia ctrl_persistencia = new ControladoraPersistencia();
    
    // Categoria
    public void crearCategoria(Categoria categoria){
        ctrl_persistencia.crearCategoria(categoria);
    }
    
    public List<Categoria> traerCategorias(){
        return ctrl_persistencia.traerCategorias();
    }
    
    // Cliente
    public void crearCliente(Cliente cliente){
         ctrl_persistencia.crearCliente(cliente);
    }
    
    public List<Cliente> traerClientes(){
        return ctrl_persistencia.traerClientes();
    }
    
    public Cliente encontrarCliente(int idCliente){
        return ctrl_persistencia.encontrarCliente(idCliente);
    }
    
    // Consulta
    public void crearConsulta(Consulta consulta){
        ctrl_persistencia.crearConsulta(consulta);
    }
    
    public List<Consulta> traerConsultas(){
        return ctrl_persistencia.traerConsultas();
    }
    
    // DetallePedido
    public void crearDetallePedido(DetallePedido detallePedido){
        ctrl_persistencia.crearDetallePedido(detallePedido);
    }
    
    public List<DetallePedido> traerDetallePedidos(){
        return ctrl_persistencia.traerDetallePedidos();
    }
    
    // Distribuidor
    public void crearDistribuidor(Distribuidor distribuidor){
        ctrl_persistencia.crearDistribuidor(distribuidor);
    }
    
    public List<Distribuidor> traerDistribuidores(){
        return ctrl_persistencia.traerDistribuidores();
    }
    
    public Distribuidor encontrarDistribuidor(int idDistribuidor){
        return ctrl_persistencia.encontrarDistribuidor(idDistribuidor);
    }
    
    // Empleado
    public void crearEmpleado(Empleado empleado){
        ctrl_persistencia.crearEmpleado(empleado);
    }
    
    public List<Empleado> traerEmpleados(){
        return ctrl_persistencia.traerEmpleados();
    }
    
    public Empleado encontrarEmpleado(int idEmpleado){
        return ctrl_persistencia.encontrarEmpleado(idEmpleado);
    }
    
    // ImagenProducto
    public void crearImagenProducto(ImagenProducto imagenProducto){
        ctrl_persistencia.crearImagenProducto(imagenProducto);
    }
    
    public List<ImagenProducto> traerImagenProductos(){
        return ctrl_persistencia.traerImagenProductos();
    }
    
    // Pedido
    public void crearPedido(Pedido pedido){
       ctrl_persistencia.crearPedido(pedido);
    }
    
    public List<Pedido> traerPedidos(){
        return ctrl_persistencia.traerPedidos();
    }
    
    public void editarPedido(Pedido pedido) throws Exception{
        ctrl_persistencia.editarPedido(pedido);
    }
    
    public Pedido encontrarPedido(int idPedido){
        return ctrl_persistencia.encontrarPedido(idPedido);
    }
    
    
    
    // Producto
    public void crearProducto(Producto producto){
        ctrl_persistencia.crearProducto(producto);
    }
    
    public List<Producto> traerProductos(){
        return ctrl_persistencia.traerProductos();
    }
    
    // Proveedor
    public void crearProveedor(Proveedor proveedor){
        ctrl_persistencia.crearProveedor(proveedor);
    }
    
    public List<Proveedor> traerProveedores(){
        return ctrl_persistencia.traerProveedores();
    }
    
 
}
