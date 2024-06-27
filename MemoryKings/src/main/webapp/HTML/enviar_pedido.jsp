
<%@page import="logica.Distribuidor"%>
<%@page import="logica.Mewing"%>
<%@page import="logica.Producto"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.DetallePedido"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TimeZone"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%   
    List<DetallePedido> listaDetalles = (List<DetallePedido>) session.getAttribute("listaDetalles");
    listaDetalles.sort((p1, p2) -> p2.getProducto().getNombre().compareTo(p2.getProducto().getNombre()));
    
    Pedido pedido = listaDetalles.get(0).getPedido();
    Cliente cliente = pedido.getCliente();
    
    TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");         
    int desplazamientoPeru = zonaHorariaPeru.getRawOffset();   
    SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'a las' hh:mm a", new java.util.Locale("es", "ES"));
    sdfFecha.setTimeZone(zonaHorariaPeru);
    
    Date dt = pedido.getFechaPedido();
    Date fechaPedido = new Date(dt.getTime() - desplazamientoPeru);
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
    <!-- Modal -->
    <div id="detalleModal" class="">
        <div class="modal-content">
            <h2>Detalle del Pedido - <span id="modal-nro-pedido"></span></h2>
            <div class="modal-line"></div>
            <h3>Datos del Cliente</h3>
            <div class="modal-line"></div>
            <form action="SvEnvios" method="POST">
                <div class="modal-body">
                    <div class="cliente-info">
                        <div class="info-item">
                            <p><strong>DNI:</strong>  <%=cliente.getDni()%></p>
                            <p id="modal-dni"></p>
                        </div>
                        <div class="info-item">
                            <p><strong>Nombres Completos:</strong>  <%=cliente.getNombres()%>  <%=cliente.getApellidos()%></p>
                            <p id="modal-nombre"></p>
                        </div>
                        <div class="info-item">
                            <p><strong>Número de Teléfono:</strong>  <%=cliente.getTelefono()%></p>
                            <p id="modal-telefono"></p>
                        </div>
                        <div class="info-item">
                            <p><strong>Fecha del Pedido:</strong>  <%=sdfFecha.format(fechaPedido)%></p>
                            <p id="modal-fecha"></p>
                        </div>
                        <div class="info-item">
                            <p><strong>Correo Electrónico:</strong>  <%=cliente.getCorreo()%></p>
                            <p id="modal-correo"></p>
                        </div>
                        <div class="info-item">
                            <p><strong>Dirección:</strong>  <%=cliente.getDireccion()%></p>
                            <p id="modal-direccion"></p>
                        </div>
                    </div>
                    <div class="modal-line"></div>
                    <h3>Productos</h3>
                    <div class="modal-line"></div>
                    <div class="producto-info">
                        <table>
                            <%
                                for (DetallePedido dtl : listaDetalles){
                                    Producto pr = dtl.getProducto();
                            %>
                            <thead>

                                <tr>
                                            <th><%=pr.getMarca()%> <%=pr.getNombre()%></th>
                                            <th>S/. <%=pr.getPrecio()%></th>
                                </tr>
                            </thead>
                            <%
                                }
                            %>
                            <tbody id="modal-productos"></tbody>
                        </table>
                        <%
                            float monto_total = Mewing.costoTotal(listaDetalles);
                            float igv = (float) (monto_total * 0.18);
                            float sub_total = monto_total - igv;

                            igv = Math.round(igv * 10.0f) / 10.0f;
                            sub_total = Math.round(sub_total * 10.0f) / 10.0f;
                            monto_total = Math.round(monto_total * 10.0f) / 10.0f;
                        %>
                        <p><strong>SubTotal:</strong> <span id="modal-total"></span>  S/. <%=sub_total%></p>
                        <p><strong>IGV(18%):</strong> <span id="modal-total"></span>  S/. <%=igv%></p>
                        <p><strong>Total:</strong> <span id="modal-total"></span>  S/. <%=monto_total%></p>
                    </div>
                    <div class="modal-line"></div>
                    <h3>Distribuidor/Repartidor</h3>
                    <div class="modal-line"></div>
                    <select id="modal-repartidor" name="distribuidor" required>
                        <option value="" disabled selected>Seleccionar Repartidor</option>
                        <%
                        List<Distribuidor> listaDistribuidores = ctrl_logica.traerDistribuidores();
                        for (Distribuidor ds : listaDistribuidores){
                        %>
                            <option value="<%=ds.getIdDistribuidor()%>"><%=ds.getNombreComercial()%></option>
                        <%
                        }
                        %>
                    </select>
                    <div class="modal-footer">
                            <input type="hidden" name="idPedido" value="<%=pedido.getIdPedido()%>">
                            <button class="enviar-btn" onclick="enviarPedido()">Enviar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</body>
</html>



