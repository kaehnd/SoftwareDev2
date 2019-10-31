/*
 * Course: CS1021 - 021
 * Winter 2019
 * Homework 2 - Grade Inheritance
 * Name: Daniel Kaehn
 * Created: 12/3/2018
 */
package kaehnd.grades;

import kaehnd.color.Color;

import java.text.DecimalFormat;

/**
 * A class that represents a student's grade for an assignment. This will be a parent class
 * for more specific grade classes
 */
@SuppressWarnings("WeakerAccess")
public class Grade implements Comparable<Grade> {

    private static final int PERCENT = 100;
    
    private final double totalPoints;
    private final double pointsPossible;
    
    /**
     * DecimalFormat to format percentage output
     */
    protected final DecimalFormat df;
    
    /**
     * The percentage score of a given grade
     */
    protected final double percentage;

    /**
     * Constructor for a Grade object.
     * @param totalPoints total points earned by the student
     * @param pointsPossible total possible points on a given grade item
     */
    public Grade(double totalPoints, double pointsPossible) {
        this.totalPoints = totalPoints;
        this.pointsPossible = pointsPossible;
        df = new DecimalFormat("#.##");
        percentage = getPercentage();
    }


    /**
     * Overrides the Object's toString() with the percentage score of the Grade, shown as a
     * full percentage rounded to two decimal places (ex. 87.33%)
     * @return a String containing the percentage score
     */
    @Override
    public String toString() {
        return df.format(percentage * PERCENT) + "%" + Color.RESET;
    }

    /**
     * Compares the percentages of two Grade objects
     * @param other The other Grade being compared
     * @return 0 if the Grades are equal, a positive number if this Grade is higher, negative
     * if the other Grade is higher
     */
    public int compareTo(Grade other) {
        return (int)(this.percentage * PERCENT - other.percentage * PERCENT);
    }

    /**
     * Helper method that calculates the percentage value, wih is the total earned points
     * divided by the total possible number of points,
     * @return Percentage value of the Grade
     */
    private double getPercentage() {
        return this.totalPoints / pointsPossible;
    }
}
