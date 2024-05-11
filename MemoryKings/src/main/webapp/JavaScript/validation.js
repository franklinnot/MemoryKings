
// Metodos de validacion de entrada con JavaScript, 

function validarDNI(input) {
    // Solo numeros :)
    input.value = input.value.replace(/\D/g, '');

    // limitamos a solo 8 caracteres
    if (input.value.length > 8) {
            input.value = input.value.slice(0, 8);
        }
}

function validarNombre(input) {
    //  letras y espacios
    input.value = input.value.replace(/[^a-zA-Z\s]/g, '');

    // solo 25 caracteres
    if (input.value.length > 25) {
        input.value = input.value.slice(0, 25);
    }
}

function validarTelefono(input){
    // solo numeros
    input.value = input.value.replace(/\D/g, '');
    
    // solo 9 caracteres
    if (input.value.length > 9) {
            input.value = input.value.slice(0, 9);
        }
}


function validarCorreo(input) {
    // Eliminar caracteres no válidos para un correo electrónico
    input.value = input.value.replace(/[^\w@.]/g, '');

    // Limitar la longitud a 30 caracteres
    if (input.value.length > 30) {
        input.value = input.value.slice(0, 30);
    }
}



function validarDireccion(input) {

    // solo 35 caracteres
    if (input.value.length > 30) {
        input.value = input.value.slice(0, 30);
    }
}




