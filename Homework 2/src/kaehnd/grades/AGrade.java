/*
 * Course: CS1021 - 021
 * Winter 2019
 * Homework 2 - Grade Inheritance
 * Name: Daniel Kaehn
 * Created: 12/3/2018
 */
package kaehnd.grades;

import kaehnd.color.Color;

/**
 * Grade subclass for A Grade
 */
public class AGrade extends Grade {

    /**
     * Minimum grade percentage for AGrade
     */
    public static final int MIN = 92;

    /**
     * Constructor for AGrade objects
     * @param totalPoints points earned
     * @param pointsPossible points possible
     */
    public AGrade(double totalPoints, double pointsPossible){
        super(totalPoints, pointsPossible);
    }

    /**
     * toString for AGrade class
     * @return String of AGrade percentage in BLUE
     */
    @Override
    public String toString() {
        return Color.BLUE + super.toString();
    }
}
