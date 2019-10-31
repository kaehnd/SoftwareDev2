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
 * Class modeling a Quadrilateral with at least two congruent sides
 * and at least two congruent angles
 */
public class Parallelogram extends Quadrilateral {

    /**
     * Constructs a Parallelogram
     * @param longerSide length of longer two sides
     * @param shorterSide length of shorter two sides
     * @param angleA degree measure of two congruent angles
     * @param angleB degree measure of other two congruent angles
     */
    public Parallelogram(double longerSide, double shorterSide, double angleA, double angleB){
        sideA = longerSide;
        sideC = sideA;

        sideB = shorterSide;
        sideD = sideB;

        angleAB = angleA;
        angleCD = angleAB;

        angleBC = angleB;
        angleDA = angleBC;
    }

    /**
     * Displays all Parallelogram attributes and area
     */
    @Override
    public void display() {
        System.out.println(Color.BLUE + "Parallelogram:\nLonger side: " + sideA +
                "\nShorter side: " + sideB + "\nAcute angle: " + Math.min(angleAB, angleBC) +
                "\nObtuse angle: " + Math.max(angleAB, angleBC) + "\nArea: " +
                DISPLAY_FORMAT.format(getArea()) + "\n");
    }

    /**
     * Calculates area of Parallelogram
     * @return area of Parallelogram
     */
    @Override
    public double getArea() {
        return sideA * getHeight();
    }


    //Helper method using trigonometry to determine the height of a parallelogram
    private double getHeight(){
        return sideD * Math.sin(Math.toRadians(angleDA));
    }

    /**
     * Compares equality between parallelograms
     * @param o other object to compare equality
     * @return true if o is a parallelogram and has area equal to area of this parallelogram
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Parallelogram){
            return (this.getArea() == ((Parallelogram) o).getArea());
        } else{
            return false;
        }
    }
}
