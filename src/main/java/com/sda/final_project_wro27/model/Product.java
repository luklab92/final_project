package com.sda.final_project_wro27.model;

import com.sda.final_project_wro27.BaseEntity;
import com.sda.final_project_wro27.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Getter
@Entity
public class Product extends BaseEntity {

    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne
    private ProductCategoryEntity category;
    private BigDecimal price;
    private ProductType productType;
    @Embedded
    private Author author;

    public static Product apply(ProductDto productDto) {
        Product product = new Product();
        product.title= productDto.getProductTitle();
        product.description= productDto.getProductDescription();
        product.imageUrl= productDto.getImageUrl();
        product.price= productDto.getPrice();
        product.author = Author.apply(productDto);
        product.productType = ProductType.valueOf(productDto.getProductType());
        return product;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryEntity category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
