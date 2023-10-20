package com.example.demo;

public class Product {

    public String code;
    public String name;
    public String description;
    public String id_supplier;

    public Product(String code, String name, String description, String id_supplier) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.id_supplier = id_supplier;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }
}
