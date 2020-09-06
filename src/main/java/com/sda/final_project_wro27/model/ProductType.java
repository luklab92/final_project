package com.sda.final_project_wro27.model;

public enum ProductType {
    EATABLE("jadalne"),
    NOT_EATABLE("niejadalne");

    public String plName;

    ProductType(String plName) {
        this.plName=plName;
    }

    public String getName() {
        return plName;
    }
}
