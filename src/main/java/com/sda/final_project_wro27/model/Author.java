package com.sda.final_project_wro27.model;


import com.sda.final_project_wro27.BaseEntity;
import com.sda.final_project_wro27.dto.ProductDto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Author {

    private String firstName;
    private String lastName;

    public static Author apply(ProductDto productDto) {
        Author author = new Author();
        author.firstName = productDto.getAuthorFirstName();
        author.lastName = productDto.getAuthorLastName();
        return author;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
