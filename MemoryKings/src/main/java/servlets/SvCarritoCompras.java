
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.DetallePedido;
import logica.Mewing;
import logica.Producto;

@WebServlet(name = "SvCarritoCompras", urlPatterns = {"/SvCarritoCompras"})
public class SvCarritoCompras extends HttpServlet {

    ControladoraLogica ctrl_logica = new ControladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // metodo para cuando el cliente agrega un producto a su carrito
        
        String id = request.getParameter("idProducto");
        
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = ctrl_logica.traerProductos();
        
        Producto producto = Producto.obtenerProducto(listaProductos, Integer.parseInt(id));
        
        HttpSession session = request.getSession();
        Mewing carrito = new Mewing();
        carrito = (Mewing) session.getAttribute("user");
        
        carrito.addProducto(producto);
        session.setAttribute("user", carrito);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // esto se activa cuando el usuario da click en el boton de carrito
        
        HttpSession session = request.getSession();
        
        Mewing carrito = (Mewing) session.getAttribute("user");
        
        List<DetallePedido> listaDetalles = new ArrayList<>();
        listaDetalles = carrito.getListaDetalles();
        
        if(listaDetalles.isEmpty()){
            String section = "productos";
            session.setAttribute("section", section);
        }
        else{
            String section = "carrito";
            session.setAttribute("section", section);
        }
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
