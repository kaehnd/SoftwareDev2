/*
 * Course: CS 1021
 * Term: Winter 2018
 * Assignment: Homework 1
 * Name: Daniel Kaehn
 * Date: 11/27/18
 */
package kaehnd;

/**
 * Color enumerator class containing ansi escape sequences for colored console text printing
 */
public enum Color {

    /**
     * ANSI Escape code for color reset
     */
    RESET("\u001B[0m"),

    /**
     * ANSI Escape code for black color
     */
    BLACK("\u001B[30m "),

    /**
     * ANSI Escape code for red color
     */
    RED("\u001B[31m"),

    /**
     * ANSI Escape code for green color
     */
    GREEN("\u001B[32m"),

    /**
     * ANSI Escape code for yellow color
     */
    YELLOW("\u001B[33m"),

    /**
     * ANSI Escape code for blue color
     */
    BLUE("\u001B[34m"),

    /**
     * ANSI Escape code for magenta color
     */
    MAGENTA("\u001B[35m"),

    /**
     * ANSI Escape code for cyan color
     */
    CYAN("\u001B[36m"),

    /**
     * ANSI Escape code for white color
     */
    WHITE("\u001B[37m");

    private String ansi;

    Color(String ansi){
        this.ansi = ansi;
    }


    /**
     * toString for Color enum
     * @return String representation of ANSI escape code for color enum
     */
    @Override
    public String toString(){
        return this.ansi;
    }
}
