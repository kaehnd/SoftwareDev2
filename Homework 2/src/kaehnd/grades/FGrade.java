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
 * Grade subclass for F Grade
 */
public class FGrade extends Grade {

    /**
     * Constructor for FGrade objects
     * @param totalPoints points earned
     * @param pointsPossible points possible
     */
    public FGrade(double totalPoints, double pointsPossible){
        super(totalPoints, pointsPossible);
    }

    /**
     * toString for FGrade class
     * @return String of FGrade percentage in RED
     */
    @Override
    public String toString() {
        return Color.RED + super.toString();
    }
}
