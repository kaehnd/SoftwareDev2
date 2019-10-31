/*
 * Course: CS1021
 * Winter 2018
 * Assignment: Homework 3
 * Name: Daniel Kaehn
 * Created: 12/10/2018
 */
package kaehnd.shapes;

import java.text.DecimalFormat;

/**
 * Interface for Shape objects, which will implement display and getArea methods,
 * and use the DISPLAY_FORMAT DecimalFormat object.
 */
public interface Shape {

    /**
     * DecimalFormat object used to format doubles to no more than two decimal places
     */
    DecimalFormat DISPLAY_FORMAT = new DecimalFormat("#.##");

    /**
     * Prints shape parameters and area
     */
    void display();


    /**
     * Calculates and returns area of a shape
     * @return area of Shape object
     */
    double getArea();

}
