// Obtenemos los elementos de los modales y botones
var modalEmpleado = document.getElementById("modalEmpleado");
var modalProveedor = document.getElementById("modalProveedor");
var modalDistribuidor = document.getElementById("modalDistribuidor")
var btnAbrirModalE = document.getElementById("btnEmpleado");
var btnAbrirModalP = document.getElementById("btnProveedor");
var btnAbrirModalD = document.getElementById("btnDistribuidor");

var spanCerrarEmpleado = modalEmpleado.getElementsByClassName("close")[0];
var spanCerrarProveedor = modalProveedor.getElementsByClassName("close")[0];
var spanCerrarDistribuidor = modalDistribuidor.getElementsByClassName("close")[0];

// Función para abrir el modal
btnAbrirModalE.onclick = function() {
    modalEmpleado.style.display = "block";
}
btnAbrirModalP.onclick = function() {
    modalProveedor.style.display = "block";
}
btnAbrirModalD.onclick = function() {
    modalDistribuidor.style.display = "block";
}
// Función para cerrar los modales al hacer clic en la 'x'
spanCerrarEmpleado.onclick = function() {
    modalEmpleado.style.display = "none";
}
spanCerrarProveedor.onclick = function() {
    modalProveedor.style.display = "none";
}
spanCerrarDistribuidor.onclick = function() {
    modalDistribuidor.style.display = "none";
}
// Función para enviar el registro
function enviarRegistro(tipo) {
    var modal, campos;

    if (tipo === 'Empleado') {
        modal = modalEmpleado;
        campos = document.querySelectorAll("#modalEmpleado .input-field input, #modalEmpleado .input-field select");
    } else if (tipo === 'Proveedor') {
        modal = modalProveedor;
        campos = document.querySelectorAll("#modalProveedor .input-field input, #modalProveedor .input-field select");
    } else if (tipo == 'Distribuidor'){
        modal = modalDistribuidor;
        campos = document.querySelectorAll("#modalDistribuidor .input-field input, #modalDistribuidor .input-field select");
    }

    var todosLlenos = true;
    campos.forEach(function(campo) {
        if (!campo.value) {
            todosLlenos = false;
        }
    });

    if (todosLlenos) {
        
        // Limpiar los campos
        campos.forEach(function(campo) {
            if (campo.tagName === "SELECT") {
                campo.selectedIndex = 0;
            } else {
                campo.value = "";
            }
        });

        // Cerrar el modal
        modal.style.display = "none";
    } else {
        
    }
}