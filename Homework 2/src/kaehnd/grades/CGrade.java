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
 * Grade subclass for C Grade
 */
public class CGrade extends Grade {

    /**
     * Minimum grade percentage for CGrade
     */
    public static final int MIN = 74;

    /**
     * Constructor for CGrade objects
     * @param totalPoints points earned
     * @param pointsPossible points possible
     */
    public CGrade(double totalPoints, double pointsPossible){
        super(totalPoints, pointsPossible);
    }

    /**
     * toString for CGrade class
     * @return String of CGrade percentage in Cyan
     */
    @Override
    public String toString() {
        return Color.CYAN + super.toString();
    }
}
