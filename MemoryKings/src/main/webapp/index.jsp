
<%@page import="logica.ControladoraLogica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="logica.Proveedor"%>
<%@page import="logica.Categoria"%>
<%@page import="java.text.ParseException"%>
<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="logica.Mewing"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%
    
    Object userObject = session.getAttribute("user");
    // aqui basicamente se comprueba quien inicio sesión: CLiente o Empleado
    // posteriormente la cuenta de Admin irá en la tabla de Empleado
    // y su privilegio se 
    if (userObject instanceof Mewing) {
        Mewing mewing = (Mewing) userObject;
        Cliente cliente = mewing.getCliente();
        //esto debe ir en la parte de la instancia de Empleado
        // y la verificacion de que cuenta es, debe ir en el jsp de menu_admin
        // así, deshabilitamos productos, empleados, clientes
        if (cliente.getCorreo().equals("admin@gmail.com") && BCrypt.checkpw("123", cliente.getPassword())) {
            %>
                <jsp:include page="menu_admin.jsp" />
            <%
        } else {
            %>
                <jsp:include page="productos.jsp" />
            <%
        }
    } else if (userObject instanceof Empleado) {
        %>
            <jsp:include page="menu_admin.jsp" />
        <%
    } else {
        %>
            <jsp:include page="login.jsp" />
        <%
    }

%>
