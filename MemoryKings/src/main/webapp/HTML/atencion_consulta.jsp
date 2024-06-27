
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
    List<Consulta> listaConsultas = (List<Consulta>) session.getAttribute("listaConsultas");
    listaConsultas.sort((p1, p2) -> p2.getFechaRegistro().compareTo(p1.getFechaRegistro())); 
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atención de Pedidos</title>
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/atencion_consulta.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
    <header>
        <a href="#" class="logo">
            <img src="Image/logo.png" alt="logo empresa">
        </a>
        <form action="SvSession" method="GET" style="position: absolute; left: 1%; top: 30px;">
            <input type="hidden" name="cancelarProceso" value="menu">
            <button type="submit"><span>Página principal</span></button>
        </form>
    </header>
    <main>
        <div class="titulo-container">
            <h1>Atender Consultas</h1>
            <div class="subrayado"></div>
        </div>
        <div class="busqueda-container">
            <input type="text" placeholder="Buscar...">
            <button id="botonBusqueda"><i class='bx bx-search'></i></button>
        </div>
        <div class="tabla-container">
            <table>
                <thead>
                    <tr>
                        <th>Nro Consulta</th>
                        <th>DNI</th>
                        <th>Nombres Completos</th>
                        <th>Fecha de la Consulta</th>
                        <th>Tipo de consulta</th>
                        <th>Estado</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <%        
                        for (Consulta cn : listaConsultas){
                            Cliente cl = cn.getCliente();
                            Date dt = cn.getFechaRegistro();
                            Date fechaConsulta = new Date(dt.getTime() - desplazamientoPeru);
                    %> 
                    
                    <!-- Iterar aqui -->
                    <tr>
                        <td>000<%=cn.getIdConsulta()%></td>
                        <td><%=cl.getDni()%></td>
                        <td><%=cl.getNombres()%> <%=cl.getApellidos()%></td>
                        <td><%=sdfFecha.format(fechaConsulta)%></td>
                        <td><%=cn.getTipoConsulta()%></td>
                        <td><span class="estado atendido"
                                  <%
                                        if(!cn.getEstadoConsulta().equals("Atendido")){
                                  %> 
                                  style="background-color: gray"
                                  <%
                                      }
                                  %>
                                  >
                                  <%=cn.getEstadoConsulta()
                                  %>
                            </span></td>
                        <td>
                            <%
                                if (!cn.getEstadoConsulta().equals("Atendido")) {
                            %>
                                <form action="SvRespuestaConsulta" method="GET">
                                    <input type="hidden" name="idConsulta" value="<%=cn.getIdConsulta()%>">
                                    <button type="submit" class="atender-btn">Atender</button>
                                </form>          
                            <%
                                }
                            %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </main>
    <footer>
        <div class="linea-inferior"></div>
    </footer>
</body>
</html>
