<%@page import="java.util.Base64"%>
<%@page import="logica.ImagenProducto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="logica.Producto"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="logica.Mewing"%>
<%@page import="logica.Cliente"%>
<%@page import="servlets.SvLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/producto.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    <title>Productos</title>
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
        
        
        <div class="juntarlogo">
            <!--CREAR BARRA DE BUSQUEDA-->
            <div class="container">
                <input type="text" placeholder="Buscar...">
                <button id="botonBusqueda"><i class='bx bx-search'></i></button>
            </div>
            <div class="espacio-separacion"></div>
            
            <div class="datito">
            <%
                // validar con un try catch cuando lista de productos sea null
                Mewing mewing = (Mewing) session.getAttribute("user");
                
                if (mewing != null && mewing.getCliente() != null) {
                    Cliente cliente = mewing.getCliente();
            %>
                    <h2>Hola <%=cliente.getNombres()%>!</h2>
                    <form action="SvSession" method="POST">
                        <button name="btn_close_session" type="submit">Cerrar sesión</button>
                    </form>
            <% 
                } else { 
            %>
                    <h2><a style="text-decoration: none; color: white;" href="<%=request.getContextPath()%>/">Inicia sesión</a></h2>
            <% 
                } 
            %>

            </div>
            
            <div class="cart">
                <form action="SvCarritoCompras" method="POST">
                    <button type="submit" ><i class='bx bxs-cart'></i></button>
                </form>
            </div>
        </div>
      
        <!-- MENÚ DESPLEGABLE -->
        <nav class="dropdown-menu">
            <ul>
                <li><a href="#">Accesorios</a></li>
                <li><a href="#">Adaptadores</a></li>
                <li><a href="#">Auriculares</a></li>
                <li><a href="#">Cables</a></li>
                <li><a href="#">Micrófonos</a></li>w
                <li><a href="#">Cámaras</a></li>
                <li><a href="#">Celulares</a></li>
                <li><a href="#">Discos Duro</a></li>
                <li><a href="#">Impresoras</a></li>
                <li><a href="#">Laptops</a></li>
                <li><a href="#">Memorias</a></li>
                <li><a href="#">Monitores</a></li>
                <li><a href="#">Mouse</a></li>
                <li><a href="#">Parlantes</a></li>
                <li><a href="#">Placas madres</a></li>
            </ul>
        </nav>
    </header>


    <main>
        <!-- contenido principal página -->
        <div class="product-wrapper">
            <div class="product-container">
                
                <%
                
                List<Producto> listaProductos = new ArrayList<>();
                List<ImagenProducto> listaImagenes = new ArrayList<>();
                listaProductos = (List<Producto>) session.getAttribute("listaProductos");
                listaImagenes = ctrl_logica.traerImagenProductos();
                for (Producto producto : listaProductos){

                %>
                
                <div class="product">
                    <div class="product-images">
                        <%
                        // Buscar las imágenes correspondientes a este producto
                        for (ImagenProducto imagen : listaImagenes) {
                            if (imagen.getProducto().getIdProducto() == producto.getIdProducto()) {
                                byte[] imagenBytes = imagen.getImagen();
                                String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
                        %>
                        <img src="data:image/jpeg;base64,<%=base64Image%>" alt="Imagen del Producto";"/>
                        <%
                            }
                        }
                        %>
                    </div>
                    <h3><%=producto.getNombre()%> S/.<%=producto.getPrecio()%></h3>
                    <%
                    String descripcion = "";
                    char[] caracteres = producto.getDescripcion().toCharArray();
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < caracteres.length && i < 30; i++) {
                        sb.append(caracteres[i]);
                    }

                    descripcion = sb.toString();
                    %>
                    <p><%=descripcion%>...</p>
                    <form action="SvCarritoCompras" method="GET">
                        <input type="hidden" name="idProducto" value="<%=producto.getIdProducto()%>">
                        <button type="submit" >Agregar al carrito</button>
                    </form>
                </div>
                
                <%
                }
                %>

            </div>
        </div>
    </main>
    
    <footer>
        <div class="consult">
            <a target="_blank" href="consulta_cliente.jsp"><span class='bx bx-message-dots'>Consultas</span></a>
        </div>
        <div class="social">
            <p>Síguenos en:</p>
            <ul>
                <li><a href="#"><i class='bx bxl-facebook-circle'></i></a></li>
                <li><a href="#"><i class='bx bxl-instagram-alt'></i></a></li>
                <li><a href="#"><i class='bx bxl-tiktok'></i></a></li>
                <li><a href="#"><i class='bx bxl-youtube'></i></a></li>
            </ul>
        </div>
    </footer>
    <script src="JavaScript/producto.js"></script>

</body>
</html>


