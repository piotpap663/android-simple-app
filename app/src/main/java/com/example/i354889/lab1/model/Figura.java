package com.example.i354889.lab1.model;

import com.example.i354889.lab1.model.enumerator.ShapeType;

public abstract class Figura {

    //(1)
    // POLA charakteryzujace wspolne wlasnosci wszystkich figur
    protected float wymiarLiniowy;
    protected String name;
    //


    // (2)
    // METODY definiujace dostep do pol klasy Figura tzw. getters i setters
    //np.
    public abstract float getWymiarLiniowy();

    public abstract String getName();

    public abstract ShapeType getShape();

    public abstract String getZaleznosc();

    public abstract float getWartoscZaleznosci();


    //(3)
    // deklaracje METOD ABSTRAKCYJNYCH (tylko nazwa i argumenty)
    // sa to metody, ktore sa wykorzystywane dla wszystkich typow figur, ale zaleznie od rodzaju figury nalezy zastosowac inna definicje metody
    // metody abstrakcyjne sa definiowane w klasach pochodnych (klasach dziedziczacych z klasy Figura)
    //np.
    public abstract double pole();

    // itd.
    //

}
