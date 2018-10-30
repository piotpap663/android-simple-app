package com.example.i354889.lab1.utils;

import android.content.SharedPreferences;

import com.example.i354889.lab1.model.Figura;
import com.example.i354889.lab1.model.Kolo;
import com.example.i354889.lab1.model.Kwadrat;
import com.example.i354889.lab1.model.Trojkat;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FigureGenerator {

    public static Figura[] getFigures(int number, int min, int max) {


            int a = number;
            Figura[] figury = new Figura[a];

            for(int nr = 0; nr < a; nr = nr + 1){
                int foo = (int)(Math.random() * 3)+1;

                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                switch(foo){
                    case 1:
                        figury[nr]=new Kwadrat((float)randomNum);
                        break;
                    case 2:
                        figury[nr]=new Trojkat((float)randomNum);
                        break;
                    case 3:
                        figury[nr]=new Kolo((float) randomNum);
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
