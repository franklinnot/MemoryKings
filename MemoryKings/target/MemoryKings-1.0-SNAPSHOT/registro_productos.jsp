
<%@page import="logica.Cliente"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Producto"%>
<%@page import="java.util.List"%>
<%@page import="logica.Mewing"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="CSS/registro_productos.css">
    <link rel="stylesheet" href="CSS/normalize.css">
    <!--permite ingresar logos de font awesome-->
    <script src="https://kit.fontawesome.com/b408879b64.js" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <div class="juntarlogo">
            <div class="btnmenu">
                <label><i class="fa-solid fa-bars"></i></label>
            </div>

            <a href="#" class="logo">
                <img src="Image/logo.png" alt="logo empresa">
                <h2 class="nombre_empresa"></h2>
            </a>
            
        </div>
        <div class="juntarlogo">

            <div class="container">
               <form action="">
                <input id="Buscar" type="text" placeholder = "Buscar...">
                <button id="botonBusqueda"><i class="fa-solid fa-magnifying-glass"></i></button>
               </form>
            </div>
            <div class="espacio-separacion"></div>
            <div class="cart">

                <label for="btn-modal" class="lbl-modal">Registrar Productos</label>
            </div>
        </div>

    </header>

    <main>
        <div class= "contenedor_general">
           <div class = "acciones_productos">
                <div class="eliminar_producto">
                    

                    <form action="SvRegistroProductos" method="GET">
                        <input type="text" placeholder="Código del producto...">
                        <button type="submit">Eliminar Producto</button>
                    </form>
                    
                    
                    
                </div>
           </div>
            <div class="div_contenedor">
                <article>   
                    <p style="color:black; background: white;">Nombre</p>
                    <p style="color:black; background: white;">Marca</p>
                    <p style="color:black; background: white;">Descripcion</p>
                    <p style="color:black; background: white;">Precio</p>
                    <p style="color:black; background: white;">Categoria</p>
                    <p style="color:black; background: white;">Proveedor</p>
                    <p style="color:black; background: white;">Imagen referencial</p>
                    
                    <%
                        List<Producto> listaProductos = new ArrayList<>();
                        listaProductos = ctrl_logica.traerProductos();
                        
                        if (listaProductos != null && !listaProductos.isEmpty()) {

                            for (Producto producto : listaProductos){
                        
                    %>                     
                    <p><%=producto.getNombre()%></p>
                    <p><%=producto.getMarca()%></p>
                    <p><%=producto.getDescripcion()%></p>
                    <p><%=producto.getPrecio()%></p>
                    <p><%=producto.getCategoria()%></p>
                    <p><%=producto.getProveedor()%></p>
                    <p><%=producto.getListaImagen()%></p>   
                    <%
                            }
                        }
                    %>
                    
                </article>
            </div>
        </div>

        




    </main>

    <!--se crea modal con el registro de productos-->
    <input type="checkbox" id="btn-modal">
    
    <div class="modal">
        <div class="contenedor">
            <label for="btn-modal">X</label>
            <div id="cabecera_modal"><h1>Registro</h1></div>
            <form action="">
                <div class="fila-input">
                    <input class="campo_producto" type="text" placeholder="Nombre" required>
                    <input class="campo_producto" type="text" placeholder="Marca" required>
                </div>
                
                
                <div class="fila-input">
                    <input class="campo_producto" type="text" placeholder="Descripción" required>
                    <input class="campo_producto" type="number" placeholder="Precio" required>
                </div>
                <div class="fila-input">
                    <select required>
                        <option value="" disabled selected>Categoría</option>
                        <option value="categoria1">Categoría 1</option>
                        <option value="categoria2">Categoría 2</option>
                        <option value="categoria3">Categoría 3</option>
                        <!-- Agrega más opciones según sea necesario -->
                    </select>
                    <input class="campo_producto" type="text" placeholder="Proveedor" required>
                </div>
                <div class="fila-input">
                    <input type="file" id="imagenes" name="imagenes" multiple accept="image/*">
                </div>
                <button id="btn-registrar" type="submit" class="btn">Registrar</button>
            </form>
        </div>
    </div>

    
</body>
</html>
</body>
</html>
