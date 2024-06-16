
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
        
        String id = request.getParameter("idProducto");
        
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = ctrl_logica.traerProductos();
        
        for (Producto producto : listaProductos){
            if(producto.getIdProducto() == Integer.parseInt(id) ){
                Mewing carrito = new Mewing();
                HttpSession session = request.getSession();
                carrito = (Mewing) session.getAttribute("user");
                carrito.addProducto(producto);
                session.setAttribute("user", carrito);
                break;
            }
        }
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession session = request.getSession();
        String section = "carrito";
        session.setAttribute("section", section);
        
        // si usara el forward, podria usar request pero a la url le da amsieda
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
