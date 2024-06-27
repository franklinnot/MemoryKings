
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Consulta;
import logica.ControladoraLogica;
import logica.DetallePedido;
import logica.Distribuidor;
import logica.Empleado;
import logica.Pedido;


@WebServlet(name = "SvRespuestaConsulta", urlPatterns = {"/SvRespuestaConsulta"})
public class SvRespuestaConsulta extends HttpServlet {

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
        
        // esto es util para mostrar las consultas asignadas al empleado
        int id = Integer.parseInt(request.getParameter("idConsulta"));
        
        Consulta consulta = ctrl_logica.encontrarConsulta(id); // recibo la consulta
        
        HttpSession session = request.getSession();
        session.removeAttribute("listaConsultas"); // removemos la lista de consultas para liberar memoria
        session.setAttribute("consulta", consulta);    // lo paso a la session para que los muestre 
        String section = "responder_consulta";
        session.setAttribute("section", section);
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
                request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // realizar la atencion de una consulta, asignado fecha de atencion
        
        // codigo para asignar correctamente la hora de la atencion
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        Date fechaActual = new Date();   
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset(); 
        
        HttpSession session = request.getSession();
        Empleado em = (Empleado) session.getAttribute("user");
        
        Consulta cn = (Consulta) session.getAttribute("consulta");
        String rpta = request.getParameter("rpta");
        
        cn.setFechaRespuesta(new Date(fechaActual.getTime() + desplazamientoPeru));
        cn.setEstadoConsulta("Atendido");
        cn.setEmpleado(em);
        cn.setRespuesta(rpta);
        
        try {
            ctrl_logica.editarConsulta(cn);
        } catch (Exception ex) {
            Logger.getLogger(SvRespuestaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // nos dirigmos a la seccion de consultas
        String section = "consultas";
        session.setAttribute("section", section); 

        System.out.println("El id del empleado logueado es " + em.getIdEmpleado());
        
        List<Consulta> listaConsultas = new ArrayList<>(); // lista vacia que tendra los pedidos del empleado
        List<Consulta> lista = ctrl_logica.traerConsultas();
        
        for (Consulta cs : lista){
            if(cs.getEmpleado().getIdEmpleado() == em.getIdEmpleado()){
                listaConsultas.add(cs);
                System.out.println("El id del empleado logueado es " + em.getIdEmpleado() + " y el "
                                  + "id del empleado relacionado al pedido " + cs.getIdConsulta() + " es " + cs.getEmpleado().getIdEmpleado());
            }
            else if(em.getCargo().equals("Administrador")){
                listaConsultas.add(cs);
            }
        }
        
        session.removeAttribute("consulta");
        session.setAttribute("listaConsultas", listaConsultas);
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
