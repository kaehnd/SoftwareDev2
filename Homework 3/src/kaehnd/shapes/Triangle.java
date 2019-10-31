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
 * Class representing a three-sided polygon
 */
public class Triangle implements Shape {

    protected double angleAB;
    protected double angleBaseA;
    protected double angleBaseB;
    protected double base;
    protected double sideA;
    protected double sideB;

    /**
     * Constructs a Triangle
     * @param sideA length of side A
     * @param sideB length of side B
     * @param base length of base side
     * @param angleAB degree measure of angle between sides A and B
     * @param angleBaseB degree measure of angle between side B and base
     * @param angleBaseA degree measure of angle between side A and base
     */
    public Triangle(double sideA, double sideB, double base,
                    double angleAB, double angleBaseB, double angleBaseA) {
        this.angleAB = angleAB;
        this.angleBaseA = angleBaseA;
        this.angleBaseB = angleBaseB;
        this.base = base;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    /**
     * Displays all attributes and area of Triangle
     */
    @Override
    public void display() {
        System.out.println(Color.MAGENTA + "Triangle:\nSide A: " + sideA + "\nSide B: " +
                sideB + "\nBase: " + base + "\nAngleAB: " + angleAB + "\nAngleBaseB: " +
                angleBaseB + "\nAngleBaseA: " + angleBaseA + "\nAreaL " +
                DISPLAY_FORMAT.format(getArea()) + "\n");
    }

    /**
     * Compares equality between triangles
     * @param o other object with which to compare equality
     * @return true if o is a parallelogram and its area is equal to area of this
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Triangle){
            return this.getArea() == ((Triangle) o).getArea();
        } else {
            return false;
        }
    }

    /**
     * Calculates the area of the Triangle
     * @return area of the Triangle
     */
    public double getArea() {
        return getHeight() * base / 2;
    }

    //Helper method using trigonometry to calculate the height of the triangle
    private double getHeight(){
        return sideA * Math.sin(Math.toRadians(angleBaseA));
    }
}
