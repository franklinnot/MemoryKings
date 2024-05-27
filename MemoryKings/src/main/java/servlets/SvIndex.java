
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


@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {

    ControladoraLogica ctrl_logica = new ControladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");

        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes = ctrl_logica.traerClientes();
        
        // si el usuario existe, guardaremos su info aqui
        Cliente datos_usuario = new Cliente(); 
        
        boolean usuarioEncontrado = false;
        for(Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equals(email) && BCrypt.checkpw(password, cliente.getPassword())) {
                usuarioEncontrado = true;
                datos_usuario = cliente;
                break;
            }
        }
        
        if (usuarioEncontrado) {
            System.out.println("Eureka! xd");
                                
            HttpSession session = request.getSession();
            session.setAttribute("datos_usuario", datos_usuario);
            
        } 
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Recibimos los datos tras hacer la solicitud POST
        // y las validamos antes de pasarlos a la logica   
        
        String dni = request.getParameter("txt_dni");          
        String name = request.getParameter("txt_name");       
        String lastname = request.getParameter("txt_lastname");       
        String phone_number = request.getParameter("txt_phonenumber");              
        String txt_date = request.getParameter("txt_date");
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date_birth = new Date();
        try {
            date_birth = formatter.parse(txt_date);
        } catch (ParseException ex) {
            Logger.getLogger(SvIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        String gender = request.getParameter("txt_gender");       
        String address = request.getParameter("txt_address");      
        String email = request.getParameter("txt_email");      
        String password = request.getParameter("txt_password");

        
        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
        Cliente cliente = new Cliente(dni, name, lastname, email, password, phone_number, address, gender, date_birth);
        ctrl_logica.crearCliente(cliente);
        
        Cliente datos_usuario = new Cliente();
        datos_usuario = cliente;
            
        HttpSession session = request.getSession(false);
        session.setAttribute("datos_usuario", datos_usuario);
        
        // aqui, en vez de index, se debe colocar el nombre del jsp que cargara los productos
        response.sendRedirect("http://localhost:8080/MemoryKings/");
         
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
