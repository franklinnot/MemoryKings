<%-- 
    Document   : mostrar_usuarios
    Created on : May 3, 2024, 9:21:12 PM
    Author     : frank
--%>

<%@page import="java.util.List"%>
<%@page import="logica.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar usuarios</title>
    </head>
    <body>
        <h1>Lista de usuarios registrados</h1>
        
        <%
            List<Cliente> listaClientes = (List) request.getSession().getAttribute("listaClientes");
            int cont = 1;
            for(Cliente cliente : listaClientes){
        %>
        
        <p><b>Cliente numero <%=cont%>: </b></p>
        <p>DNI: <%=cliente.getDni()%></p>
        <p>Nombre completo: <%=cliente.getNombres()%><%=cliente.getApellidos()%></p>
        <p>Correo: <%=cliente.getCorreo()%></p>
        <p>Telefono: <%=cliente.getTelefono()%></p>
        <p>Direccion: <%=cliente.getDireccion()%></p>
        <p>Genero: <%=cliente.getGenero()%></p>
        <p>Fecha de Nacimiento: <%=cliente.getFechaNacimiento()%></p>
        <p>-------------------------------------------------------</p>
        <%
            cont += 1;
            }
        %>
        
    </body>
</html>
