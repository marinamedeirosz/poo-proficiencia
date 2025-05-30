package com.marina.model;

public class Specialty {
    private String description;
    private final String id;

    public Specialty(String description, String id) {
        this.description = description;
        this.id = id;
    }
    public Specialty() {
        this.description = "";
        this.id = "";
    }
    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Descrição: " + description;
    }
}