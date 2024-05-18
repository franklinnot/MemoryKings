const btnAbrirRegistro = document.getElementById('signUp');
const btnCerrarRegistro = document.getElementById('btn-cerrar');
const modal = document.getElementById('modal');

btnAbrirRegistro.addEventListener('click', function(event) {
    event.preventDefault(); // Evita el envío del formulario
    modal.showModal();
});

btnCerrarRegistro.addEventListener('click', function () {
    modal.close();
});
