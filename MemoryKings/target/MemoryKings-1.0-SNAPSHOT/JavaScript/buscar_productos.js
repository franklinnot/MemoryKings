function searchProduct() {

    // Obtener el valor del input de búsqueda
    const searchValue = document.getElementById('Buscar').value;

    // Obtener todos los elementos <tr> dentro de la tabla
    const productList = document.getElementById('productList');
    const products = productList.getElementsByTagName('tr');

    // Variable para indicar si se encontró el producto
    let productFound = false;
    
    // Recorrer todos los productos y buscar el código
    for (let i = 0; i < products.length; i++) {
        const product = products[i];
        const productCode = product.getAttribute('data-code');

        if (productCode === searchValue) {
            // Si se encuentra el producto, mostrar solo este
            productList.innerHTML =  '';
            productList.appendChild(product);
            productFound = true;
            break;
        }
    }

    // Si no se encuentra el producto, mostrar un mensaje
    if (!productFound) {
        productList.innerHTML = '<tr><p colspan="7">No se encontró el producto.</p></tr>';
    }
}
