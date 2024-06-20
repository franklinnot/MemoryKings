
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Empleado"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.ControladoraLogica"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%
    TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");         
    int desplazamientoPeru = zonaHorariaPeru.getRawOffset();      
    SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'a las' hh:mm a", new java.util.Locale("es", "ES"));
    sdfFecha.setTimeZone(zonaHorariaPeru);
    
    List<Pedido> listaPedidos = (List<Pedido>) session.getAttribute("listaPedidos");
    listaPedidos.sort((p1, p2) -> p2.getFechaPedido().compareTo(p1.getFechaPedido()));
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atención de Pedidos</title>
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/atencionPedido.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
    <header>
        <a href="" class="logo">
            <img src="Image/logo.png" alt="logo empresa">
        </a>
        <form action="SvMenu" method="GET" style="position: absolute; left: 1%; top: 30px;">
            <button type="submit"><span>Página principal</span></button>
        </form>
    </header>
        <main>
            <div class="titulo-container">
                <h1>Atención de Pedidos</h1>
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
                            <th>Nro Pedido</th>
                            <th>DNI</th>
                            <th>Nombres Completos</th>
                            <th>Fecha del Pedido</th>
                            <th>Dirección de Envío</th>
                            <th>Estado</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%        
                            for (Pedido pd : listaPedidos){
                                Cliente cl = pd.getCliente();
                                Date dt = pd.getFechaPedido();
                                Date fechaPedido = new Date(dt.getTime() - desplazamientoPeru);
                        %>              
                        <tr>
                            <td>000<%=pd.getIdPedido()%></td>
                            <td><%=cl.getDni()%></td>
                            <td><%=cl.getNombres()%> <%=cl.getApellidos()%></td>
                            <td><%=sdfFecha.format(fechaPedido)%></td>
                            <td><%=pd.getDireccion()%></td>
                            <td><span class="estado atendido"  
                                      <%
                                        if(!pd.getEstadoPedido().equals("Enviado")){
                                      %> style="background-color: gray" <%}%>>
                                      <%=pd.getEstadoPedido()%></span></td>
                            <td>
                                <%
                                if (!pd.getEstadoPedido().equals("Enviado")){
                                %>
                                <form action="SvEnvios" method="GET">
                                    <input type="hidden" name="idPedido" value="<%=pd.getIdPedido()%>">
                                    <button type="submit" class="atender-btn" onclick="openModal('001')">Atender</button>
                                </form>          
                                <%}%>
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

    <script src="JavaScript/atencionPedido.js"></script>
    
</body>
</html>


