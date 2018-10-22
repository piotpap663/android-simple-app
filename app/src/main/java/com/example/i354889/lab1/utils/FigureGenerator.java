package com.example.i354889.lab1.utils;

import com.example.i354889.lab1.model.Figura;
import com.example.i354889.lab1.model.Kolo;
import com.example.i354889.lab1.model.Kwadrat;
import com.example.i354889.lab1.model.Trojkat;

public class FigureGenerator {

    public static Figura[] getFigures(int number) {

            int a = number;
            Figura[] figury = new Figura[a];

            for(int nr = 0; nr < a; nr = nr + 1){
                int foo = (int)(Math.random() * 3)+1;
                //System.out.println(foo);
                switch(foo){
                    case 1:
                        figury[nr]=new Kwadrat((float)Math.random());
                        break;
                    case 2:
                        figury[nr]=new Trojkat((float) Math.random());
                        break;
                    case 3:
                        figury[nr]=new Kolo((float) Math.random());
                        break;
                }



                System.out.format("%f %d. " + figury[nr].getName()+
                                " o polu "+ "%.3f" +
                                " i " + figury[nr].getZaleznosc() +" " +   "%.3f",
                        figury[nr].getWymiarLiniowy(),
                        nr + 1, figury[nr].pole(), figury[nr].getWartoscZaleznosci() );

                System.out.println();
            }


            return figury;
            // System.out.format("%.3f" , figury[nr].pole() );

            // System.out.print(" i średnicy "+ "%.3f" , figury[nr].getWymiarLiniowy() );
            // System.out.println();

        }

}
