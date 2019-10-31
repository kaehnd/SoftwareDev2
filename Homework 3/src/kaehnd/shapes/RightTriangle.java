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
 * Class representing a Triangle with one right angle
 */
public class RightTriangle extends Triangle {

    private static final double RIGHT_ANGLE = 90;


    /**
     * Constructs a right triangle using Triangle super class constructor
     * @param height length of one of two triangle sides not the hypotenuse
     * @param base length of the other of two triangle sides not the hypotenuse
     * @param angleHypHt degree measure of angle between height and hypotenuse
     * @param angleHypBase degree measure of angle between base and hyptenuse
     */
    public RightTriangle(double height, double base, double angleHypHt, double angleHypBase){
        super(angleHypHt, angleHypBase, RIGHT_ANGLE, base,
                0, height);
        double hyp = Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));
        sideA = hyp;
    }

    /**
     * Displays all triangle
     */
    public void display(){
        System.out.println(Color.YELLOW + "Right Triangle:\nSide A: " + sideA + "\nSide B: " +
                sideB + "\nBase: " + base + "\nAngleAB: " + angleAB +
                "\nAngleBaseB: " + angleBaseB + "\nAngleBaseA: " + angleBaseA +
                "\nArea " + DISPLAY_FORMAT.format(getArea()) + "\n");
    }

}
