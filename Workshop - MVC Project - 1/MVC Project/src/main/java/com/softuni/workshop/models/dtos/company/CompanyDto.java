package com.softuni.workshop.models.dtos.company;

public class CompanyDto {

    private long id;
    private String name;

    public CompanyDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
