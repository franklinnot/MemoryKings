<%@page import="logica.Cliente"%>
<%@page import="servlets.SvIndex"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="menu">
            <button class="menu-btn"><i class='bx bx-menu'></i></button>
        </div>
        <div class="logo">
            <a href="http://localhost:8080/MemoryKings/"><img src="Image/logo.png" alt="Logo"></a>          
        </div>
        <div class="search">
            <input type="text" placeholder="Buscar">
            <button><i class='bx bx-search'></i></button>
        </div>

        <div>
        <%
            Cliente cliente = (Cliente) session.getAttribute("datos_usuario");
            if (cliente != null) {
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
            <button><i class='bx bxs-cart'></i></button>
        </div>
    </header>

    
    <main>
        <aside class="filters">
            <!-- filtros de búsqueda -->
        </aside>
    
        <!-- contenido principal página -->
    
    </main>
    
    <footer>
        <div class="consult">
            <button><i class='bx bx-message-dots'></i> Consultas</button>
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
    
   
</body>
</html>
