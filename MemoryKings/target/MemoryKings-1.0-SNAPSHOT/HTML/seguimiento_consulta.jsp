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
    Consulta cn = (Consulta) session.getAttribute("consulta");
    Cliente cl = cn.getCliente();
    Date fechaConsulta = cn.getFechaRegistro();
    Date fechaAtencion = cn.getFechaRespuesta();
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
        <header>
            <form action="SvSession" method="GET" style="position: absolute; left: 1%; top: 30px;">
                <input type="hidden" name="cancelarProceso" value="consultas">
                <button type="submit"><span>Ir atrás</span></button>
            </form>
        </header>
        <main style="padding: 20px;width: 70%;display: grid;">
            <div class="tabla-container" style="margin-bottom: 40px;">
                <table>
                    <thead>
                        <tr>
                            <th>Fecha de la Consulta</th>
                            <th>Tipo de consulta</th>
                            <th>Fecha de atención</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%=sdfFecha.format(fechaConsulta)%></td>
                            <td><%=cn.getTipoConsulta()%></td>
                            <td><%=sdfFecha.format(fechaAtencion)%></td>
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
            <div class="modal-content">
                <span class="close-btn">&times;</span>
                <h2>Respuesta</h2>
                <textarea disabled><%=cn.getRespuesta()%></textarea>
            </div>
        </main>
    </body>

</html>
