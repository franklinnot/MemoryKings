
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
import logica.Producto;


@WebServlet(name = "SvBusquedaProductos", urlPatterns = {"/SvBusquedaProductos"})
public class SvBusquedaProductos extends HttpServlet {


    ControladoraLogica ctrl_logica = new ControladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");   
        
        HttpSession session = request.getSession();
        List<Producto> lista = ctrl_logica.traerProductos();
        List<Producto> listaProductos = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("productCode"));
        
        boolean exist = false;
        if (id != 0){
            for (Producto pd : lista){
                if (pd.getIdProducto() == id){
                    listaProductos.add(pd);
                    exist = true;
                }
            }
        }
        
        if(!exist){
            listaProductos = lista;
        }
        
        session.setAttribute("listaProductos",listaProductos);
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
