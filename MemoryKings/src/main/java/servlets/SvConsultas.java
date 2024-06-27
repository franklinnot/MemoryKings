
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
import logica.Cliente;
import logica.Consulta;
import logica.ControladoraLogica;
import logica.Empleado;
import logica.Mewing;


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
        
        // esto sirve para traer las consultas hechas por el cliente
        HttpSession session = request.getSession();
        
        List<Consulta> listaConsultas = new ArrayList<>();
        List<Consulta> lista = ctrl_logica.traerConsultas();
        
        int id = Integer.parseInt(request.getParameter("idCliente"));
        
        for(Consulta cn : lista){
            if(cn.getCliente().getIdCliente() == id){
                listaConsultas.add(cn);
            }
        }
        
        session.setAttribute("listaConsultas", listaConsultas);
        String section = "consultas";
        session.setAttribute("section", section);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");    
        
        // esto se activa cuando registramos una nueva consulta
        
        
        // primero recibimos todos los atributos necesarios para registrar la consulta
        HttpSession session = request.getSession();
        Mewing mw = (Mewing) session.getAttribute("user");
        Cliente cliente = mw.getCliente();
        
        String descripcion = request.getParameter("descripcion");
        String tipo_consulta = request.getParameter("tipo_consulta");
        
        Empleado empleado = Empleado.elegirEmpleado("Atender Consulta");
        
        // asignamos estos valores a la consulta
        Consulta nueva_consulta = new Consulta();
        nueva_consulta.setCliente(cliente);
        nueva_consulta.setDescripcion(descripcion);
        nueva_consulta.setTipoConsulta(tipo_consulta);
        nueva_consulta.setEmpleado(empleado);
        
        ctrl_logica.crearConsulta(nueva_consulta); // creamos la consulta en la base de datos
        
        // este codigo es necesario para volver a cargar las consultas que le apareceran al cliente cuando la pagina se actualice
        List<Consulta> listaConsultas = new ArrayList<>();
        List<Consulta> lista = ctrl_logica.traerConsultas();
        
        int id = cliente.getIdCliente();
        
        for(Consulta cn : lista){
            if(cn.getCliente().getIdCliente() == id){
                listaConsultas.add(cn);
            }
        }
        
        session.setAttribute("listaConsultas", listaConsultas);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
