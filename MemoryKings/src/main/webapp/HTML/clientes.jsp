
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../CSS/clientes.css">
    <!--permite ingresar logos de font awesome-->
    <script src="https://kit.fontawesome.com/b408879b64.js" crossorigin="anonymous"></script>
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
        <div class="div_contenedor">
            <article>   
                <p>DNI</p>
                <p>Nombre</p>
                <p>Apellido</p>
                <p>Telefono</p>
                <p>Genero</p>
                <p>Correo</p>
                <p>Fecha Nacimiento</p>
                <p>Direccion</p>
                <p>Contraseña</p>
                   
                
                <h4>75495595</h4>
                <p>Flavio</p>
                <p>Zavaleta</p>
                <p>9565654</p>
                <p>M</p>
                <p>zflavioalberto@gmail.com</p>
                <p>27/05/2005</p>
                <p>av si</p>
                <p>no te la voy a decir</p>

                <h4>75495595</h4>
                <p>Flavio</p>
                <p>Zavaleta</p>
                <p>9565654</p>
                <p>M</p>
                <p>zflavioalberto@gmail.com</p>
                <p>27/05/2005</p>
                <p>av si</p>
                <p>no te la voy a decir</p>
                
               
            </table>
            
        </div>


    </main>
</body>
</html>