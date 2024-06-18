
<%@page import="java.util.Date"%>
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

    if (userObject instanceof Mewing) {
    
        String section = (String) session.getAttribute("section");
        
        if (section.equals("productos")){
        %>
            <jsp:include page="HTML/productos.jsp"/>
        <%
        }
        else if(section.equals("carrito")){
        %>
            <jsp:include page="HTML/detalle_compra.jsp"/>
        <%    
        }
        else if (section.equals("pago")){
        %>
        <jsp:include page="HTML/pago.jsp"/>
        <%
        } 


    } else if (userObject instanceof Empleado) {
        %>
            <jsp:include page="HTML/menu_admin.jsp" />
        <%
            
    } else {
        %>
            <jsp:include page="HTML/login.jsp" />
        <%
    }
%>
