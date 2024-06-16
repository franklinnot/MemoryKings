
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.ControladoraLogica;
import logica.Empleado;
import logica.Mewing;
import logica.Producto;


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

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
        List<Empleado> listaEmpleados = new ArrayList<>();
        
        listaClientes = ctrl_logica.traerClientes();
        listaEmpleados = ctrl_logica.traerEmpleados();
        
        // si el usuario existe, guardaremos su info aqui
        Cliente user = new Cliente(); 
        
        boolean usuarioEncontrado = false;
        for(Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equals(email) && BCrypt.checkpw(password, cliente.getPassword())) {
                usuarioEncontrado = true;
                user = cliente;
                break;
            }
        }
        
        // si el usuario existe, guardaremos su info aqui
        Empleado empleado = new Empleado(); 
        
        boolean empleadoEncontrado = false;
        for(Empleado emp : listaEmpleados) {
            if (emp.getCorreo().equals(email) && BCrypt.checkpw(password, emp.getPassword())) {
                empleadoEncontrado = true;
                empleado = emp;
                break;
            }
        }
        
        if (usuarioEncontrado) { // esto solo para el cliente jeje obvi
            System.out.println("Eureka! xd");
            HttpSession session = request.getSession();
            Mewing mewing = new Mewing(user);
            session.setAttribute("user", mewing);
            
            List<Producto> listaProductos = new ArrayList<>();
            listaProductos = ctrl_logica.traerProductos();
            
            session.setAttribute("listaProductos", listaProductos);           
            String section = "productos";
            session.setAttribute("section", section);
        }
        else if (empleadoEncontrado){
            System.out.println("yupi yupiii! xd");
            HttpSession session = request.getSession();
            session.setAttribute("user", empleado);
            
        }
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Recibimos los datos tras hacer la solicitud POST
        // y las validamos antes de pasarlos a la logica   
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
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
            Logger.getLogger(SvLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        String gender = request.getParameter("txt_gender");       
        String address = request.getParameter("txt_address");      
        String email = request.getParameter("txt_email");      
        String password = request.getParameter("txt_password");

        
        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
        Cliente cliente = new Cliente(dni, name, lastname, email, password, phone_number, address, gender, date_birth);
        ctrl_logica.crearCliente(cliente);
        
        Mewing user = new Mewing(cliente);
        
        HttpSession session = request.getSession();
        
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = ctrl_logica.traerProductos();

        session.setAttribute("listaProductos", listaProductos);
        
        session.setAttribute("user", user);
        String section = "productos";
        session.setAttribute("section", section);
        
        // aqui, en vez de index, se debe colocar el nombre del jsp que cargara los productos
        response.sendRedirect("http://localhost:8080/MemoryKings/");
         
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
