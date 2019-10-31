/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2
 * Name: Daniel Kaehn
 * Created: 12/6/2018
 */
package kaehnd;

import java.util.Scanner;

/**
 * Reference superclass for book and article objects, represents bibliographical information
 */
public class Reference {

    private String author;
    private static int instanceCount;
    private final String myUniqueID;
    private int publicationYear;
    private String title;

    /**
     * Creates a reference and generates a unique ID from static counter variable
     * @param author author of the reference
     * @param title title of the book or article
     * @param publicationYear year the book or article was published
     */
    public Reference(String author, String title, int publicationYear){
        this.author = author;
        this.title = title;
        this.publicationYear = Math.abs(publicationYear); //ABS ensures year is never negative
        this.myUniqueID = "REF" + instanceCount;
        instanceCount++;
    }

    public String getAuthor() {
        return author;
    }

    public String getMyUniqueID() {
        return myUniqueID;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getTitle() {
        return title;
    }

    /**
     * prompts user to update specified Reference object and updates object fields
     * @param in Scanner used to prompt user
     */
    public void promptForUpdate(Scanner in){
        System.out.println("Enter the updated author of the reference");
        this.setAuthor(in.nextLine());

        System.out.println("Enter the updated title of the reference");
        this.setTitle(in.nextLine());

        System.out.println("Enter the updated copyright year for the reference.");
        while (!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated copyright year for the reference.");
        }

        this.setPublicationYear(Math.abs(in.nextInt())); //ABS ensures year is never negative
        in.nextLine();
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
