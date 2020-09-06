package com.sda.final_project_wro27.service;

import com.sda.final_project_wro27.dto.ProductCategoryDto;
import com.sda.final_project_wro27.model.ProductCategoryEntity;
import com.sda.final_project_wro27.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepo;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    public List<ProductCategoryDto> findProductCategory() {

        return productCategoryRepo.findAll().stream()
                .map(ProductCategoryDto::apply)
                .collect(Collectors.toList());
    }
    public Optional<ProductCategoryEntity> findCategoryById(Long id) {
       return productCategoryRepo.findById(id);
    }
}
