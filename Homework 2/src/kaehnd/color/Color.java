/*
 * Course: CS1021 - 001
 * Winter 2019
 * Homework 1 - Color
 * Name: Sean Jones
 * Created: 11/18/2018
 */
package kaehnd.color;

/**
 * A list of 3-bit ansi colors
 */
@SuppressWarnings("unused")
public enum Color {
    /**
     * Reset color to default
     */
    RESET("\u001B[0m"),
    /**
     * Black
     */
    BLACK("\u001B[30m"),
    /**
     * Red
     */
    RED("\u001B[31m"),
    /**
     * Green
     */
    GREEN("\u001B[32m"),
    /**
     * Yellow
     */
    YELLOW("\u001B[33m"),
    /**
     * Blue
     */
    BLUE("\u001B[34m"),
    /**
     * Magenta
     */
    MAGENTA("\u001B[35m"),
    /**
     * Cyan
     */
    CYAN("\u001B[36m"),
    /**
     * White
     */
    WHITE("\u001B[37m");

    private final String ansi;

    /**
     * Constructor for the enumeration
     * @param ansi the ansi escape code for the given value
     */
    Color(String ansi) {
        this.ansi = ansi;
    }

    /**
     * Returns the ansi escape code for the value of the enumeration
     * @return String containing the ansi escape code
     */
    @Override
    public String toString() {
        return ansi;
    }
}
