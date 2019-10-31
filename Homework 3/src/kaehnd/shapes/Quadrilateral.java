/*
 * Course: CS1021
 * Winter 2018
 * Assignment: Homework 3
 * Name: Daniel Kaehn
 * Created: 12/10/2018
 */
package kaehnd.shapes;

/**
 * Abstract class describing all Shapes with four sides
 */
public abstract class Quadrilateral implements Shape {

    protected double angleAB;
    protected double angleBC;
    protected double angleCD;
    protected double angleDA;

    protected double sideA;
    protected double sideB;
    protected double sideC;
    protected double sideD;

    /**
     * Default constructor for Quadrilateral objects
     */
    public Quadrilateral() { }

}
