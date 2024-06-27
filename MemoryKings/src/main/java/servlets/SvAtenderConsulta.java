
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
import logica.Consulta;
import logica.ControladoraLogica;
import logica.Empleado;
import logica.Pedido;


@WebServlet(name = "SvAtenderConsulta", urlPatterns = {"/SvAtenderConsulta"})
public class SvAtenderConsulta extends HttpServlet {

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
        
        // devolver las consultas segun el empleado
        
        HttpSession session = request.getSession();
        Empleado empleado = (Empleado) session.getAttribute("user");
        
        List<Consulta> listaConsultas = ctrl_logica.traerConsultas();
        List<Consulta> lista = new ArrayList<>();
        
        for (Consulta cn : listaConsultas){
            if(cn.getEmpleado().getIdEmpleado() == empleado.getIdEmpleado()){
                lista.add(cn);
            }
            else if (empleado.getCargo().equals("Administrador")){
                lista.add(cn);
            }
        }
        
        listaConsultas.clear();
        listaConsultas = lista;
        
        String section = "consultas";
        session.setAttribute("section", section);
        session.setAttribute("listaConsultas", listaConsultas);
        
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
