

package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;


public class ControladoraLogica {
    
    
    ControladoraPersistencia ctrl_persistencia = new ControladoraPersistencia();
    
    public void crearCliente(Cliente cliente){
         ctrl_persistencia.crearCliente(cliente);
    }
    
    public List<Cliente> traerClientes(){
        return ctrl_persistencia.traerClientes();
    }
    
    public List<Producto> traerProductos(){
        return ctrl_persistencia.traerProductos();
    }
}
