// script.js
function openModal(orderId) {
    const orderDetails = {
        "001": {
            dni: "49017935",
            nombre: "Luceriro Rata Hermana",
            telefono: "123456789",
            fecha: "01/06/2024",
            direccion: "Al costado de su vecino",
            correo: "luceriro@example.com",
            productos: [
                { nombre: "Producto 1", precio: "10.00" },
                { nombre: "Producto 2", precio: "20.00" }
            ],
            total: "30.00"
        },
        "002": {
            dni: "70549588",
            nombre: "Anira Super Pro LegendariaS",
            telefono: "987654321",
            fecha: "05/06/2024",
            direccion: "Mi casa",
            correo: "anira@example.com",
            productos: [
                { nombre: "Producto A", precio: "15.00" },
                { nombre: "Producto B", precio: "25.00" }
            ],
            total: "40.00"
        }
    };

    const order = orderDetails[orderId];

    document.getElementById("modal-nro-pedido").innerText = orderId;
    document.getElementById("modal-dni").innerText = order.dni;
    document.getElementById("modal-nombre").innerText = order.nombre;
    document.getElementById("modal-telefono").innerText = order.telefono;
    document.getElementById("modal-fecha").innerText = order.fecha;
    document.getElementById("modal-direccion").innerText = order.direccion;
    document.getElementById("modal-correo").innerText = order.correo;
    
    const productosTable = document.getElementById("modal-productos");
    productosTable.innerHTML = "";
    order.productos.forEach(producto => {
        const row = document.createElement("tr");
        const nombreCell = document.createElement("td");
        const precioCell = document.createElement("td");

        nombreCell.innerText = producto.nombre;
        precioCell.innerText = producto.precio;

        row.appendChild(nombreCell);
        row.appendChild(precioCell);

        productosTable.appendChild(row);
    });

    document.getElementById("modal-total").innerText = order.total;
    
    document.getElementById("detalleModal").style.display = "block";
}

function closeModal() {
    document.getElementById("detalleModal").style.display = "none";
}

function enviarPedido() {
    alert("Pedido enviado con éxito!");
    closeModal();
}
