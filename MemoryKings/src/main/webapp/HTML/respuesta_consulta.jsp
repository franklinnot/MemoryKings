
<%@page import="java.util.Date"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.Consulta"%>
<%@page import="logica.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="logica.Empleado"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TimeZone"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");         
    int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
    SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'a las' hh:mm a", new java.util.Locale("es", "ES"));
    sdfFecha.setTimeZone(zonaHorariaPeru);
    Empleado empleado = (Empleado) session.getAttribute("user");
    Consulta cn = (Consulta) session.getAttribute("consulta");
    Cliente cl = cn.getCliente();
    Date fechaConsulta = cn.getFechaRegistro();
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/atencion_consulta.css">
    <title>Document</title>
</head>

<body style="display: flex;min-height: 100vh;align-items: center;justify-content: center;">
    <main style="padding: 20px;width: 70%;display: grid;">
        <div class="tabla-container" style="margin-bottom: 40px;">
            <table>
                <thead>
                    <tr>
                        <th>Nro Consulta</th>
                        <th>DNI</th>
                        <th>Nombres Completos</th>
                        <th>Fecha de la Consulta</th>
                        <th>Tipo de consulta</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>000<%=cn.getIdConsulta()%></td>
                        <td><%=cl.getDni()%></td>
                        <td><%=cl.getNombres()%> <%=cl.getApellidos()%></td>
                        <td><%=sdfFecha.format(fechaConsulta)%></td>
                        <td><%=cn.getTipoConsulta()%></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="modal" class="">
            <div class="modal-content">
                <span class="close-btn">&times;</span>
                <h2>Consulta</h2>
                <textarea disabled><%=cn.getDescripcion()%></textarea>
            </div>
        </div>
        <form action="SvRespuestaConsulta" method="POST">
            <div class="modal-content">
                <span class="close-btn">&times;</span>
                <h2>Respuesta</h2>
                <textarea name="rpta" placeholder="Escribe aquí tu respuesta..." required></textarea>
                <button type="submit" class="enviar-btn">Enviar</button>
            </div>
        </form>
    </main>
</body>

</html>
