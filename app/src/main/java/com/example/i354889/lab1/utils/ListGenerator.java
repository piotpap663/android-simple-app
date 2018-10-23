package com.example.i354889.lab1.utils;

import com.example.i354889.lab1.model.DisplayFigura;
import com.example.i354889.lab1.model.Figura;

import java.util.ArrayList;
import java.util.List;

public class ListGenerator  {

    public static List<DisplayFigura> getList(int size) {
        Figura []shapes = FigureGenerator.getFigures(size);
        List<DisplayFigura> listOfShapes = new ArrayList<>();
        for (int i=0;i<size;i++){
            listOfShapes.add(new DisplayFigura(shapes[i].getShape(),shapes[i].pole(),shapes[i].getWartoscZaleznosci()));
        }
        return  listOfShapes;

    }
}
