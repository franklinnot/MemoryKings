
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.Mewing"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/pago.css">
    <link rel="stylesheet" href="CSS/normalize.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    <title>Pago | MemoryKings</title>
</head>
<body>
    <header>
        <div class="juntarlogo">
            <!--BOTON MENU-->
            <div class="btnmenu">
                <button class="menu-btn"><i class='bx bx-menu'></i></button>
            </div>
            <div class="espacio-separacion"></div>
            <!--AÑADIR LOGO-->
            <a href="#" class="logo">
                <img src="Image/logo.png" alt="logo empresa">
                <h2 class="nombre_empresa"></h2>
            </a>
        </div>
       
        
    </header>
    <main>
        <%
        Mewing carrito = new Mewing();
        carrito = (Mewing) session.getAttribute("user");
        Cliente cliente = carrito.getCliente();
        %>
        <form id="formPago" action="SvPedidos" method="POST">
            <div class="contenedor">
                <div class="detalle-pedido">
                    <div class="parte parte-detalle">
                        <h2>DETALLE DEL PEDIDO</h2>
                    </div>
                    <div class = "parte parte-cliente">
                        <h3>Datos del cliente</h3>   
                    </div>
                    <div class="parte parte-datacliente">
                        <div class="input-box">
                            <div class="input-field">
                                <p>Nombres</p>
                                <input type="text" placeholder="<%=cliente.getNombres()%>" readonly>
                                <i class='bx bxs-user'></i>
                            </div>
                            <div class="input-field">
                                <p>Apellidos</p>
                                <input type="text" placeholder="<%=cliente.getApellidos()%>" readonly>
                                <i class='bx bxs-user'></i>
                            </div>                                            
                        </div>
                        <div class="input-box">
                            <div class="input-field">
                                <p>Documento de identidad</p>
                                <input type="text" placeholder="<%=cliente.getDni()%>" readonly>
                                <i class='bx bx-id-card'></i>
                            </div>
                            <div class="input-field">
                                <p>Teléfono</p>
                                <input type="text" name="pedido_telefono" value="<%=cliente.getTelefono()%>" required>
                                <i class='bx bxs-phone'></i>
                            </div>                                        
                        </div>
                        <div class="input-box">
                            <div class="input-field">
                                <p>Dirección</p>
                                <input type="text" name="pedido_direccion" value="<%=cliente.getDireccion()%>" required>
                                <i class='bx bxs-home'></i>
                            </div>                               
                        </div>                    
                    </div> 
                    <div class="parte parte-pedido">
                        <h3>Datos del pedido</h3>
                    </div>
                    <div class="parte parte-datapedido">                    
                        <div class="input-box" >
                            <div class="input-field">
                                <p>Cantidad de productos</p>
                                <input id="cantidad_productos" type="text" placeholder="<%=carrito.cantidadProductos()%>" readonly>
                                <i class='bx bx-package'></i>
                            </div>                                        
                        </div>
                        <div class="input-box" id="Fecha">
                            <div class="input-field">
                                <p>Fecha de pedido</p>
                                <%
                                Date fecha = new Date();
                                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaFormateada = formateador.format(fecha);
                                %>
                                <input type="text" placeholder="<%=fechaFormateada%>" readonly>
                                <i class='bx bx-calendar'></i>
                            </div>                                                         
                        </div>                    
                    </div>                
                </div>            
                <div class ="resumen_pago">
                    <div class="grid_resumen">
                        <%
                        float costo_total = carrito.costoTotal();
                        float igv = (float) (costo_total * 0.18);
                        float sub_total = costo_total - igv;
                        igv = Math.round(igv * 10.0f) / 10.0f;
                        sub_total = Math.round(sub_total * 10.0f) / 10.0f;
                        costo_total = Math.round(costo_total * 10.0f) / 10.0f;
                        %>
                        <div class="resumen"><h2>Resumen</h2></div>
                        <div class="subtotal_resumen"><p class="p_subtotal">SubTotal:</p></div>
                        <div class="precio_subtotal"><p>S/. <%=sub_total%></p></div>
                        <div class="subtotal_resumen"><p class="p_subtotal">IGV(18%):</p></div>
                        <div class="precio_subtotal"><p>S/. <%=igv%></p></div>
                        <div class="total_resumen"><p class="p_total">Total:</p></div>
                        <div class="precio_total"><p>S/. <%=costo_total%></p></div>                
                    </div>   
                    <div class="grid_metodo_pago">
                        <div class="metodo_pago">
                            <h2>Método de pago</h2>                        
                        </div>
                        <div class="seleccion_pago">
                            <p>Seleccione cómo pagar: </p>
                            <select id="metodo_pago_select" name="metodo_pago" required>                            
                                <option value="" selected disabled>Medio de pago</option>
                                <option value="Tarjeta">Tarjeta de crédito o débito</option>
                                <option value="Depósito">Efectivo</option>
                            </select>
                        </div>
                        <button id="btnPagar" type="submit" class="btn_pagar">Pagar</button>
                    </div>
                </div>            
            </div>
        </form>
    </main>  
                    
    <script>
        // Evento cuando se envía el formulario
        document.getElementById('formPago').addEventListener('submit', function(event) {
            // Obtener nombres y apellidos
            let nombres = "<%=cliente.getNombres()%>";
            let apellidos = "<%=cliente.getApellidos()%>";

            // Dividir nombres y apellidos en arrays de palabras
            let palabrasNombres = nombres.split(' ');
            let palabrasApellidos = apellidos.split(' ');

            // Obtener el nombre completo
            let nombreCompleto = palabrasNombres.join(' ') + ' ' + palabrasApellidos.join(' ');

            // Construir la URL completa
            let url = "https://web.whatsapp.com/send/?phone=%2B51941225240&text=Hola+Franklin%21.+Me+llamo+" + encodeURIComponent(nombreCompleto) + "+y+mi+DNI+es+" + <%=cliente.getDni()%> + ".+Deseo+confirmar+el+pago+de+S%2F." + <%=carrito.costoTotal()%> + "+de+mi+pedido.&type=phone_number&app_absent=0";

            // Abrir la URL en una nueva pestaña
            window.open(url, '_blank');

            // Puedes agregar aquí cualquier otra lógica que necesites antes de enviar el formulario
        });
    </script>
           
</body>
</html>
