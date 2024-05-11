
package servlets;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        
        String email = request.getParameter("txt_email");
        System.out.println("Correo: " + email);
        String password = request.getParameter("txt_password");
        System.out.println("Contraseña: " + password);

        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes = ctrl_logica.traerClientes();

        boolean usuarioEncontrado = false;
        for(Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equals(email) && BCrypt.checkpw(password, cliente.getPassword())) {
                usuarioEncontrado = true;
                break;
            }
        }

        if (usuarioEncontrado) {
            System.out.println("Eureka!");
            response.sendRedirect("productos.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Recibimos los datos tras hacer la solicitud POST
        // y las validamos antes de pasarlos a la logica   
        
        // probamos si los datos llegan con la consola
        int dni = Integer.parseInt(request.getParameter("txt_dni"));  
        System.out.println("Dni: " + dni);
        
        String name = request.getParameter("txt_name");
        System.out.println("Nombre: " + name);
        
        String lastname = request.getParameter("txt_lastname");
        System.out.println("Apellido: " + lastname);
        
        long phone_number = Long.parseLong(request.getParameter("txt_phonenumber"));
        System.out.println("Telefono: " + phone_number);
        
        String txt_date = request.getParameter("txt_date");
        System.out.println("F. Nacimiento: " + txt_date);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date_birth = new Date();
        try {
            date_birth = formatter.parse(txt_date);
        } catch (ParseException ex) {
            Logger.getLogger(SvCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        String gender = request.getParameter("txt_gender");
        System.out.println("Genero: " + gender);
        
        String address = request.getParameter("txt_address");
        System.out.println("Direccion: " + address);
        
        String email = request.getParameter("txt_email");
        System.out.println("Correo: " + email);
        
        String password = request.getParameter("txt_password");
        System.out.println("Contraseña: " + password);
        
        
        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
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
