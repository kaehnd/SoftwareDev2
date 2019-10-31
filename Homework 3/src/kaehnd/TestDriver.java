/*
 * Course: CS1021 - 001
 * Winter 2019
 * Homework 3 - Shapes
 * Name: //todo
 * Created: 12/07/2018
 */
package kaehnd;

import kaehnd.color.Color;
import kaehnd.shapes.Circle;
import kaehnd.shapes.Parallelogram;
import kaehnd.shapes.Rectangle;
import kaehnd.shapes.RightTriangle;
import kaehnd.shapes.Shape;
import kaehnd.shapes.Square;
import kaehnd.shapes.Triangle;

import java.util.ArrayList;

/**
 * Test Driver for the Shapes Homework
 */
@SuppressWarnings("WeakerAccess")
public class TestDriver {
    @SuppressWarnings("CheckStyle")
    public static void main(String[] args) {
        // Create shapes (ignoring magic numbers)
        Shape s1 = new Parallelogram(24, 16, 65, 115);
        Shape s2 = new RightTriangle(4, 3, 45, 45);
        Shape s3 = new Rectangle(6.8, 4.3);
        Shape s4 = new Triangle(5, 7, 9, 26, 110, 44);
        Shape s5 = new Circle(6);
        Shape s6 = new Square(5);

        // Add the shapes to a list
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(s1);
        shapes.add(s2);
        shapes.add(s3);
        shapes.add(s4);
        shapes.add(s5);
        shapes.add(s6);

        // Print all the shapes
        for(Shape s : shapes) {
            s.display();
            System.out.print(Color.RESET);
        }

        // Shapes with the same area should be equal
        Shape s7 = new Circle(6);
        System.out.println(Color.RED + "" + s7.equals(s5) + Color.RESET);
    }
}