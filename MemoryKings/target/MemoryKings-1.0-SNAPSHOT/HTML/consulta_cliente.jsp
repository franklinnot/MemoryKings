

<%@page import="logica.Mewing"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TimeZone"%>
<%@page import="logica.Consulta"%>
<%@page import="java.util.List"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%
    List<Consulta> listaConsultas = (List<Consulta>) session.getAttribute("listaConsultas");
    listaConsultas.sort((p1, p2) -> p2.getFechaRegistro().compareTo(p1.getFechaRegistro())); 
    Mewing mw = (Mewing) session.getAttribute("user");
    Cliente cl = mw.getCliente();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="stylesheet" href="CSS/normalize.css">
        <link rel="stylesheet" href="CSS/consulta_cliente.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consultas</title>
    </head>
    <body>
        <div class="div_principal"> 
            <div class="logo_container">
                <img id="loguito" src="Image/logo.png" alt="Logo">
            </div>
            <form action="SvConsultas" method="POST" id="consultaForm"  style="min-width: -webkit-fill-available;display: contents;"> 
                <div class="div_division1">
                    <div class="div_inputs">
                        <label id="dnilbl"><b>DNI:</b> <%=cl.getDni()%></label>
                        <label id="nomlbl"><b>Nombres:</b> <%=cl.getNombres()%></label>
                        <label id="apelbl"><b>Apellidos:</b> <%=cl.getApellidos()%></label>
                        <label id="colbl"><b>Correo:</b> <%=cl.getCorreo()%></label>
                    </div>
                    <div class="div_combobox">
                        <label id="escoger" for="cbtiposervicio">Escoge el tipo de servicio: </label>
                        <select id="cbtiposervicio" name="tipo_consulta" required>
                            <option value="0" selected disabled>Selecciona el tipo de consulta</option>
                            <option value="Disponibilidad de productos">Disponibilidad de productos</option>
                            <option value="Precios y ofertas">Precios y ofertas</option>
                            <option value="Detalles técnicos">Detalles técnicos</option>
                            <option value="Proceso de compra">Proceso de compra</option>
                            <option value="Envío y entrega">Envío y entrega</option>
                            <option value="Devoluciones y garantías">Devoluciones y garantías</option>
                            <option value="Soporte postventa">Soporte postventa</option>
                            <option value="Recomendaciones de productos">Recomendaciones de productos</option>
                            <option value="Información sobre promociones y novedades">Información sobre promociones y novedades</option>
                            <option value="Problemas con la web o la cuenta">Problemas con la web o la cuenta</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </div>
                </div>
                <div class="div_textarea">
                    <label id="lbldescripcion" for="descripcion"><b>Descripción de su consulta:</b></label>
                    <textarea id="descripcion"  name="descripcion" placeholder="Describe tu problema" required></textarea>
                </div>
                <div class="div_botones">
                    <button id="btenviar" type="submit">ENVIAR CONSULTA</button>   
                </div>  
            </form>

            <form action="SvSession" method="GET" style="position: absolute; left: 10px;">
                <input type="hidden" name="cancelarProceso" value="productos">
                <button id="btregresar" type="submit">REGRESAR</button>
            </form>

            <div id="consultas-container">
                <%
                    TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");
                    int desplazamientoPeru = zonaHorariaPeru.getRawOffset();
                    SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'a las' hh:mm a", new java.util.Locale("es", "ES"));
                    sdfFecha.setTimeZone(zonaHorariaPeru);

                    for (Consulta cn : listaConsultas) {
                        Date dt = cn.getFechaRegistro();
                        Date fechaPedido = new Date(dt.getTime() - desplazamientoPeru);
                %>
                <div class="consulta">
                    <h2>CAS-A-000<%=cn.getIdConsulta()%></h2>
                    <div class="fecha">Fecha de registro: <%=sdfFecha.format(fechaPedido)%></div>
                    <div class="estado"><p style="position: absolute;right: 108%;bottom: 2%;">Estado:</p> <%=cn.getEstadoConsulta()%></div>
                    <h3>Categoria: <%=cn.getTipoConsulta()%></h3>
                    <p>Consulta: <%=cn.getDescripcion()%></p>
                    <%
                        if(cn.getEstadoConsulta().equals("Atendido")){
                    %>
                    <form action="SvSeguimientoConsulta" method="GET">
                        <input type="hidden" name="idConsulta" value="<%=cn.getIdConsulta()%>">
                        <button type="submit" class="boton-seguimiento">SEGUIMIENTO</button>
                    </form>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <script src="/JavaScript/Scripts.jS"></script>
    </body>
</html>
