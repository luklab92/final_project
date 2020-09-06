package com.sda.final_project_wro27;

import javax.persistence.Entity;


@Entity
public class UserRole extends BaseEntity {

    private String role;

    public String getRole() {
        return role;
    }

    public static UserRole apply(Roles role) {
        UserRole userRole = new UserRole();
        userRole.role = role.name();
        return userRole;
    }
    public enum Roles {
        ADMIN, USER
    }

    @Override
    public String toString() {
        return role;
    }
}
