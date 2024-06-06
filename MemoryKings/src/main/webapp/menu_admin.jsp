
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/menu_admin.css">
    <title>Menú | Admin</title>
</head>
<body>
    <header>
        <div class="encabezado">
            <div class="encabezado__logo">
                <img src="Image/logo.png" alt="">
            </div>
            <div class="encabezado__saludo">
                <span>Hola <b>admin</b>!</span>
            </div>
        </div>
    </header>
    <!-- queda pendiente alutilizar a la pagina principal para cualquier opcion seleccionada -->
    <!-- otra opcion es dejarlo como target="_blank" -->
    <main>
        <div class="menu">
            <div class="menu_fila_uno">
                <a target="_blank" href="atencion_pedido.jsp"><span>Pedidos</span></a>
                <a target="_blank" href="clientes.jsp"><span>Clientes</span></a>
                <a target="_blank" href="consulta_cliente"><span>Consultas</span></a>
            </div>
            <div class="menu_fila_dos">
                <a target="_blank" href=""><span>Productos</span></a>
                <a target="_blank" id="cerrar_sesion" href=""><span>Cerrar sesión</span></a>
            </div>
        </div>
    </main>

    <footer>
        <span>03/06/2024</span>
    </footer>
</body>
</html>