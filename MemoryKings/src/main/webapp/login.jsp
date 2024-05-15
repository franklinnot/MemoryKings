
<%@page import="logica.Cliente"%>
<%@page import="servlets.SvIndex"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MemoryKings | Inicio de sesión</title>
    <link rel = "stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="CSS/normalize.css">
    <script src="JavaScript/preventBack.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body> 
    <div class="container">
        <div class="form-content">                       
            <h1 id="title">Inicio</h1>
            <form action="SvIndex" method="GET">
                <div class="input-group">
                    <div class="input-field">
                        <i class="fa-solid fa-user"></i>
                        <input name="txt_email" type="email" placeholder="Correo Electrónico">
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-lock"></i>
                        <input name="txt_password" type="password" placeholder="Contraseña">
                    </div>
                    <!-- <p> Olvidaste tu contraseña <a href="#">click aqui</a></p>-->
                </div>
                <div class="btn-field">
                    <button name="btn_login" id="signIp" type="buttom" onclick="refreshPage()" >Ingresar</button>
                    <button name="btn_signup" id="btn_signUp" type="buttom" class="disable">Registrarme</button>                          
                </div>
            </form>
        </div>
    </div>
    <h2>Ver lista de usuarios</h2>
        <h3>Para ver la lista de ususarios, haga click en el siguiente boton</h3>
        <form action="SvCliente" method="GET">
            <button type="submit">Mostrar usuarios</button>
        </form>
</body>
</html>