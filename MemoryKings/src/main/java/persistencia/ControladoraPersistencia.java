
package persistencia;

import java.util.List;
import logica.Categoria;
import logica.Cliente;
import logica.Consulta;
import logica.DetallePedido;
import logica.Distribuidor;
import logica.Empleado;
import logica.ImagenProducto;
import logica.Pedido;
import logica.Producto;
import logica.Proveedor;

public class ControladoraPersistencia {
    
    CategoriaJpaController categoriaJpa = new CategoriaJpaController();
    ClienteJpaController clienteJpa = new ClienteJpaController();
    ConsultaJpaController consultaJpa = new ConsultaJpaController();
    DetallePedidoJpaController detallePedidoJpa = new DetallePedidoJpaController();
    DistribuidorJpaController distribuidorJpa = new DistribuidorJpaController();
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    ImagenProductoJpaController imagenProductoJpa = new ImagenProductoJpaController();
    PedidoJpaController pedidoJpa = new PedidoJpaController();
    ProductoJpaController productoJpa = new ProductoJpaController();
    ProveedorJpaController proveedorJpa = new ProveedorJpaController();
    
    
    // Categoria
    public void crearCategoria(Categoria categoria){
        categoriaJpa.create(categoria);
    }
    
    public List<Categoria> traerCategorias(){
        return categoriaJpa.findCategoriaEntities();
    }
    
    // Cliente
    public void crearCliente(Cliente cliente){
        clienteJpa.create(cliente);
    }
    
    public List<Cliente> traerClientes(){
        return clienteJpa.findClienteEntities();
    }
    
    public Cliente encontrarCliente(int idCliente){
        return clienteJpa.findCliente(idCliente);
    }
    
    // Consulta
    public void crearConsulta(Consulta consulta){
        consultaJpa.create(consulta);
    }
    
    public List<Consulta> traerConsultas(){
        return consultaJpa.findConsultaEntities();
    }
    
    public Consulta encontrarConsulta(int idConsulta){
        return consultaJpa.findConsulta(idConsulta);
    }
    
    public void editarConsulta(Consulta consulta) throws Exception{
        consultaJpa.edit(consulta);
    }
    
    // Detalle Pedido
    public void crearDetallePedido(DetallePedido detallePedido){
        detallePedidoJpa.create(detallePedido);
    }
    
    public List<DetallePedido> traerDetallePedidos(){
        return detallePedidoJpa.findDetallePedidoEntities();
    }
    
    
    // Distribuidor
    public void crearDistribuidor(Distribuidor distribuidor){
        distribuidorJpa.create(distribuidor);
    }
    
    public List<Distribuidor> traerDistribuidores(){
        return distribuidorJpa.findDistribuidorEntities();
    }
    
    public Distribuidor encontrarDistribuidor(int idDistribuidor){
        return distribuidorJpa.findDistribuidor(idDistribuidor);
    }
    
    // Empleado
    public void crearEmpleado(Empleado empleado){
        empleadoJpa.create(empleado);
    }
    
    public List<Empleado> traerEmpleados(){
        return empleadoJpa.findEmpleadoEntities();
    }
    
    public Empleado encontrarEmpleado(int idEmpleado){
        return empleadoJpa.findEmpleado(idEmpleado);
    }
    
    // Imagen Producto
    public void crearImagenProducto(ImagenProducto imagenProducto){
        imagenProductoJpa.create(imagenProducto);
    }
    
    public List<ImagenProducto> traerImagenProductos(){
        return imagenProductoJpa.findImagenProductoEntities();
    }
   
    
    // Pedido
    public void crearPedido(Pedido pedido){
       pedidoJpa.create(pedido);
    }
    
    public List<Pedido> traerPedidos(){
        return pedidoJpa.findPedidoEntities();
    }
    
    public void editarPedido(Pedido pedido) throws Exception{
        pedidoJpa.edit(pedido);
    }
    
    public Pedido encontrarPedido(int idPedido){
        return pedidoJpa.findPedido(idPedido);
    }
    
    
    // Producto
    public void crearProducto(Producto producto){
        productoJpa.create(producto);
    }
    
    public List<Producto> traerProductos(){
        return productoJpa.findProductoEntities();
    }
    

    // Proveedor
    public void crearProveedor(Proveedor proveedor){
        proveedorJpa.create(proveedor);
    }
    
    public List<Proveedor> traerProveedores(){
        return proveedorJpa.findProveedorEntities();
    }
    
}
