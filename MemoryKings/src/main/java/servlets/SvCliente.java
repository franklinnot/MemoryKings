
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.ControladoraLogica;


@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente"})
public class SvCliente extends HttpServlet {

    ControladoraLogica ctrl_logica = new ControladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(57408026, "Tom", "Want", "tom@gmail.com", "123", 914252402, "Bunker", "Masculino", new Date()));
        listaClientes.add(new Cliente(75048062, "Franklin", "Not", "franklinot@outlook.com", "remy", 941225240, "L.A.", "Masculino", new Date()));
        listaClientes.add(new Cliente(85213697, "Ramiro", "Stom", "dont@outlook.com", "stock", 996284126, "Madrid", "Masculino", new Date()));
        
        
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaClientes", listaClientes);
        
        response.sendRedirect("mostrar_usuarios.jsp");
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        
        
        // prueba de consolita xdddddddd
        System.out.println("El email es: " + email);
        System.out.println("La contraseña es: " + password);
        
        Cliente cliente = new Cliente(75048062, "Franklin", "Not", email, password, 941225240, "L.A.", "Masculino", new Date());
        
        ctrl_logica.crearUsuario(cliente);
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
