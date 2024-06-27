
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Distribuidor;
import logica.Proveedor;

@WebServlet(name = "SvEmpresas", urlPatterns = {"/SvEmpresas"})
public class SvEmpresas extends HttpServlet {

    ControladoraLogica ctrl_logica = new ControladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // metodo usado para registar empresas
        
        // con esta variable comprobamos si es un Distribuidor o un Proveedor
        String tipo = request.getParameter("tipo");
        
        String ruc = request.getParameter("ruc");
        String nombre_comercial = request.getParameter("nombre_comercial");
        String telefono = request.getParameter("telefono");
        String razon_social = request.getParameter("razon_social");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        
        if(tipo.equals("distribuidor")) {
            Distribuidor d = new Distribuidor();
            d.setRuc(ruc);
            d.setNombreComercial(nombre_comercial);
            d.setTelefono(telefono);
            d.setRazonSocial(razon_social);
            d.setDireccion(direccion);
            d.setCorreo(correo);
            
            ctrl_logica.crearDistribuidor(d);
        }
        else if(tipo.equals("proveedor")) {
            Proveedor p = new Proveedor();
            p.setRuc(ruc);
            p.setNombreComercial(nombre_comercial);
            p.setTelefono(telefono);
            p.setRazonSocial(razon_social);
            p.setDireccion(direccion);
            p.setCorreo(correo);
            
            ctrl_logica.crearProveedor(p);
        }
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
