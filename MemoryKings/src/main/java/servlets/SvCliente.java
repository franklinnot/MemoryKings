
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
        
        listaClientes = ctrl_logica.traerClientes();
        
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaClientes", listaClientes);
        
        response.sendRedirect("mostrar_clientes.jsp");
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Recibimos los datos tras hacer la solicitud POST
        // y las validamos antes de pasarlos a la logica
        
        int dni = Integer.getInteger(request.getParameter("txt_dni"));  
        String name = request.getParameter("txt_name");
        String lastname = request.getParameter("txt_lastname");
        long phone_number = Long.getLong(request.getParameter("txt_phonenumber"));
        String gender = request.getParameter("txt_gender");
        String address = request.getParameter("txt_address");
        String date_birth = request.getParameter("txt_date");
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        
        
        // prueba de consolita xdddddddd
        System.out.println("El email es: " + email);
        System.out.println("La contraseña es: " + password);
        
        Cliente cliente = new Cliente(dni, name, lastname, email, password, phone_number, address, gender, date_birth);
        
        ctrl_logica.crearCliente(cliente);
        
        // aqui, en vez de index, se debe colocar el nombre del jsp que cargara los productos
        response.sendRedirect("productos.jsp");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
