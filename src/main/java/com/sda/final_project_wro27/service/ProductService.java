package com.sda.final_project_wro27.service;

import com.sda.final_project_wro27.dto.ProductDto;
import com.sda.final_project_wro27.model.Product;
import com.sda.final_project_wro27.model.ProductCategoryEntity;
import com.sda.final_project_wro27.repository.ProductCategoryRepository;
import com.sda.final_project_wro27.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService ;

    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
    }

    public void addProduct(ProductDto productDto) {
        Product productEntityToSave = Product.apply(productDto);
        ProductCategoryEntity category = productCategoryService.findCategoryById(productDto.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("Can't find category"));
        productEntityToSave.setCategory(category);
        productRepository.save(productEntityToSave);
    }


//    public List<ProductDto> getAllProducts() {
//        return productRepository.findAll().stream()
//                .map(ProductDto::apply)
//                .collect(Collectors.toList());
//    }

    public Page<ProductDto> findProducts(Pageable pageable) {

        Page<Product> page = productRepository.findAll(pageable);
        List<ProductDto> collect = page.stream()
                .map(ProductDto::apply)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, page.getTotalElements());
    }
}



