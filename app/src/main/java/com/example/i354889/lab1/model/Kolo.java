package com.example.i354889.lab1.model;

import com.example.i354889.lab1.model.enumerator.ShapeType;

public class Kolo extends Figura{
    public Kolo(float wymiarLiniowy){
        this.wymiarLiniowy=wymiarLiniowy;
        // zawartosc konstruktora - operacje wykonane podczas tworzenia obiektu
    }

    @Override
    public String getName(){
        return ShapeType.CIRCLE.getName();
    }

    @Override
    public ShapeType getShape() {
        return ShapeType.CIRCLE;
    }

    @Override
    public String getZaleznosc() {
        return "średnicy";
    }

    @Override
    public double pole() {
        return (this.wymiarLiniowy * this.wymiarLiniowy) * Math.PI;
    }
    @Override
    public float getWymiarLiniowy() {
        return this.wymiarLiniowy;
    }

    @Override
    public float getWartoscZaleznosci() {
        return (this.wymiarLiniowy * 2);
    }
}
