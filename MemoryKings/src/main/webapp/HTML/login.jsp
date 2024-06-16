
<%@page import="logica.Cliente"%>
<%@page import="servlets.SvLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/registro.css">   
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <div class="container">
        <div class="form-content">
            <h1 id="title">Inicio</h1>
            <div>
                <form action="SvLogin" method="GET">
                <div class="input-group">
                    <div class="input-fi">
                        <i class="fa-solid fa-user"></i>
                        <input name="txt_email" type="email" placeholder="Correo Electrónico">
                    </div>
                    <div class="input-fi">
                        <i class="fa-solid fa-lock"></i>
                        <input name="txt_password" type="password" placeholder="Contraseña">
                    </div>
                    <!-- <p> Olvidaste tu contraseña <a href="#">click aqui</a></p>-->
                </div>

                <div class="btn-field">
                    <button name="btn_login" id="signIp" type="submit">Ingresar</button>
                    
                </div>
            </form>
                <div class="btn-field" id="signUpDiv">
                    <button id="signUp" type="buttom" class="disable">Registrarme</button>
                </div>
            </div>

            <dialog id="modal" modal>
                <script src="JavaScript/validation.js"></script>
                <div class="wrapper">
                    <div class="registro_head">
                        <button id="btn-cerrar">regresar</button>
                        <h1>Registro</h1>
                    </div>
                    <form action="SvLogin" method="POST">
                        <div class="input-box">
                            <div class="input-field">

                                <input name="txt_dni" type="text" placeholder="DNI" oninput="validarDNI(this)" min="0" max="99999999" required>
                                
                                <i class='bx bx-id-card'></i>
                            </div>
                        </div>

                        <div class="input-box">
                            <div class="input-field">
                                <input name="txt_name" type="text" placeholder="Nombre" oninput="validarNombre(this)"
                                    required>
                                <i class='bx bxs-user'></i>
                            </div>
                            <div class="input-field">
                                <input name="txt_lastname" type="text" oninput="validarNombre(this)"
                                    placeholder="Apellidos" required>
                                <i class='bx bxs-user'></i>
                            </div>
                        </div>

                        <div class="input-box">
                            <div class="input-field">
                                <input name="txt_phonenumber" type="number" placeholder="Teléfono celular"
                                    oninput="validarTelefono(this)" required>
                                <i class='bx bxs-phone'></i>
                            </div>
                            <div class="input-field">
                                <input name="txt_date" type="date" placeholder="Fecha de Nacimiento" required>
                                <i class='bx bx-notepad'></i>
                            </div>
                        </div>

                        <div class="input-box">
                            <div class="input-field">
                                <input name="txt_gender" type="text" placeholder="Género" required>
                                <i class='bx bx-male-female'></i>
                            </div>
                            <div class="input-field">
                                <input name="txt_address" type="text" placeholder="Dirección"
                                    oninput="validarDireccion(this)" required>
                                <i class='bx bxs-home'></i>
                            </div>
                        </div>

                        <div class="input-box">
                            <div class="input-field">
                                <input name="txt_email" type="email" placeholder="Correo Electrónico"
                                    oninput="validarCorreo(this)" required>
                                <i class='bx bxs-envelope'></i>
                            </div>
                            <div class="input-field">
                                <input name="txt_password" type="password" placeholder="Contraseña" required>
                                <i class='bx bxs-lock-alt'></i>
                            </div>
                        </div>
                        <button id="btn-registrar" type="submit" class="btn"> Registrarse </button>
                    </form>
                </div>
                
            </dialog>

        </div>
    </div>
    <script src="JavaScript/login.js"></script>
</body>

</html>