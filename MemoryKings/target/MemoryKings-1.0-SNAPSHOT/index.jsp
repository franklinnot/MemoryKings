<%@page import="java.text.ParseException"%>
<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="logica.Mewing"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    Object userObject = session.getAttribute("user");

    if (userObject instanceof Mewing) {
        Mewing mewing = (Mewing) userObject;
        Cliente cliente = mewing.getCliente();
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
