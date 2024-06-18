
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atención de Pedidos</title>
    <link rel="stylesheet" href="../CSS/normalize.css">
    <link rel="stylesheet" href="../CSS/style.css">
    <link rel="stylesheet" href="../CSS/atencionPedido.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>Atención de Pedidos</title>
</head>
<body>
    <header>
        <a href="#" class="logo">
            <img src="../Image/logo.png" alt="logo empresa">
        </a>
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
                    <tr>
                        <td>001</td>
                        <td>49017935</td>
                        <td>Jhoncito BC</td>
                        <td>01/06/2024</td>
                        <td>Al costado de su vecino</td>
                        <td><span class="estado atendido">Atendido</span></td>
                        <td><button>Atender</button></td>
                    </tr>
                    <tr>
                        <td>002</td>
                        <td>70549588</td>
                        <td>Ana Belen SB</td>
                        <td>05/06/2024</td>
                        <td>Mi casa</td>
                        <td><span class="estado en-proceso">En Proceso</span></td>
                        <td><button>Atender</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
    <footer>
        <div class="linea-inferior"></div>
    </footer>
</body>
</html>

