
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
import logica.Cliente;
import logica.Consulta;
import logica.ControladoraLogica;


@WebServlet(name = "SvConsultas", urlPatterns = {"/SvConsultas"})
public class SvConsultas extends HttpServlet {
    
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
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = ctrl_logica.encontrarCliente(idCliente);
        List<Consulta> listaConsultas = new ArrayList<>();
        listaConsultas = ctrl_logica.traerConsultas();
        
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