

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel = "stylesheet" href="style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container">
            <div class="form-content">
                <h1 id="title">Log in</h1>
                <form action="SvCliente" method="POST">
                    <div class="input-group">
                        <div class="input-field">
                            <i class="fa-solid fa-user"></i>
                            <input name="txt_email" type="email" placeholder="Email adrress">
                        </div>
                        <div class="input-field">
                            <i class="fa-solid fa-lock"></i>
                            <input name="txt_password" type="password" placeholder="Password">
                        </div>
                        <p> Olvidaste tu contraseña <a href="#">click aqui</a></p>
                    </div>
                    <div class="btn-field">
                        <button id="signUp" type="button">Sign up</button>
                        <button name="btn_login" id="signIn" type="submit" class="disable">Log in</button>
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
