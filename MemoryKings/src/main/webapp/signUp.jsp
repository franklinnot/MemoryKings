
<%@page import="servlets.SvCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MemoryKings | Registro</title>
    <link rel="stylesheet" href="CSS/sign_up.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="CSS/normalize.css">
    <script src="JavaScript/validation.js"></script>
</head>

<body>
    <div class="wrapper">
        <form action="SvCliente" method="POST">
            <h1>Sign Up</h1>

            <div class="input-box">
                <div class="input-field">
                    <input name="txt_dni" type="number" placeholder="DNI" oninput="validarDNI(this)" min="0" max="99999999" required>
                </div>
            </div>

            <div class="input-box">
                <div class="input-field">
                    <input name="txt_name" type="text" placeholder="Name" oninput="validarNombre(this)" required>
                    <i class='bx bxs-user'></i>
                </div>
                <div class="input-field">
                    <input name="txt_lastname" type="text" oninput="validarNombre(this)" placeholder="Last Name" required>
                    <i class='bx bxs-user'></i>
                </div>
            </div>

            <div class="input-box">
                <div class="input-field">
                    <input name="txt_phonenumber" type="number" placeholder="Phone Number" oninput="validarTelefono(this)" required>
                    <i class='bx bxs-phone'></i>
                </div>
                <div class="input-field">
                    <!--
                         <label class="id">
                        Date of birth
                    </label>
                    -->
                    <input name="txt_date" type="date" required>
                </div>
            </div>

            <div class="input-box">
                <div class="input-field">
                    <input name="txt_gender" type="text" placeholder="Gender" required>
                </div>
                <div class="input-field">
                    <input name="txt_address" type="text" placeholder="Address" oninput="validarDireccion(this)" required>
                    <i class='bx bxs-location-plus'></i>
                </div>
                
            </div>

            <div class="input-box">
                <div class="input-field">
                    <input name="txt_email" type="email" placeholder="Email adrress" oninput="validarCorreo(this)" required>
                    <i class='bx bxs-envelope'></i>
                </div>
                <div class="input-field">
                    <input name="txt_password" type="password" placeholder="Password" required>
                    <i class='bx bxs-lock-alt'></i>
                </div>
            </div>
            <button type="submit" class="btn">Register</button>
        </form>
    </div>
</body>
</html>
