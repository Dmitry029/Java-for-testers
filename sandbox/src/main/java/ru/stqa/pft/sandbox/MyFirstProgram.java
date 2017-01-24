package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(2, 3);

    Point p2 = new Point(2, 12);

    System.out.println("Расстояние между точками = " + p2.distance(p1));

  }

}
