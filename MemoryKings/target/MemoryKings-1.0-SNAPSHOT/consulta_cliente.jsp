<%-- 
    Document   : consulta_cliente
    Created on : Jun 6, 2024, 8:26:14 AM
    Author     : frank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/consulta_cliente.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>consultas</title>
</head>
<body>
    <div class="div_principal"> 
        <div class="logo_container">
            <img id="loguito" src="Images/logo.png">
        </div>
            <form > 
                <div class="div_division1">
                    <div class="div_inputs">
                        <label id="dnilbl" for="dni">DNI: <input type="text"id="dni" placeholder="DNI"></label>
                        <label id="nomlbl" for="nombres">Nombres: <input type="text"id="nombres" placeholder="Nombre"></label>
                        <label id="apelbl" for="apellidos">Apellidos: <input type="text"id="apellidos" placeholder="Apellidos"></label>
                        <label id="colbl"  for="correo">Correo: <input type="email"id="correo" placeholder="Correo"></label>
                    </div>
                    <div class="div_combobox">
                        <label id="escoger">Escoge el tipo de servicio: 
                            <select id="cbtiposervicio" >
                                <option value = "" selected disabled>---Seleccionar Tipo---</option>
                                <option value = "a">a</option>
                                <option value = "b">b</option>
                                <option value = "c">c</option>
                            </select>
                         </label>
                    </div>
                </div>
                <div class="div_textarea">
                    <label id="lbldescripcion" for="descripcion">Descripcíon de su consulta:</label>
                    <textarea id ="descripcion" placeholder="Describe tu problema" ></textarea>
                </div >
                <div class="div_botones">
                    <button  id="btregresar" type="submit">REGRESAR</button>
                    <button id="btenviar" type="submit">ENVIAR CONSULTA</button>   
                </div>  
            </form>
    </div>
</body>
</html>