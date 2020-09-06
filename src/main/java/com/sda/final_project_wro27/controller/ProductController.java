package com.sda.final_project_wro27.controller;

import com.sda.final_project_wro27.dto.ProductDto;
import com.sda.final_project_wro27.model.ProductType;
import com.sda.final_project_wro27.service.ProductCategoryService;
import com.sda.final_project_wro27.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;


    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }
   /* public List<ProductDto> getProductList() {
        ProductDto productDto = new ProductDto();
        productDto.setAuthorFirstName("kos");
        productDto.setAuthorLastName("kos2");
        productDto.setProductTitle("Ziemniak");
        productDto.setPrice(BigDecimal.valueOf(5000000000L));

        ProductDto productDto2 = new ProductDto();
        productDto2.setAuthorFirstName("kosiasty");
        productDto2.setAuthorLastName("kosiasty2");
        productDto2.setProductTitle("Pyra");
        productDto2.setPrice(BigDecimal.valueOf(2));

        return List.of(productDto,productDto2);
    }*/

    @GetMapping("/all")
    public ModelAndView getProduct(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("showProductList");
        modelAndView.addObject("products",productService.findProducts(pageable));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView saveProductForm() {
        ModelAndView modelAndView = new ModelAndView("productAdd");
ProductDto productDto = new ProductDto();
        modelAndView.addObject("productToSave", productDto);
        modelAndView.addObject("productCategories", productCategoryService.findProductCategory());
        modelAndView.addObject("productTypes", ProductType.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public String saveProduct(ProductDto productDto) {
        productService.addProduct(productDto);
        return "redirect:/product/all";
    }
}
