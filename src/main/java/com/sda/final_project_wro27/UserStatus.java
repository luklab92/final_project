package com.sda.final_project_wro27;

import javax.persistence.Entity;

@Entity
public class UserStatus extends BaseEntity{

    private String status;


    public static UserStatus apply(Status status) {
        UserStatus userStatus = new UserStatus();
        userStatus.status = status.name();
        return userStatus;
    }

    public enum Status {
        ACCEPTED, NOT_ACCEPTED
    }

    @Override
    public String toString() {
        return status;
    }
}
