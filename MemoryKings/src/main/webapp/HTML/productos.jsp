<%@page import="logica.Categoria"%>
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

        <title>MemoryKings</title>
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
                <form action="SvListaPedidos" method="GET" style="display: flex;padding-right: 40px;left: 30%;min-width: 220px;min-height: 25px;">
                    <button style="border: none;border-radius: 5px;width: -webkit-fill-available;min-height: 35px;background-color: gold;"><span>Lista de pedidos</span></button>
                </form>
                <div class="container">
                    <form action="SvBusquedaProductos" method="GET">
                        <input type="text" id="productInput" name="productInput" placeholder="Buscar..." autocomplete="off">
                        <input type="hidden" id="productCode" name="productCode">
                        <div id="suggestions" class="suggestions"></div>
                        <button type="submit" id="botonBusqueda"><i class='bx bx-search'></i></button>
                    </form>
                    <form action="SvBusquedaProductos" method="GET" style="
                          position: absolute;
                          right: -25px;
                          top: 0;
                          ">
                        <input type="hidden" id="productCode" name="productCode" value="0">
                        <button style="height: 38px;width: 20px;border-radius: 5px; border: none;">X</button>
                    </form>
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
                        <button id="salir" name="btn_close_session" type="submit">Cerrar sesión</button>
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
                    <li>
                        <form action="SvCategorias" method="GET">
                            <input type="hidden" name="idCategoria" value="0">
                            <button>Todos</button>
                        </form>
                    </li>
                    <%
                        List<Categoria> listaCategorias = ctrl_logica.traerCategorias();
                        for (Categoria ct : listaCategorias) {
                    %>
                    <li>
                        <form action="SvCategorias" method="GET">
                            <input type="hidden" name="idCategoria" value="<%=ct.getIdCategoria()%>">
                            <button><%=ct.getNombre()%></button>
                        </form>
                    </li>
                    <%
                        }
                    %>
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
                        for (Producto producto : listaProductos) {

                    %>

                    <div class="product">
                        <div class="product-images">
                            <%                            // Buscar las imágenes correspondientes a este producto
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
                            int n = 0;
                            for (int i = 0; i < caracteres.length && i < 100; i++) {
                                sb.append(caracteres[i]);
                                n++;
                            }

                            if (n <= 100) {
                        %>
                        <p><%=producto.getDescripcion()%></p>
                        <%
                        } else {
                        %>
                        <p><%=descripcion%>...</p>
                        <%
                            }
                        %>

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
            <form action="SvConsultas" method="GET">
                <%
                    Cliente cliente = mewing.getCliente();
                %>
                <div class="consult">
                    <input type="hidden" name="idCliente" value="<%=cliente.getIdCliente()%>">
                    <button type="submit"><i class='bx bx-message-dots'></i> Consultas</button>
                </div>
            </form>
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
    <script>
        <% 
            List<Producto> ls = ctrl_logica.traerProductos();
            int size = ls.size();
            int c = 0;
        %>
        const products = {
            <% for (Producto p : ls) { %>
                "<%= p.getNombre() %>": "<%= p.getIdProducto() %>"<%= c++ < size - 1 ? "," : "" %>
            <% } %>
        };

        const productInput = document.getElementById('productInput');
        const productCode = document.getElementById('productCode');
        const suggestionsBox = document.getElementById('suggestions');

        productInput.addEventListener('input', function() {
            const input = productInput.value.toLowerCase();
            suggestionsBox.innerHTML = '';
            if (input) {
                const suggestions = Object.keys(products).filter(product => product.toLowerCase().includes(input));
                suggestions.forEach(product => {
                    const suggestionItem = document.createElement('div');
                    suggestionItem.textContent = product;
                    suggestionItem.addEventListener('click', function() {
                        productInput.value = product;
                        productCode.value = products[product];
                        suggestionsBox.innerHTML = '';
                        suggestionsBox.style.display = 'none';
                    });
                    suggestionsBox.appendChild(suggestionItem);
                });
                suggestionsBox.style.display = 'block';
            } else {
                suggestionsBox.style.display = 'none';
            }
        });

        document.addEventListener('click', function(event) {
            if (!suggestionsBox.contains(event.target) && event.target !== productInput) {
                suggestionsBox.style.display = 'none';
            }
        });
    </script>
</body>
</html>


