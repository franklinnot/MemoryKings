
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
import logica.Mewing;
import logica.Pedido;
import logica.Producto;

@WebServlet(name = "SvDetalleCompra", urlPatterns = {"/SvDetalleCompra"})
public class SvDetalleCompra extends HttpServlet {
    
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
        // esto se activa cuando el cliente elimina un producto de su carrito
        
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        
        HttpSession session = request.getSession();
        
        Mewing carrito = new Mewing();
        carrito = (Mewing) session.getAttribute("user");
        
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = ctrl_logica.traerProductos();
        Producto producto = new Producto();
        producto = Producto.obtenerProducto(listaProductos, idProducto);
        carrito.removeProducto(producto);
         
        if(carrito.getListaDetalles().isEmpty() || carrito.getListaDetalles() == null){
            String section = "productos";
            session.setAttribute("section", section);
        }
        
        session.setAttribute("user", carrito);  
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // esto se activa cuando el cliente quiere dirigirse a la seccion pago
        HttpSession session = request.getSession();
        
        Mewing carrito = (Mewing) session.getAttribute("user");
        List<DetallePedido> listaDetalles = carrito.getListaDetalles();
          
        Pedido nuevo_pedido = new Pedido();
        nuevo_pedido.setCliente(carrito.getCliente());
        float costo_total = 0;
        for (DetallePedido detalle : listaDetalles){
            costo_total += detalle.getCantidad() * detalle.getProducto().getPrecio();
        }
        nuevo_pedido.setCostoTotal(costo_total);
        session.setAttribute("pedido", nuevo_pedido);
        String section = "pago";
        session.setAttribute("section", section);
        
        response.sendRedirect("http://localhost:8080/MemoryKings/");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
