
package servlets;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import logica.Categoria;
import logica.Cliente;
import logica.ControladoraLogica;
import logica.ImagenProducto;
import logica.Producto;
import logica.Proveedor;
@MultipartConfig
@WebServlet(name = "SvRegistroProductos", urlPatterns = {"/SvRegistroProductos"})
public class SvRegistroProductos extends HttpServlet {
 
    ControladoraLogica ctrl_logica = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // este metodo get puede servir para buscar algun producto en especifico, por ahora
        // no tiene utilidad
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = ctrl_logica.traerProductos();
        
        request.setAttribute("listaProductos", listaProductos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("registro_productos.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); 
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Producto nuevo_producto = new Producto();
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String marca = request.getParameter("marca");
        float precio = Float.parseFloat(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");
        String proveedor = request.getParameter("proveedor");    
        
        nuevo_producto.setNombre(nombre);
        nuevo_producto.setDescripcion(descripcion);
        nuevo_producto.setMarca(marca);
        nuevo_producto.setPrecio(precio);
        
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias = ctrl_logica.traerCategorias();
        for (Categoria c : listaCategorias){
            String id = String.valueOf(c.getIdCategoria());
            if(categoria.equals(id)){
                nuevo_producto.setCategoria(c);
                break;
            }
        }
        
        List<Proveedor> listaProveedores = new ArrayList<>();
        listaProveedores = ctrl_logica.traerProveedores();
        for (Proveedor p : listaProveedores){
            String id = String.valueOf(p.getIdProveedor());
            if(proveedor.equals(id)){
                nuevo_producto.setProveedor(p);
                break;
            }
        }

        
        
        // logica para imagenes, teniendo en cuenta que
        // tengo una clase ImagenProducto, y cada instancia
        // apunta hacia el mismo producto
        // al final debe registrarse en la base de datos el producto y las imagenes
        
        ctrl_logica.crearProducto(nuevo_producto);

        
        // Procesar las imágenes subidas
        for (Part part : request.getParts()) {
            if (part.getName().equals("imagenes") && part.getSize() > 0) {
                InputStream inputStream = part.getInputStream();
                byte[] imagenBytes = inputStream.readAllBytes();
                
                // Crear la instancia de ImagenProducto
                ImagenProducto imagen = new ImagenProducto();
                imagen.setProducto(nuevo_producto);
                imagen.setImagen(imagenBytes);

                // Guardar la imagen en la base de datos
                ctrl_logica.crearImagenProducto(imagen);
            }
        }
        
        
        response.sendRedirect("http://localhost:8080/MemoryKings/HTML/registro_productos.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
