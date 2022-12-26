const body = document.querySelector('tbody')

function getProducts() {
    // API_URL를 사용해 json 데이터 가져와서
    fetch("/api/products") //얘를 통해서 요청 클라이언트 사이드 랜더링한다.  ,fetch("", POST)GET POST method etc.. api는 타임리프를 사용하지 않는다.
    .then(res => res.json()) // res == List<Product> listProducts
    .then(data => showProducts(data));
}

function showProducts(products) { //동적 html 생성해서 하나하나씩 추가를 하는 방식임.
    body.innerHTML = ''

    products.forEach((product) => {

        productElem = document.createElement('tr')

        productElem.innerHTML =
            `
            <td>${product.no}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>

//            <td> <a class="link-primary" href="#" href="/products/${product.no}/update">등록</a>
//            </td>
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