package com.sda.final_project_wro27;


import java.util.Arrays;

public enum Countries {
    POLAND("POLSKA", "PL"),
    ENGLAND("ANGLA", "EN"),
    GERMANY("NIEMCY", "DE"),
    UKRAINE("UKRAINA", "UA");

    private final String plName;
    private final String symbol;

    Countries(String plName, String symbol) {
        this.plName = plName;
        this.symbol = symbol;
    }

    public String getPlName() {
        return plName;
    }

    public String getSymbol() {
        return symbol;
    }
    public static Countries getCountries(String smb){
        return Arrays.stream(Countries.values())
                .filter(c->c.symbol.equals(smb))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Nie znaleziono kraju"));
    }
}
