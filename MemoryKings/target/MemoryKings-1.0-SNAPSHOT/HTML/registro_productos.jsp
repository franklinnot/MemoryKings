
<%@page import="logica.Empleado"%>
<%@page import="java.util.Base64"%>
<%@page import="logica.ImagenProducto"%>
<%@page import="java.util.Collections"%>
<%@page import="logica.Proveedor"%>
<%@page import="logica.Categoria"%>
<%@page import="logica.Cliente"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Producto"%>
<%@page import="java.util.List"%>
<%@page import="logica.Mewing"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.SvRegistroProductos"%>
<% ControladoraLogica ctrl_logica = new ControladoraLogica(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Empleado empleado = (Empleado) session.getAttribute("user");
    boolean admin = false;
    if (empleado.getCargo().equals("Administrador")) { admin = true; } 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Productos</title>
    <link rel="stylesheet" href="../CSS/normalize.css">
    <link rel="stylesheet" href="../CSS/registro_productos.css">
    <title>Registro de Productos</title>
    <!--permite ingresar logos de font awesome-->
    <script src="https://kit.fontawesome.com/b408879b64.js" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <!--aqui estoy juntando boton menu con logo con un div
            para que cuando se ajuste la pantalla a las preferencias 
            del usuario no se desconfigure y esten los dos juntos-->
        <div class="juntarlogo">
            <!--BOTON MENU-->
            <div class="btnmenu">
                <label><i class="fa-solid fa-bars"></i></label>
            </div>
    
    
            <!--AÑADIR LOGO-->
            <a href="#" class="logo">
                <img src="../Image/logo.png" alt="logo empresa">
                <h2 class="nombre_empresa"></h2>
            </a>
            
        </div>
        <div class="juntarlogo">
            <!--CREAR BARRA DE BUSQUEDA-->
            <form action="" method="GET">
                <div class="container">
               <input id="Buscar" type="text" placeholder = "Buscar...">
               <button onclick="searchProduct()" id="botonBusqueda"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </form>
            <div class="espacio-separacion"></div>
            <div class="cart">
                <!--este lbl actua como un boton y se usara para abrir el modal donde se almacena registros-->
                <% if(admin){%> <label for="btn-modal" class="lbl-modal">Registrar Productos</label> <%}%>
            </div>
        </div>

        

        
    </header>

    <main>

                <div class= "contenedor_general">
            <!--creo un div para acciones de productos, aqui se almacenaran los botones y el textbox-->
           <div class = "acciones_productos">
                <input class="eliminar_producto"></input>
               <button>Eliminar Producto</button>

           </div>
            <!--creo un div en donde se almacenaran todos los registros de productos-->
            <div class="div_contenedor">
                <article class="noom">

                    <table class="jaja">
                        <tr data-code="123">
                            <td id="nombreHeader">Nombre</td>
                            <td id="marcaHeader">Marca</td>
                            <td id="descripcionHeader">Descripción</td>
                            <td id="precioHeader">Precio</td>
                            <td id="categoriaHeader">Categoría</td>
                            <td id="proveedorHeader">Proveedor</td>
                            <td id="imagenHeader">Imagen referencial</td>
                        </tr>
                    </table>
                    <!-- Datos del producto -->
                    <table class="productList" id="productList">
                    <%
                        List<Producto> listaProductos = new ArrayList<>();
                        List<ImagenProducto> listaImagenes = new ArrayList<>();
                        
                        listaProductos = ctrl_logica.traerProductos();    
                        listaImagenes = ctrl_logica.traerImagenProductos();
                        
                        Collections.sort(listaProductos, (p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
                        
                        if (listaProductos != null && !listaProductos.isEmpty()) {
                            for (Producto producto : listaProductos) {
                    %>
                    <tr data-code="<%=producto.getIdProducto()%>">
                            <td><%=producto.getNombre()%></td>
                            <td><%=producto.getMarca()%></td>
                            <td><%=producto.getDescripcion()%></td>
                            <td><%=producto.getPrecio()%></td>
                            <td><%=producto.getCategoria().getNombre()%></td>
                            <td><%=producto.getProveedor().getNombreComercial()%></td>
                            <td>
                            <%
                            // Buscar las imágenes correspondientes a este producto
                            for (ImagenProducto imagen : listaImagenes) {
                                if (imagen.getProducto().getIdProducto() == producto.getIdProducto()) {
                                    byte[] imagenBytes = imagen.getImagen();
                                    String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
                                    
                            %>
                            <img src="data:image/jpeg;base64,<%=base64Image%>" alt="Imagen del Producto" style="width:calc(100% - 10px);"/>
                            <%
                                break;
                                }
                            }
                            %>
                            </td>
                    </tr>                
                    <%
                            }
                        }
                    %>
                    </table>
                </article>
            </div>
        </div>


    </main>

    <!--se crea modal con el registro de productos-->
    <input type="checkbox" id="btn-modal">
    
    <div class="modal">
        <div class="contenedor">         
            <label for="btn-modal" class="btn-cerrar">X</label>
            <div id="cabecera_modal"><h1>Registro</h1></div>
            <form action="../SvRegistroProductos" method="POST" enctype="multipart/form-data">
                <div class="contenedor_inputs">
                    <div class="fila-input">
                        <input name="nombre" class="campo_producto" type="text" placeholder="Nombre" required>
                        <input name="marca" class="campo_producto" type="text" placeholder="Marca" required>
                    </div>
                    <div class="fila-input">
                        <input name="descripcion" class="campo_producto" type="text" placeholder="Descripción" required>
                        <input name="precio" class="campo_producto" type="number" placeholder="Precio" required>
                    </div>
                    <div class="fila-input">
                        
                        <!-- dentro del atributo value, ira el id de la categoria y proveedor respectivamente-->
                        <!-- al momento de validar su seleccion, comprobar que sean distintos de 0 -->
                        <select name="categoria" required>
                            <option value="0" disabled selected>Categoría</option>
                            
                            <%
                                List<Categoria> listaCategorias = new ArrayList<>();
                                listaCategorias = ctrl_logica.traerCategorias();
                                Collections.sort(listaCategorias, (p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
                                for (Categoria categoria : listaCategorias) {
                            %>
                            
                            <option value="<%=categoria.getIdCategoria()%>"><%=categoria.getNombre()%></option>
                            
                            <%
                                }
                            %>
                            
                        </select>
                        <select name="proveedor" required style="max-width: 240px;">
                            <option value="0" disabled selected>Proveedor</option>
                            <%
                                List<Proveedor> listaProveedores = new ArrayList<>();
                                listaProveedores = ctrl_logica.traerProveedores();
                                Collections.sort(listaProveedores, (p1, p2) -> p1.getRazonSocial().compareTo(p2.getRazonSocial()));
                                for (Proveedor proveedor : listaProveedores){
                            %>
                            <option value="<%=proveedor.getIdProveedor()%>"><%=proveedor.getRazonSocial()%></option>
                            <%
                                }
                            %>
                        </select>
                        
                    </div>
                    <input name="categoria_opcional" class="campo_producto" type="text" placeholder="Categoria (Opcional)">
                    <!--permite subir solo imagenes-->
                    <label id="subir_imagenes">Subir imagenes:</label>
                    <div class="fila-input">
                        <input name="imagenes" id="fileInput" type="file" accept="image/*" multiple required>
                        <ul id="fileList"></ul>
                    </div>
                    
                    <!--boton de registrar-->
                    <button id="btn-registrar" type="submit" class="btn">Registrar</button>
                
                </div>
                
                
            </form>
        </div>
    </div>

    
    <footer>
        
      

    </footer>
    <script src="../JavaScript/buscar_productos.js"></script>
</body>
</html>
