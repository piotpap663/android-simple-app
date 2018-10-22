package com.example.i354889.lab1.model;

import com.example.i354889.lab1.model.enumerator.ShapeType;

public class Kwadrat extends Figura{

    //(1)
    // dodatkowe POLA potrzebne do okreslenia stanu Kwadratu;


    //(2)
    //KONSTRUKTOR - sposob inicjalizowania obiektu
    public Kwadrat(float wymiarLiniowy){
        this.wymiarLiniowy=wymiarLiniowy;
        // zawartosc konstruktora - operacje wykonane podczas tworzenia obiektu
    }

    @Override
    public String getName() {
        return ShapeType.SQUARE.getName();
    }

    @Override
    public ShapeType getShape() {
        return ShapeType.SQUARE;
    }

    @Override
    public double pole() {
        return this.wymiarLiniowy * this.wymiarLiniowy ;
        // zawartosc metody - operacje ktore trzeba wykonac aby wyznaczyc pole kwadratu
    }
    @Override
    public String getZaleznosc() {
        return "przekątnej";
    }

    @Override
    public float getWymiarLiniowy() {
        return this.wymiarLiniowy;
    }

    @Override
    public float getWartoscZaleznosci() {
        return (float)(wymiarLiniowy * Math.sqrt(2));
    }


    // wszystkie metody abstrakcyjne klasy bazowej musza byc zdefiniowane w tej klasie
    //itd.
}
