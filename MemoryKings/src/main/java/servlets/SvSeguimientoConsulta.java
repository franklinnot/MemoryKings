
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Consulta;
import logica.ControladoraLogica;

@WebServlet(name = "SvSeguimientoConsulta", urlPatterns = {"/SvSeguimientoConsulta"})
public class SvSeguimientoConsulta extends HttpServlet {

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
        
        // esto es util para mostrar el seguimiento de una consulta
        int id = Integer.parseInt(request.getParameter("idConsulta"));
        
        Consulta consulta = ctrl_logica.encontrarConsulta(id); // recibo la consulta
        
        HttpSession session = request.getSession();
        session.setAttribute("consulta", consulta);    // lo paso a la session para que los muestre 
        String section = "seguimiento";
        session.setAttribute("section", section);
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
