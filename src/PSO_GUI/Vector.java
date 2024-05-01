//Tymoteusz
package PSO_GUI;

import javax.swing.*;

public class Vector {
    public double x, y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector other){
        this.x += other.x;
        this.y += other.y;
    }

    public void sub(Vector other){
        this.x -= other.x;
        this.y -= other.y;
    }

    public void mult(double n){
        x *= n;
        y *= n;
    }

    double length(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void normalize(){
        double l = length();
        if(l!=0) this.mult(1/l);
    }

    public void setLength(double n){
        normalize();
        mult(n);
    }

    public void limit(double n){
        if(length() > n) this.setLength(n);
    }

    public void printSelf(){
        System.out.println(x + " "+y);
    }

    //testy
//    public static void main(String[] args){
//        //stworzenie wektorów
//        Vector vector1 = new Vector(2, 5);
//        Vector vector2 = new Vector(3, 7);
//        vector1.printSelf();
//        vector2.printSelf();
//
//        //odejmowanie i limit
//        System.out.println();
//        vector2.sub(vector1);
//        vector2.printSelf();
//        System.out.println(vector2.length());
//        vector2.limit(1.5);
//        vector2.printSelf();
//        System.out.println(vector2.length());
//
//        //dodawanie wektorów
//        System.out.println();
//        vector1.add(vector2);
//        vector1.printSelf();
//
//        //sprawdzenie długości wektorów
//        System.out.println();
//        System.out.println(vector1.length());
//        System.out.println(vector2.length());
//
//
//
//        //normalizacja wektorów
//        System.out.println();
//        vector1.normalize();
//        vector2.normalize();
//        System.out.println(vector1.length());
//        System.out.println(vector2.length());
//        vector1.printSelf();
//        vector2.printSelf();
//    }

}
