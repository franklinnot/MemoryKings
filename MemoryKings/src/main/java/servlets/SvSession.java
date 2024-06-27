
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvSession", urlPatterns = {"/SvSession"})
public class SvSession extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // esto se activa unicamente cuando se quiera "cancelar" algun proceso
        // pero no cerrar sesion
        
        String section = request.getParameter("cancelarProceso");
        
        HttpSession session = request.getSession();
        session.setAttribute("section", section);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // esto se activa cuando se quiere cerrar sesión
        
        HttpSession session = request.getSession(false); // Obtener la sesión sin crear una nueva
        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }
        response.sendRedirect(request.getContextPath() + "/"); // Redirigir a la página principal o de inicio de sesión
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
