
package persistencia;

import java.util.List;
import logica.Cliente;
import logica.Producto;


public class ControladoraPersistencia {
    
    
    ClienteJpaController clienteJpa = new ClienteJpaController();
    ProductoJpaController productoJpa = new ProductoJpaController();
    
    // OPERACIONES CRUD para CLIENTE
    // Create
    public void crearCliente(Cliente cliente){
        clienteJpa.create(cliente);
    }
    
    // Read
    public List<Cliente> traerClientes(){
        return clienteJpa.findClienteEntities();
    }
    
    // Update
    
    
    public List<Producto> traerProductos(){
        return productoJpa.findProductoEntities();
    }
    
}
