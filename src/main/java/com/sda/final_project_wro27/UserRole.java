package com.sda.final_project_wro27;

import javax.persistence.Entity;


@Entity
public class UserRole extends BaseEntity {

    private String role;

    public String getRole() {
        return role;
    }
}
