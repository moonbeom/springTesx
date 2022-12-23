package com.example.productmanager.controller;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.SpringDataRepository;
import com.example.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;
//타임 리프 쓰는 이유 동적으로 해주기 위해서.

@Controller //서 사이드 렌더링 서버에서
public class ProductController {
    private final ProductService productService;
    private final SpringDataRepository springDataRepository;

    @Autowired
    public ProductController(ProductService productService,
                             SpringDataRepository springDataRepository) {
        this.productService = productService;
        this.springDataRepository = springDataRepository;
    }

    // create form 으로 연결 get 조회 post 등록
    @GetMapping("/products/new")
    public String createForm() {
        return "products/createProductForm";
    }

    //
    @PostMapping("/products/new") // 리소스 생성, 생성 되면
    public String create(ProductForm form) {

        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());


        productService.join(product);

        return "redirect:/";
    }
    @GetMapping("/products")
    public String list(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "products/productList";
    }

    // url에 있는 no
    // form(client가 작성한 데이터) 를 이용하러임

// 문범띠
//    @GetMapping("/products/edit/{no}")
//    public String updateForm(@PathVariable("no") Integer no, @ModelAttribute("product") Product product) {
//
////        model.addAttribute("no", no);
//
//        return "products/productUpdateForm";
//    }

    // 김진욱
    @GetMapping("/products/edit/{no}")
    public String updateForm(@PathVariable("no") Integer no, Model model) {

        Product product = productService.findOne(no).get();
        model.addAttribute("product", product);

        return "products/productUpdateForm";
    }




    // 전체적인 로직에 대한 작동 순서

    // 클라이언트로 부터 요청(url)로 들어온다
    // getmapping으로 받아준다
    // 매개 변수 두개 (url no , model)을 이용한다
    // view(html)에 데이터를 보내주기 위해서 Model model을 이용한다
    // 우리들은 updateform에서 name price stock만 입력할 예정이기 때문에
    // 기본적으로 필요한 PK(no)를 여기서 넣어주기 위해서 (직접 입력을 안하기 때문에) model에 넣어준것
    // 이런 기본 작업이 끝났다고 한다면 이제
    // 컨트롤러에서 view를 반환 해주면 된다


    // 이제 view(client)로부터 받은 데이터를
    // db로 넘겨줘야함
    @PostMapping("/products/edit/{no}")
    public String update(@PathVariable("no") Integer no, ProductForm form) {
       Product product = new Product();
       product.setName(form.getName());
       product.setPrice(form.getPrice());
       product.setStock(form.getStock());
       product.setNo(no);
       //여기까지 한것 : 받은 데이터를 가공을 한거임
        // 그럼 이제 service로 보내줘야함
        productService.update(product);
        return "redirect:/products";

    }



    @GetMapping("/products/{no}/delete")
    public String delete(@PathVariable("no") Integer no) {
        Product product = new Product();
        product.setNo(product.getNo());
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());

        productService.delete(no);
        return "redirect:/products";
    }






}
