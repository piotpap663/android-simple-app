package com.example.i354889.lab1.model.enumerator;

public enum ShapeType {
    CIRCLE("KOłO"),
    TRIANGLE("TRÓJKĄT"),
    SQUARE("KWADRAT");

    private String name;

     ShapeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
