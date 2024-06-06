
package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.Producto;

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
        
        List<Producto> listaProducto = new ArrayList<>();
        listaProducto = ctrl_logica.traerProductos();
        
        HttpSession session = request.getSession(false);
        session.setAttribute("datos_usuario", listaProducto);
        response.sendRedirect("http://localhost:8080/MemoryKings/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
