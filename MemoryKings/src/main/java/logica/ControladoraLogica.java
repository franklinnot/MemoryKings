

package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;


public class ControladoraLogica {
    
    
    ControladoraPersistencia ctrl_persistencia = new ControladoraPersistencia();
    
    public void crearUsuario(Cliente cliente){
         ctrl_persistencia.crearCliente(cliente);
    }
    
    public List<Cliente> traerCliente(){
        return ctrl_persistencia.traerUsuario();
    }
}
