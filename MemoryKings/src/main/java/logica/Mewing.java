
package logica;
import java.util.ArrayList;
import java.util.List;


public class Mewing {
    
    Cliente cliente = new Cliente();
    List<Producto> listaProducto = new ArrayList<>();
    List<Consulta> listaConsulta = new ArrayList<>(); // este quizas no lo use, pero lo dejo como guiño guiño

    public Mewing() {
        
    }
    
    public Mewing(Cliente cliente) {
        this.cliente = cliente;
        this.listaProducto = new ArrayList<>();
        this.listaConsulta = new ArrayList<>();
    }

    public void addProducto(Producto producto){
        this.listaProducto.add(producto);
    }
    
    public void removeProducto(Producto del_product){ 
        listaProducto.removeIf(product -> product.equals(del_product));
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
}



