package com.example.i354889.lab1.model;

import com.example.i354889.lab1.R;
import com.example.i354889.lab1.model.enumerator.ShapeType;

public class DisplayFigura {

    private int drawablePath;
    private double pole;
    private float cecha;

    public DisplayFigura(ShapeType shapeType, double pole, float cecha) {
        switch (shapeType){
            case CIRCLE:
                this.drawablePath = R.drawable.circle;
                break;
            case SQUARE:
                this.drawablePath = R.drawable.square;
                break;
            case TRIANGLE:
                this.drawablePath = R.drawable.triangle;
                break;
        }
        this.pole=pole;
        this.cecha=cecha;
    }

    public int getDrawablePath() {
        return drawablePath;
    }

    public void setDrawablePath(int drawablePath) {
        this.drawablePath = drawablePath;
    }

    public double getPole() {
        return pole;
    }

    public void setPole(double pole) {
        this.pole = pole;
    }

    public float getCecha() {
        return cecha;
    }

    public void setCecha(float cecha) {
        this.cecha = cecha;
    }
}
