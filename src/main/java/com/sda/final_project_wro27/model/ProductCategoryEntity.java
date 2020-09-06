package com.sda.final_project_wro27.model;

import com.sda.final_project_wro27.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
public class ProductCategoryEntity extends BaseEntity {

    private String title;

    public ProductCategoryEntity(String title) {
        this.title=title;
    }

    public ProductCategoryEntity() {

    }

    public String getTitle() {
        return title;
    }

}
