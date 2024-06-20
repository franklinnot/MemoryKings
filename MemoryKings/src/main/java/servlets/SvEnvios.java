
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
import logica.ControladoraLogica;
import logica.DetallePedido;
import logica.Distribuidor;
import logica.Empleado;
import logica.Pedido;

@WebServlet(name = "SvEnvios", urlPatterns = {"/SvEnvios"})
public class SvEnvios extends HttpServlet {

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
        
        int id = Integer.parseInt(request.getParameter("idPedido"));
        
        List<DetallePedido> listaDetalles = DetallePedido.obtenerDetalle(id); // recibo los detalles del pedido
        
        HttpSession session = request.getSession();
        session.setAttribute("listaDetalles", listaDetalles);    // lo paso a la session para que los muestre 
        String section = "envio";
        session.setAttribute("section", section);
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // codigo para asignar correctamente la hora el envio
        TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");        
        Date fechaActual = new Date();   
        int desplazamientoPeru = zonaHorariaPeru.getRawOffset(); 
        
        HttpSession session = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("idPedido")); // id de pedido
        Pedido pd = ctrl_logica.encontrarPedido(id); // buscamos el pedido que se esta atendiendo
        
        int id_ds = Integer.parseInt(request.getParameter("distribuidor")); // id de distribuidor
        Distribuidor ds = ctrl_logica.encontrarDistribuidor(id_ds); // recibimos al distribuidor de la bd
         
        pd.setFechaEnvio(new Date(fechaActual.getTime() + desplazamientoPeru));
        pd.setEstadoPedido("Enviado");
        pd.setDistribuidor(ds);

        try {
            ctrl_logica.editarPedido(pd);
        } catch (Exception ex) {
            Logger.getLogger(SvEnvios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // nos dirigmos a la seccion pedidos
        String section = "pedidos";
        session.setAttribute("section", section); 

        // traigo al empleado logueado
        Empleado em = (Empleado) session.getAttribute("user");
        System.out.println("El id del empleado logueado es " + em.getIdEmpleado());
        List<Pedido> listaPedidos = new ArrayList<>(); // lista vacia que tendra los pedidos del empleado
        List<Pedido> lista = ctrl_logica.traerPedidos();
        
        for (Pedido pe : lista){
            if(pe.getEmpleado().getIdEmpleado() == em.getIdEmpleado()){
                listaPedidos.add(pe);
                System.out.println("El id del empleado logueado es " + em.getIdEmpleado() + " y el "
                                  + "id del empleado relacionado al pedido " + pe.getIdPedido() + " es " + pe.getEmpleado().getIdEmpleado());
            }
            else if(em.getCargo().equals("Administrador")){
                listaPedidos.add(pe);
            }
        }
        
        session.setAttribute("listaPedidos", listaPedidos);
        response.sendRedirect("http://localhost:8080/MemoryKings/");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
