package com.sda.final_project_wro27.dto;

import com.sda.final_project_wro27.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public class ProductDto {

    private String productTitle;
    private String productDescription;
    private String imageUrl;
    private Long productCategoryId;
    private String productCategoryTitle;
    private BigDecimal price;
    private String productType;
    private String authorFirstName;
    private String authorLastName;


    public static ProductDto apply(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductTitle(product.getTitle());
        productDto.setProductDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setProductCategoryId(product.getCategory().getId());
        productDto.setProductCategoryTitle(product.getCategory().getTitle());
        productDto.setProductType(product.getProductType().getName());
        productDto.setAuthorFirstName(product.getAuthor().getFirstName());
        productDto.setAuthorLastName(product.getAuthor().getLastName());

        return productDto;

    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryTitle() {
        return productCategoryTitle;
    }

    public void setProductCategoryTitle(String productCategoryTitle) {
        this.productCategoryTitle = productCategoryTitle;
    }
}