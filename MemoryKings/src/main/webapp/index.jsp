
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // El siguiente codigo es util para no redireccionar, o sea pasar 
    // de una pagina a otra, aun sin importar que sea la misma.
    // Lo que hacemos es que en esta misma URL, verificamos
    // si ya hay una sesion o no, para cualquiera de las 2 opciones
    // mostramos los jsp correcpondientes, pero no nos dirigimos a ellos
    
    // Verificar si hay una sesión activa
    HttpSession bool_session = request.getSession(false);
    if (bool_session != null && bool_session.getAttribute("datos_usuario") != null) {
        // si hay una sesión activa, mostramos el jsp de productos
        %>
            <jsp:include page="productos.jsp" />
        <%
    } else {
        // si no hay una sesión activa, incluir el contenido del JSP de inicio de sesión
        %>
            <jsp:include page="login.jsp" />
        <%
    }
%>
