
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import logica.Empleado;
import logica.Mewing;
import logica.Producto;
import org.mindrot.jbcrypt.BCrypt;


@WebServlet(name = "SvRegistroEmpleados", urlPatterns = {"/SvRegistroEmpleados"})
public class SvRegistroEmpleados extends HttpServlet {

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
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String dni = request.getParameter("empleado_dni");      
        String cargo = request.getParameter("empleado_cargo"); 
        String nombres = request.getParameter("empleado_nombres");       
        String apellidos = request.getParameter("empleado_apellidos");       
        String telefono = request.getParameter("empleado_telefono");              
        String fechaNacimiento = request.getParameter("empleado_fecha_nacimiento");
        String genero = request.getParameter("empleado_genero");
        String direccion = request.getParameter("empleado_direccion");
        String correo = request.getParameter("empleado_correo");          
        String password = request.getParameter("empleado_password");
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date_birth = new Date();
        try {
            date_birth = formatter.parse(fechaNacimiento);
        } catch (ParseException ex) {
            Logger.getLogger(SvLogin.class.getName()).log(Level.SEVERE, null, ex);
        }                   

        
        // dni , nombres, apellidos, correo, password, telefono, direccion, genero, fechaNacimiento 
        Empleado empleado = new Empleado();
        empleado.setDni(dni);
        empleado.setCargo(cargo);
        empleado.setNombres(nombres);
        empleado.setApellidos(apellidos);
        empleado.setTelefono(telefono);
        empleado.setFechaNacimiento(date_birth);
        empleado.setGenero(genero);
        empleado.setDireccion(direccion);
        empleado.setCorreo(correo);
        empleado.setPassword(password);
        
        ctrl_logica.crearEmpleado(empleado);
        
        
        // aqui, en vez de index, se debe colocar el nombre del jsp que cargara los productos
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
