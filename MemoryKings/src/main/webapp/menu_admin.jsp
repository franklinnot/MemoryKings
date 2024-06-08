
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
                <span>Hola <b>admin</b>!</span> <!<!-- similar al cliente, aqui debe colocarse el nombre del empleado logueado -->
            </div>
        </div>
    </header>

    <main>
        <div class="menu">
            <div class="menu_fila_uno">
                <a target="_blank" href=""><span>Pedidos</span></a>
                <a target="_blank" href="clientes.jsp"><span>Clientes</span></a>
                <a target="_blank" href=""><span>Consultas</span></a>
            </div>
            <div class="menu_fila_dos">
                <a target="_blank" href="registro_productos.jsp"><span>Productos</span></a>
                <form action="SvSession" method="POST">
                    <button id="cerrar_sesion"><span>Cerrar sesión</span></button>
                </form>
            </div>
        </div>
    </main>

    <footer>
        <span>03/06/2024</span>
    </footer>
</body>
</html>
