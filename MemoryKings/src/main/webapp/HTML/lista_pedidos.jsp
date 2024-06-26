
<%@page import="java.util.Base64"%>
<%@page import="logica.ImagenProducto"%>
<%@page import="logica.Producto"%>
<%@page import="logica.DetallePedido"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TimeZone"%>
<%@page import="logica.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista de Pedidos | MemoryKings</title>
    <link rel="stylesheet" href="CSS/lista_pedidos.css" />
    <link rel="stylesheet" href="CSS/normalize.css" />
  </head>

  <body>
    <aside class="sidebar" style="width: 12vw;">
      <header>Mi Cuenta</header>
      <form action="SvSession" method="GET">
        <input type="hidden" name="cancelarProceso" value="productos">
        <button style="background-color: transparent;border: none;">Productos</button>
      </form>

      <form action="SvSession" method="POST">
        <button style="background-color: transparent;border: none;">Cerrar sesión</button>
      </form>
    </aside>
    <main>
      <div class="elements">
        <p class="titulo">Compras</p>
        <form class="form_e" action="">
          <input type="text" />
          <button class="btn_buscar">Buscar</button>
        </form>
        <form>
          <button class="btn_limpiar">Limpiar</button>
        </form>
      </div>
      <div class="list_products">
          
        <%
            for (Pedido pd : listaPedidos){
                Date dt = pd.getFechaPedido();
                Date fechaPedido = new Date(dt.getTime() - desplazamientoPeru);
                List<DetallePedido> detalles = DetallePedido.obtenerDetalle(pd.getIdPedido());
                Producto pr = detalles.get(0).getProducto();
                ImagenProducto img = ImagenProducto.obtenerImagen(pr.getIdProducto());
                byte[] imagenBytes = img.getImagen();
                String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
        %>
        <div class="producto">
          <p class="fecha_pedido"><%=sdfFecha.format(fechaPedido)%></p>
          <img class="imagen_p" src="data:image/jpeg;base64,<%=base64Image%>" alt="Imagen del Producto";"/>
          <div class="descripcion_p">
              <p><b>Estado de pedido:</b> <%=pd.getEstadoPedido()%></p>
            <p><b>Dirección:</b> <%=pd.getDireccion()%></p>
            <p><b>Método de pago:</b> <%=pd.getMetodoPago()%></p>
            <p><b>Costo total:</b> S/. <%=pd.getCostoTotal()%></p>
          </div>
        </div>
        <%
            }
        %>
        
      </div>
    </main>
  </body>
</html>
