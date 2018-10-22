package com.example.i354889.lab1.model;

import com.example.i354889.lab1.model.enumerator.ShapeType;

public class Trojkat extends Figura{

    public Trojkat(float wymiarLiniowy){
        this.wymiarLiniowy=wymiarLiniowy;
        // zawartosc konstruktora - operacje wykonane podczas tworzenia obiektu
    }

    @Override
    public String getName(){
        return ShapeType.TRIANGLE.getName();
    }

    @Override
    public ShapeType getShape() {
        return ShapeType.TRIANGLE;
    }

    @Override
    public String getZaleznosc() {
        return "wysokości";
    }

    @Override
    public double pole() {
        return ((this.wymiarLiniowy * this.wymiarLiniowy) * Math.sqrt(3))/4;
    }
    @Override
    public float getWymiarLiniowy() {
        return this.wymiarLiniowy;
    }

    @Override
    public float getWartoscZaleznosci() {
        return (float)((this.wymiarLiniowy * Math.sqrt(3)) / 2);
    }
}
