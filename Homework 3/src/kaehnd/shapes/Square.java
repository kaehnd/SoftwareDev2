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
 * Class representing a Rectangle whose four side lengths are equal
 */
public class Square extends Rectangle {

    /**
     * Constructs a Square using the Rectangle super class constructor
     * @param side side length
     */
    public Square(double side){
        super(side, side);
    }

    /**
     * Displays all attributes for a Square and its area
     */
    public void display(){
        System.out.println(Color.CYAN + "Square:\nSide: " + sideA + "\nArea: " +
                DISPLAY_FORMAT.format(getArea()) + "\n");
    }
}
