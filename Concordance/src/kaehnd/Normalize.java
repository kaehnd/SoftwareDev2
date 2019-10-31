/*
 * Course: CS1021-001
 * Daniel Kaehn
 * Functional Programming Exercise
 * 2-8-19
 */
package kaehnd;

/**
 * Interface used to perform operations on Strings
 */
@FunctionalInterface
public interface Normalize {

    /**
     * Normalizes a String
     * @param s String to be normalized
     * @return normalized String
     */
    String normalize(String s);
}
