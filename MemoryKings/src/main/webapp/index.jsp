
<%@page import="java.text.ParseException"%>
<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="logica.Mewing"%>
<%@page import="logica.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // El siguiente codigo es util para no redireccionar, o sea pasar 
    // de una pagina a otra, aun sin importar que sea la misma.
    // Lo que hacemos es que en esta misma URL, verificamos
    // si ya hay una sesion o no, para cualquiera de las 2 opciones
    // mostramos los jsp correcpondientes, pero no nos dirigimos a ellos
    
    // Verificar si hay una sesión activa
    Cliente cliente = (Cliente) session.getAttribute("user");
    
    if (cliente != null && cliente.getDni() != null) {
        // si hay una sesión activa, mostramos el jsp de productos
        if (cliente.getCorreo().equals("admin@gmail.com") && BCrypt.checkpw("123", cliente.getPassword())) { 
            %>
                <jsp:include page="menu_admin.jsp" />
            <%          
        }     
        else {
            Mewing mewing = new Mewing(cliente);
            session.setAttribute("mewing", mewing);
            %>
                <jsp:include page="productos.jsp" />
            <%           
        }    
    } 
    else {
        // si no hay una sesión activa, incluir el contenido del JSP de inicio de sesión
        %>
            <jsp:include page="login.jsp" />
        <%
    }
%>
