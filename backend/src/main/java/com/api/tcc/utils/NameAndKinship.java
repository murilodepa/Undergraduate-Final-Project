package com.api.tcc.utils;

public class NameAndKinship {
    private String name;
    private String kinship;

    public NameAndKinship(String name, String kinship) {
        this.name = name;
        this.kinship = kinship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }
}
