
<%@page import="logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.SvRegistroEmpleados"%>
<%@page import="logica.ControladoraLogica"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%
    Empleado empleado = (Empleado) session.getAttribute("user");
    String nombre = empleado.getNombres();
    String cargo = empleado.getCargo();
    boolean admin = true;
    if (!cargo.equals("Administrador")) { admin = false; }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/menu_admin.css">
    <title>Menú de Opciones</title>
</head>
<body>
    <header>
        <div class="encabezado">
            <div class="encabezado__logo">
                <img src="Image/logo.png" alt="">
            </div>
            <div class="encabezado__saludo">
                <span>Hola <b><%=nombre%></b>!</span> <!<!-- similar al cliente, aqui debe colocarse el nombre del empleado logueado -->
            </div>
        </div>
    </header>

    <main>
        <div class="menu">
            
            <div class="menu_fila_uno">
                <div>
                    <%
                        if (cargo.equals("Atender Pedido") || cargo.equals("Administrador")){
                    %>
                            <form action="SvPedidos" method="GET">
                    <%
                        }
                    %>
                                <button type="submit" id="" type="button"><span>Pedidos</span></button>
                    <%
                        if (cargo.equals("Atender Pedido") || cargo.equals("Administrador")){
                    %>
                            </form>
                    <%
                        }
                    %>
                </div>
                <div>
                    <a target="_blank" href="HTML/clientes.jsp"><span>Clientes</span></a>
                </div>
                <div>
                    <%
                        if (cargo.equals("Atender Consulta") || cargo.equals("Administrador")){
                    %>
                            <form action="SvAtenderConsulta" method="GET">
                    <%
                        }
                    %>
                                <button type="submit" id="" type="button"><span>Consultas</span></button>
                    <%
                        if (cargo.equals("Atender Consulta") || cargo.equals("Administrador")){
                    %>
                            </form>
                    <%
                        }
                    %>
                </div>
            </div>
            
            <div class="menu_fila_dos">
                <div>
                    <button <% if(!admin){%> disabled <%}%> id="btnEmpleado" type="button"><span>Empleado</span></button>
                </div>
                <div>
                    <button <% if(!admin){%> disabled <%}%> id="btnProveedor" type="button"><span>Proveedor</span></button>
                </div>
                <div>
                    <button <% if(!admin){%> disabled <%}%> id="btnDistribuidor" type="button"><span>Distribuidor</span></button>
                </div>
            </div>
            
            <div class="menu_fila_tres">
                <div>
                    <a target="_blank" href="HTML/registro_productos.jsp"><span>Productos</span></a>
                </div>
                <div>
                    <form action="SvSession" method="POST">
                        <button id="cerrar_sesion"><span>Cerrar sesión</span></button>
                    </form>
                </div>
            </div>
            
        </div>
    </main>

    <footer>
        <span>03/06/2024</span>
    </footer>
    
    <!-- Modal EMPLEADO -->
    <div id="modalEmpleado" class="modal">      
        <div class="wrapper">
            <span class="close">&times;</span>
            <h1>Empleado</h1>
            <form action="SvRegistroEmpleados" method="POST">
                <div class="input-box">

                        <div class="input-field">
                            <input  name="empleado_dni" type="number" id="dni" placeholder="DNI" required>
                            <i class="fas fa-id-card"></i>
                        </div>
                        <div class="input-field">
                            <select name="empleado_cargo" id="cargo" required>
                                <option value="" disabled selected>Seleccionar Cargo</option>
                                <option value="Atender Pedido">Atender Pedido</option>
                                <option value="Atender Consulta">Atender Consulta</option>
                            </select>
                            <i class="fas fa-briefcase"></i>
                        </div>
                        <div class="input-field">
                            <input name="empleado_nombres" type="text" id="nombre" placeholder="Nombre" required>
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="input-field">
                            <input name="empleado_apellidos" type="text" id="apellidos" placeholder="Apellidos" required>
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="input-field">
                            <input name="empleado_telefono" type="number" id="telefono" placeholder="Teléfono" required>
                            <i class="fas fa-phone"></i>
                        </div>
                        <div class="input-field">
                            <input name="empleado_fecha_nacimiento" type="date" id="fecha_nacimiento" placeholder="Fecha de Nacimiento" required>
                            <i class="fas fa-calendar-alt"></i>
                        </div>
                       <div class="input-field">
                           <select name="empleado_genero" id="genero" required>
                               <option value="" disabled selected>Seleccionar Género</option>
                               <option value="F">Femenino (F)</option>
                               <option value="M">Masculino (M)</option>
                               <option value="Otro">Otro</option>
                           </select>
                           <i class="fas fa-venus-mars"></i>
                       </div>
                       <div class="input-field">
                           <input name="empleado_direccion" type="text" id="direccion" placeholder="Dirección" required>
                           <i class="fas fa-map-marker-alt"></i>
                       </div>
                       <div class="input-field">
                           <input type="email" id="correo" name="empleado_correo" placeholder="Correo Electrónico" required>
                           <i class="fas fa-envelope"></i>
                       </div>
                       <div class="input-field">
                           <input type="password" id="contrasena" name="empleado_password" placeholder="Contraseña" required>
                           <i class="fas fa-lock"></i>
                       </div>
                       <button type="submit" id="btnRegistrarEmpleado" class="btn">Registrar</button>         
                </div>
            </form>
        </div>
    </div>

    <!-- Modal Proveedor -->
    <div id="modalProveedor" class="modal">
            <div class="wrapper">
                <span class="close">&times;</span>
                <h1>Proveedor</h1>
            <form action="SvEmpresas" method="POST">
                <input type="hidden" name="tipo" value="proveedor">
                <div class="input-box">
                    <div class="input-field">
                        <input type="number" id="ruc" name="ruc" placeholder="RUC" required>
                        <i class="fas fa-id-card"></i>
                    </div>
                    <div class="input-field">
                        <input type="text" id="nombre_comercial" name="nombre_comercial" placeholder="Nombre Comercial" required>
                        <i class="fas fa-store"></i>
                    </div>

                    <div class="input-field">
                        <input type="number" id="telefono" name="telefono" placeholder="Teléfono" required>
                        <i class="fas fa-phone-alt"></i>

                    </div>
                    <div class="input-field">
                        <input type="text" id="razon_social" name="razon_social" placeholder="Razon Social" required>
                        <i class="fas fa-building"></i>
                    </div>

                    <div class="input-field">
                        <input type="text" id="direccion" name="direccion" placeholder="Dirección" required>
                        <i class="fas fa-map-marked-alt"></i>
                    </div>
                    <div class="input-field">
                        <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>
                        <i class="fas fa-envelope"></i>
                    </div>
                </div>
                <button type="submit" id="btnRegistrarProveedor" class="btn" onclick="enviarRegistro('Proveedor')">Registrar</button>
            </form>
            </div>
   
    </div>

    <!-- Modal Distribuidor -->
    <div id="modalDistribuidor" class="modal">
        <form action="SvEmpresas" method="POST">
            <input type="hidden" name="tipo" value="distribuidor">
            <div class="wrapper">
                <span class="close">&times;</span>
                <h1>Distribuidor</h1>
                <div class="input-box">
                    <div class="input-field">
                        <input type="number" id="ruc" name="ruc" placeholder="RUC" required>
                        <i class="fas fa-id-card"></i>
                    </div>
                    <div class="input-field">
                        <input type="text" id="nombre_comercial" name="nombre_comercial" placeholder="Nombre Comercial" required>
                        <i class="fas fa-store"></i>
                    </div>

                    <div class="input-field">
                        <input type="number" id="telefono" name="telefono" placeholder="Teléfono" required>
                        <i class="fas fa-phone-alt"></i>

                    </div>
                    <div class="input-field">
                        <input type="text" id="razon_social" name="razon_social" placeholder="Razon Social" required>
                        <i class="fas fa-building"></i>
                    </div>

                    <div class="input-field">
                        <input type="text" id="direccion" name="direccion" placeholder="Dirección" required>
                        <i class="fas fa-map-marked-alt"></i>
                    </div>
                    <div class="input-field">
                        <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>
                        <i class="fas fa-envelope"></i>
                    </div>
                </div>
                <button type="submit" id="btnRegistrarDistribuidor" class="btn" onclick="enviarRegistro('Distribuidor')">Registrar</button>
            </div>
        </form>
    </div>
    <script src="JavaScript/modal.js"></script>
    
</body>
</html>