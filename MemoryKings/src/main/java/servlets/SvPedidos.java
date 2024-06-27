
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
import logica.DetallePedido;
import logica.Empleado;
import logica.Mewing;
import logica.Pedido;

@WebServlet(name = "SvPedidos", urlPatterns = {"/SvPedidos"})
public class SvPedidos extends HttpServlet {
    
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
        
        // devolver los pedidos segun el empleado
        
        HttpSession session = request.getSession();
        Empleado empleado = (Empleado) session.getAttribute("user");
        
        List<Pedido> listaPedidos = ctrl_logica.traerPedidos();
        List<Pedido> lista = new ArrayList<>();
        
        for (Pedido pd : listaPedidos){
            if(pd.getEmpleado().getIdEmpleado() == empleado.getIdEmpleado()){
                lista.add(pd);
            }
            else if (empleado.getCargo().equals("Administrador")){
                lista.add(pd);
            }
        }
        
        listaPedidos.clear();
        listaPedidos = lista;
        
        String section = "pedidos";
        session.setAttribute("section", section);
        session.setAttribute("listaPedidos", listaPedidos);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // realizar pedidos
        
        HttpSession session = request.getSession();
        Mewing mewing = (Mewing) session.getAttribute("user");
        
        String direccion = request.getParameter("pedido_direccion");
        String telefono = request.getParameter("pedido_telefono");
        String metodoPago = request.getParameter("metodo_pago");
        Empleado empleado = Empleado.elegirEmpleado("Atender Pedido");
        
        Pedido nuevo_pedido = new Pedido();
        
        nuevo_pedido.setCliente(mewing.getCliente());
        nuevo_pedido.setDireccion(direccion);
        nuevo_pedido.setTelefono(telefono);
        nuevo_pedido.setMetodoPago(telefono);
        nuevo_pedido.setMetodoPago(metodoPago);
        nuevo_pedido.setEmpleado(empleado);
        nuevo_pedido.setEstadoPedido("Procesando pedido");
        nuevo_pedido.setCostoTotal(mewing.costoTotal());
        
        ctrl_logica.crearPedido(nuevo_pedido);
        System.out.println("El id del nuevo pedido es " + nuevo_pedido.getIdPedido() + " con la fecha " + nuevo_pedido.getFechaPedido());
        
        List<DetallePedido> listaDetalles = mewing.getListaDetalles();
        for (DetallePedido dtl : listaDetalles){
            dtl.setPedido(nuevo_pedido);
            ctrl_logica.crearDetallePedido(dtl);
        }
        
        listaDetalles.clear();
        mewing.setListaDetalles(listaDetalles);
        String section = "productos";
        session.setAttribute("section", section);
        session.setAttribute("user", mewing);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
