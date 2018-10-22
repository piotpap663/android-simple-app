package com.example.i354889.lab1.utils;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.model.Figura;

import java.util.ArrayList;
import java.util.List;

public class ListGenerator  {
    private static int size = 10;

    public static List<DisplayFigura> getList() {

        Figura []shapes = FigureGenerator.getFigures(size);
        List<DisplayFigura> listOfShapes = new ArrayList<>();
        for (int i=0;i<size;i++){
            listOfShapes.add(new DisplayFigura(shapes[i].getShape(),shapes[i].pole(),shapes[i].getWartoscZaleznosci()));
        }
        return  listOfShapes;

    }
}
