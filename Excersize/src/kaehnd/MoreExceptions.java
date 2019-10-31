/*
 * Course: CS1021 - 001
 * Winter 2019
 * Class Exercise: Exceptions
 * Name: Sean Jones
 * Created: 1/14/2019
 */
package kaehnd;

import java.util.Scanner;

/**
 * Class exercise on Exceptions
 */
public class MoreExceptions {
    private static String[] entries = {"What", "is", "the", "airspeed", "of", "an", "unladen",
            "swallow"};

    /**
     * Complete this program so it will continue to ask for input and print selected words
     * regardless of what the user may enter. Entering 'q' will exit the program.
     * @param args unused
     */
    public static void main(String[] args) {
        boolean keepGoing = true;
        while (keepGoing) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the index of the word you would like: ");
            try {
                String input = in.nextLine();
                String result = getEntry(input);
                if (result.isEmpty()){
                    keepGoing = false;
                } else {
                    System.out.println(result);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.err.println("You entered an integer out of bounds.");
            } catch (NumberFormatException e){
                System.err.println("That wasn't an integer.");
            }
        }
        System.out.println("Te Marta.");
    }

    /**
     * Accesses the array and returns the entry contained in the given index
     * @param input Index of the entry, stored as a String
     * @return entry contained in the list at the given entry
     */
    private static String getEntry(String input) throws NumberFormatException, ArrayIndexOutOfBoundsException{
        int i = parseInput(input);
        if (i == Integer.MIN_VALUE){
            return "";
        } else {
            return entries[i];
        }
    }

    /**
     * Helper method that parses the user input and returns the index as an integer
     * @param input User input
     * @return index entered by user
     */
    private static int parseInput(String input) throws NumberFormatException{
        if (input.equals("q")){
            return Integer.MIN_VALUE;
        } else{
            return Integer.parseInt(input);
        }
    }
}