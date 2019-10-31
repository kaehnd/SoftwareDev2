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
 * Object representing a Parallelogram where all angles are right angles
 */
public class Rectangle extends Parallelogram {


    protected static final double RIGHT_ANGLE = 90; //Degree measure of a right angle

    /**
     * Constructs a Rectangle using the constructor of the Parallelogram class
     * @param longerSide length of longer two sides
     * @param shorterSide length of shorter two sides
     */
    public Rectangle(double longerSide, double shorterSide){
        super(longerSide, shorterSide, RIGHT_ANGLE, RIGHT_ANGLE);
    }

    /**
     * Calculates the area of the rectangle
     * @return area of a rectangle
     */
    public double getArea(){
        return sideA * sideB;
    }

    /**
     * Displays all Rectangle attributes and area
     */
    public void display(){
        System.out.println(Color.GREEN + "Rectangle: \nLonger side: " + sideA +
                "\nShorter side: " + sideB + "\nArea: " + DISPLAY_FORMAT.format(getArea()) + "\n");
    }
}
