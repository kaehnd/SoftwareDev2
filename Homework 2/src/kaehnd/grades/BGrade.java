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
 * Grade subclass for B Grade
 */
public class BGrade extends Grade {

    /**
     * Minimum grade percentage for BGrade
     */
    public static final int MIN = 82;

    /**
     * Constructor for BGrade objects
     * @param totalPoints points earned
     * @param pointsPossible points possible
     */
    public BGrade(double totalPoints, double pointsPossible){
        super(totalPoints, pointsPossible);
    }

    /**
     * toString for BGrade class
     * @return String of BGrade percentage in GREEN
     */
    @Override
    public String toString() {
        return Color.GREEN + super.toString();
    }
}
