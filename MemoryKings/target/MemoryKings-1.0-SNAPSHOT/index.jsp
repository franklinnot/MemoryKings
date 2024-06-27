
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
        else if(section.equals("lista_pedidos")){
        %>
            <jsp:include page="HTML/lista_pedidos.jsp"/>
        <%    
        }
        else if(section.equals("consultas")){
        %>
            <jsp:include page="HTML/consulta_cliente.jsp"/>
        <%    
        }
        else if(section.equals("seguimiento")){
        %>
            <jsp:include page="HTML/seguimiento_consulta.jsp"/>
        <%    
        }
        else if (section.equals("pago")){
        %>
        <jsp:include page="HTML/pago.jsp"/>
        <%
        } 


    } else if (userObject instanceof Empleado) {

        String section = (String) session.getAttribute("section");

        if (section.equals("menu")){
        %>
            <jsp:include page="HTML/menu_admin.jsp"/>
        <%
        }
        else if (section.equals("pedidos")){
        %>
            <jsp:include page="HTML/atencion_pedido.jsp"/>
        <%
        }
        else if (section.equals("consultas")){
        %>
            <jsp:include page="HTML/atencion_consulta.jsp"/>
        <%
        }
        else if (section.equals("responder_consulta")){
        %>
            <jsp:include page="HTML/respuesta_consulta.jsp"/>
        <%
        }
        else if(section.equals("carrito")){
        %>
            <jsp:include page="HTML/detalle_compra.jsp"/>
        <%    
        }
        else if(section.equals("clientes")){
        %>
            <jsp:include page="HTML/clientes.jsp"/>
        <%    
        }
        else if (section.equals("pago")){
        %>
        <jsp:include page="HTML/pago.jsp"/>
        <%
        }
        else if(section.equals("envio")){
        %>
            <jsp:include page="HTML/enviar_pedido.jsp"/>
        <%    
        }
            
    } else {
        %>
            <jsp:include page="HTML/login.jsp" />
        <%
    }
%>
