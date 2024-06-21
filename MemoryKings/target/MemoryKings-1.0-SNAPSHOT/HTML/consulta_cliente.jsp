

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="CSS/normalize.css">
    <link rel="stylesheet" href="CSS/consulta_cliente.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultas</title>
</head>
<body>
    <div class="div_principal"> 
        <div class="logo_container">
            <img id="loguito" src="Image/logo.png" alt="Logo">
        </div>
        <form id="consultaForm"  style="
        min-width: -webkit-fill-available;
        display: contents;
    "> 
            <div class="div_division1">
                <div class="div_inputs">
                    <label id="dnilbl"><b>DNI:</b> 09871234</label>
                    <label id="nomlbl"><b>Nombres:</b> Martin</label>
                    <label id="apelbl"><b>Apellidos:</b> Vizcarra</label>
                    <label id="colbl"><b>Correo:</b> VizcaMartin@presi.com</label>
                </div>
                <div class="div_combobox">
                    <label id="escoger" for="cbtiposervicio">Escoge el tipo de servicio: </label>
                    <select id="cbtiposervicio">
                        <option value="" selected disabled>---Seleccionar Tipo---</option>
                        <option value="Garantia">Garantia</option>
                        <option value="Soporte Tecnico">Soporte Tecnico</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
            </div>
            <div class="div_textarea">
                <label id="lbldescripcion" for="descripcion"><b>Descripción de su consulta:</b></label>
                <textarea id="descripcion" placeholder="Describe tu problema"></textarea>
            </div>
            <div class="div_botones">
                <button id="btregresar" type="button" onclick="window.history.back();">REGRESAR</button>
                <button id="btenviar" type="submit">ENVIAR CONSULTA</button>   
            </div>  
        </form>
        <div id="consultas-container">
            <div class="consulta">
                <h2>CAS-A-0001</h2>
                <div class="fecha">Fecha de registro: 02/02/2024 16:31</div>
                <div class="estado">Estado: Caso resuelto</div>
                <h3>Categoria: Garantia</h3>
                <p>Consulta: de datos como para electrónica digital, por favor, las inscripciones empezaron recién en estos días, y que no me figuren horarios me parece raro.</p>
                <button class="boton-seguimiento">SEGUIMIENTO</button>
            </div>
            <div class="consulta">
                <h2>CAS-A-0001</h2>
                <div class="fecha">Fecha de registro: 02/02/2024 16:31</div>
                <div class="estado">Estado: Caso resuelto</div>
                <h3>Categoria: Garantia</h3>
                <p>Consulta: Buen día. Andaba revisando horarios para la inscripción de mis cursos, y llevo días esperando, me sale que no hay horarios disponibles, tanto para base de datos como para electrónica digital, por favor, las inscripciones empezaron recién en estos días, y que no me figuren horarios me parece raro.</p>
                <button class="boton-seguimiento">SEGUIMIENTO</button>
            </div>
        </div>
    </div>
    <script src="/JavaScript/Scripts.jS"></script>
</body>
</html>
