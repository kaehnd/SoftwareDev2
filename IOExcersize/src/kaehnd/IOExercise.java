/*
 * Course: CS1021-001
 * IO Exercise
 * Daniel Kaehn
 * 1/18/2019
 */
package kaehnd;

import java.io.*;

/**
 * An exercise using different types of output streams
 */
public class IOExercise {
    public static void main(String[] args) {
        int base = 2;
        File file1 = new File("data.txt");
        File file2 = new File("data.bin");
        final int iterations = 30;
        // use try with resources to instantiate a PrintWriter and
        // a DataOutputStream
        try (PrintWriter writer = new PrintWriter(file1);
             DataOutputStream dataOutputStream = new DataOutputStream(
                     new FileOutputStream(file2))) {
            for (int i = 0; i < iterations; i++) {
                int power = (int) Math.pow(2, i);
                writer.println(power);
                dataOutputStream.writeInt(power);
            }


            // Calculate and store the first 10 powers of 2 (from 0 to 9) as integers
            // in both a text file (one value per line) and a binary file.
        } catch (IOException e) {
            System.err.println("Danger, Will Robinson! IOException!");
        }
        System.out.println(file1.length());
        System.out.println(file2.length());
        // display the size of both files.

        // Which is larger? Repeat this exercise (change the above code) storing the
        // first 20 powers of 2, then the first 30 powers of 2. Which is larger in
        // these cases? Can you explain why, for each run (10, 20, and 30) what the
        // size is for each file?
    }
}