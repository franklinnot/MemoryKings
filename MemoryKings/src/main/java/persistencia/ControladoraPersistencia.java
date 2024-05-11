
package persistencia;

import java.util.List;
import logica.Cliente;


public class ControladoraPersistencia {
    
    
    ClienteJpaController clienteJpa = new ClienteJpaController();
    
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
    
    
}
