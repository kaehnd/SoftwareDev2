/*
 * Course: CS1021 - 021
 * Winter 2019
 * Homework 2 - Grade Inheritance
 * Name: Daniel Kaehn
 * Created: 12/3/2018
 */
package kaehnd;

import kaehnd.color.Color;
import kaehnd.grades.Grade;
import kaehnd.grades.AGrade;
import kaehnd.grades.BGrade;
import kaehnd.grades.CGrade;
import kaehnd.grades.DGrade;
import kaehnd.grades.FGrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Driver class for Homework 2. This should produce output similar to the sample output given
 * in Blackboard, sorted from low to high
 */
public class GradeDriver {
    private static final int PERCENT = 100;
    private static final ArrayList<Grade> GRADES = new ArrayList<>();

    /**
     * main method for the driver
     * @param args unused
     */



    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean running = true;
        String input;

        System.out.println(Color.MAGENTA + "Welcome to GradeBook 3000!" + Color.RESET);
        // Program loop
        while (running) {
            // Ask user if they want to enter a new grade
            do {
                System.out.print("Enter a new grade(y/n)? ");
                input = in.nextLine();
            } while (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")));

            running = input.equalsIgnoreCase("y");

            // If not quitting, get a new grade from user
            if(running) {
                GRADES.add(getGrade(in));
            }
        }

        // sort and display the grades
        Collections.sort(GRADES);
        System.out.println("Your GRADES: ");
        displayGrades();
        System.exit(0);
    }

    /**
     * Helper method that takes input from the user and creates a new Grade object based on
     * the input
     *
     * @param in Scanner to get input from the user
     * @return The appropriate Grade Object, based on user input
     */
    private static Grade getGrade(Scanner in) {
        String input;
        double totalPoints;
        double possiblePoints;

        // Get points earned
        do {
            System.out.print("Enter points earned: ");
            input = in.nextLine();
        } while(isNotDouble(input));
        totalPoints = Double.parseDouble(input);

        // Get points possible
        do {
            System.out.print("Enter points possible: ");
            input = in.nextLine();
        } while(isNotDouble(input));
        possiblePoints = Double.parseDouble(input);

        return calculateGrade(totalPoints, possiblePoints);
    }

    /**
     * Helper method that verifies a given String contains a Double value
     * @param input String that contains user input
     * @return true, if the input can be parsed as a double, false otherwise
     */
    @SuppressWarnings("unused")
    private static boolean isNotDouble(String input) {
        try {
            double test = Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Helper method that generates the appropriate subclass of Grade, based on the values
     * passed in
     * @param total total points earned by the student
     * @param possible total possible points on a given grade item
     * @return The appropriate Grade Object, based on the parameters given
     */
    private static Grade calculateGrade(double total, double possible) {
        double percentage = total / possible * PERCENT;
        if(percentage >= AGrade.MIN) {
            return new AGrade(total, possible);
        } else if (percentage >= BGrade.MIN) {
            return new BGrade(total, possible);
        } else if(percentage >= CGrade.MIN) {
            return new CGrade(total, possible);
        } else if (percentage >= DGrade.MIN) {
            return new DGrade(total, possible);
        } else {
            return new FGrade(total, possible);
        }
    }

    /**
     * Helper method that displays all Grades contained in the GRADES ArrayList
     */
    private static void displayGrades() {
        for(int i = 0; i < GRADES.size(); i++) {
            System.out.println("Grade " + (i + 1) + ": " + GRADES.get(i));
        }
    }
}
