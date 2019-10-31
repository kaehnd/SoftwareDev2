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
 * Grade subclass for D Grade
 */
public class DGrade extends Grade {

    /**
     * Minimum grade percentage for DGrade
     */
    public static final int MIN = 66;

    /**
     * Constructor for DGrade objects
     * @param totalPoints points earned
     * @param pointsPossible points possible
     */
    public DGrade(double totalPoints, double pointsPossible){
        super(totalPoints, pointsPossible);
    }

    /**
     * toString for DGrade class
     * @return String of DGrade percentage in YELLOW
     */
    @Override
    public String toString() {
        return Color.YELLOW + super.toString();
    }
}
