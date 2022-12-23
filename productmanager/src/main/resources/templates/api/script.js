const body = document.querySelector('tbody')

function getProducts() {
    // API_URL를 사용해 json 데이터 가져와서
    fetch("/api/products")
    .then(res => res.json())
    .then(data => showProducts(data));
}

function showProducts(products) {
    body.innerHTML = ''

    products.forEach((product) => {

        productElem = document.createElement('tr')

        productElem.innerHTML =
            `
            <td>${product.no}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>
            <td>
                <a class="link-primary" href="#" href="/products/${product.no}/update">수정</a>
            </td>
            <td>
                <a class="link-primary" href="#" href="/products/${product.no}/delete">삭제</a>
            </td>`

        body.append(productElem);
    })

}
getProducts()