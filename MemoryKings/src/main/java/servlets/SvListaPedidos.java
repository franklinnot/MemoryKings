
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
import logica.ControladoraLogica;
import logica.Mewing;
import logica.Pedido;

@WebServlet(name = "SvListaPedidos", urlPatterns = {"/SvListaPedidos"})
public class SvListaPedidos extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        Mewing mw = (Mewing) session.getAttribute("user");
        Cliente cl = mw.getCliente();
        int id = cl.getIdCliente();
        
        List<Pedido> listaPedidos = new ArrayList<>();
        List<Pedido> lista = ctrl_logica.traerPedidos();
        
        for(Pedido pd : lista){
            if(pd.getCliente().getIdCliente() == id){
                listaPedidos.add(pd);
            }
        }
        
        String section = "lista_pedidos";
        session.setAttribute("section", section);
        session.setAttribute("listaPedidos", listaPedidos);
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
