/*
 * Course: CS 1021
 * Term: Winter 2018
 * Assignment: Homework 1
 * Name: Daniel Kaehn
 * Date: 11/27/18
 */

package kaehnd;

import java.util.Scanner;

/**
 * Driver for Color Enumerator class which prints decimal, hexadecimal, and binary conversions
 * of a data-validated, user-inputted integer
 */

public class ColorDriver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome  to Number Converter 3000!\nThis program will take a decimal" +
                "number and convert it into binary and hexadecimal representations\n" + Color.BLUE+
                "Decimal will be displayed in blue\n" + Color.RED + "Hexadecimal will be" +
                "displayed in red\n" + Color.GREEN + "Binary will be displayed in green" +
                Color.RESET);

        String input = "";
        while (!validate(input)) {
            System.out.print("Please enter a number to convert (or q to quit): ");
            input = in.nextLine();
        }

        while (!input.equals("q")) {

            int userInt = Integer.parseInt(input);
            display(userInt);
            input = "";

            while (!validate(input)) {
                System.out.print("Please enter a number to convert (or q to quit): ");
                input = in.nextLine();
            }
        }
        System.out.println("Thank you for using Number Converter 3000!");
    }


    //Ensures String input is a valid integer or the character q
    // returns a boolean stating if input is valid
    private static boolean validate(String input){
        int i = 0;
        while (i < input.length() && Character.isDigit(input.charAt(i))){
            i++;
        }
        return ((i == input.length() || input.equals("q")) && !input.isEmpty());
    }

    private static void display(int integer){
        final int HEX_RADIX = 16; //Base for hexadecimal number system
        final int BINARY_RADIX = 2; //Base for binary number system
        String hex = Integer.toString(integer, HEX_RADIX);
        String binary = Integer.toString(integer, BINARY_RADIX);
        System.out.printf(Color.BLUE + "Decimal: %d\n" + Color.RED + "Hexadecimal: %s\n" +
                Color.GREEN + "Binary: %s\n\n" + Color.RESET, integer, hex, binary);
    }
}
