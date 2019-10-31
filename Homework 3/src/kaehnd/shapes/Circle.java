/*
 * Course: CS1021
 * Winter 2018
 * Assignment: Homework 3
 * Name: Daniel Kaehn
 * Created: 12/10/2018
 */
package kaehnd.shapes;

import kaehnd.color.Color;

/**
 * Class representing a geometric circle Shape
 */
public class Circle implements Shape {

    private double radius;

    /**
     * Constructs a Circle
     * @param radius radius of the circle
     */
    public Circle(double radius){
        this.radius = radius;
    }

    /**
     * Displays all attributes of a Circle and its area
     */
    @Override
    public void display() {
        System.out.println(Color.BLACK + "Circle:\nRadius: " + radius +
                "\nArea: " + getArea() + "\n");
    }

    /**
     * Compares equality between Circle objects
     * @param o other object to compare equality
     * @return true if o is a Circle and its area is equal to the area of this
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Circle){
            return this.getArea() == ((Circle) o).getArea();
        } else {
            return false;
        }
    }

    /**
     * Calculates the area of the Circle
     * @return area of the Circle
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
