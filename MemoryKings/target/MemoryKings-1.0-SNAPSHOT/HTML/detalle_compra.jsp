
<%@page import="java.util.Base64"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="logica.ImagenProducto"%>
<%@page import="logica.Mewing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="logica.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/detalle_compra.css">
</head>
<body>

    <div class="grid_principal">
        <div class="grid_cabecera">
            <div class="imagen_cabecera"><img class="imagen" src="Image/logo.png" alt=""></div>
            <div class="text_cabecera"><p>Carro de Compra</p></div>
            <div class="grid_lista">
                <div class="grid_producto">
                    
                    <%
                        Mewing mewing = (Mewing) session.getAttribute("user");
                        float monto_total = 0;
                        float sub_total = 0;
                        float igv = 0;
                        if (mewing != null && mewing.getCliente() != null && !mewing.getListaProducto().isEmpty()){
                        
                            List<Producto> listaProductos = new ArrayList<>();
                            listaProductos = mewing.getListaProducto();
                            List<ImagenProducto> listaImagenes = new ArrayList<>();
                            listaImagenes = ctrl_logica.traerImagenProductos();
                            for (Producto producto : listaProductos){
                    %>
                                <div class="producto">
                                    <%
                                        ImagenProducto imagen = new ImagenProducto();
                                        imagen = ImagenProducto.obtenerImagen(listaImagenes, producto.getIdProducto());
                                        byte[] imagenBytes = imagen.getImagen();
                                        String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
                                    %>
                                    <img class="imagen_p" src="data:image/jpeg;base64,<%=base64Image%>" alt="Imagen del Producto";">
                                    <div class="descripcion_p">
                                        <p class="a1">Nombre:</p>
                                        <p class="nombre_p"><%=producto.getNombre()%></p>
                                        <p class="a2">Precio:</p>
                                        <p class="precio_p">S/.<%=producto.getPrecio()%></p>
                                    </div>
                                    <form action="SvDetalleCompra" action="GET">
                                        <input type="hidden" name="idProducto" value="<%=producto.getIdProducto()%>">
                                        <button class="btn_borrar" type="submit"><img src="Image/icon_basura.png" alt=""></button>
                                    </form>
                                </div>
                    <%
                                monto_total += producto.getPrecio();
                            }
                        }
                        igv = (float) (monto_total * 0.18);
                        sub_total = monto_total - igv;
                    %>
                    
                </div>
            </div>
        </div>
        <div class="grid_resumen">
            <div class="resumen"><p>Resumen</p></div>
            <div class="subtotal_resumen"><p class="p_subtotal">SubTotal:</p></div>
            <div class="precio_subtotal"><p>$0.00</p></div>
            <div class="subtotal_resumen"><p class="p_subtotal">IGV(18%):</p></div>
            <div class="precio_subtotal"><p>$0.00</p></div>
            <div class="total_resumen"><p class="p_total">Total:</p></div>
            <div class="precio_total"><p>$0.00</p></div>
            <button class="btn_continuar">Continuar</button>
            <button class="btn_seguir">Seguir comprando</button>
            
        </div>
    </div>
</body>
</html>
