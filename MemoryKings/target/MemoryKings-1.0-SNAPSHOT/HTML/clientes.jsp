
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TimeZone"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    TimeZone zonaHorariaPeru = TimeZone.getTimeZone("America/Lima");
    int desplazamientoPeru = zonaHorariaPeru.getRawOffset();
    SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM", new java.util.Locale("es", "ES"));
    sdfFecha.setTimeZone(zonaHorariaPeru);

    ControladoraLogica ctrl_logica = new ControladoraLogica();
    List<Cliente> listaClientes = ctrl_logica.traerClientes();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../CSS/clientes.css">
        <!--permite ingresar logos de font awesome-->
        <script src="https://kit.fontawesome.com/b408879b64.js" crossorigin="anonymous"></script>
        <title>Registro de Clientes</title>
    </head>
    <body>
        <header>
            <!--aqui estoy juntando boton menu con logo con un div
                para que cuando se ajuste la pantalla a las preferencias 
                del usuario no se desconfigure y esten los dos juntos-->
            <div class="juntarlogo">
                <!--AÑADIR ICONO DE MENU-->
                <div class="btnmenu">
                    <label><i class="fa-solid fa-bars"></i></label>
                </div>


                <!--AÑADIR LOGO-->
                <a href="#" class="logo">
                    <img src="../Image/logo.png" alt="logo empresa">
                    <h2 class="nombre_empresa"></h2>
                </a>
            </div>


            <!--CREAR BARRA DE BUSQUEDA-->
            <div class="container">
                <input type="text" placeholder = "Buscar...">
                <button id="botonBusqueda"><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
        </header>

        <main>
            <!--creo un div para acciones de clientes, aqui se almacenaran los botones y el combobox para filrar las busquedas-->
            <div class = "acciones_cliente">
                <div class ="eliminar_cliente"></div>
                <button>Eliminar Cliente</button>
            </div>
        </div>
        <!--creo un div en donde se almacenaran todos los registros de clientes-->
        <table style="width: -webkit-fill-available;">   
            <thead>
                <tr>
                    <th style="background-color: white;color: black;height: 50px;">DNI</th>
                    <th style="background-color: white;color: black;height: 50px;">Nombres completos</th>
                    <th style="background-color: white;color: black;height: 50px;">Telefono</th>
                    <th style="background-color: white;color: black;height: 50px;">Genero</th>
                    <th style="background-color: white;color: black;height: 50px;">Correo</th>
                    <th style="background-color: white;color: black;height: 50px;">Fecha Nacimiento</th>
                    <th style="background-color: white;color: black;height: 50px;">Direccion</th> 
                </tr>
            </thead>

            <tbody>
                <%
                    for (Cliente cl : listaClientes) {
                %>
                <tr>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getDni()%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getNombres()%> <%=cl.getApellidos()%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getTelefono()%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getGenero()%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getCorreo()%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=sdfFecha.format(cl.getFechaNacimiento())%></th>
                    <th style="background: #453C39;color: white; font-weight: lighter;height: 50px;"><%=cl.getDireccion()%></th>
                </tr>
                <%
                    }
                %>
            </tbody>

        </table>


    </main>
</body>
</html>