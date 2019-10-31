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
 * Class representing bibliographical reference of a book
 */
public class Book extends Reference {

    private String publisher;

    /**
     * Constructor for book object
     * @param author author of book
     * @param title title of book
     * @param publicationYear year book was published
     * @param publisher publisher of book
     */
    public Book(String author, String title, int publicationYear, String publisher){
        super(author, title, publicationYear);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    /**
     * Propmpts user and updates all Book elements
     * @param in Scanner used to prompt user
     */
    public void promptForUpdate(Scanner in){
        super.promptForUpdate(in);
        System.out.println("Enter the updated publisher for the book.");
        setPublisher(in.nextLine());
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * toString for Book object
     * @return BibTeX reference of a Book
     */
    public String toString(){
        String toReturn = "@BOOK { " + super.getMyUniqueID() + ",\n";
        toReturn += "author = \"" + super.getAuthor() + "\",\n";
        toReturn += "title = \"" + super.getTitle() + "\",\n";
        toReturn += "publisher = \"" + this.getPublisher() + "\",\n";
        toReturn += "year = \"" + super.getPublicationYear() + "\"\n";
        return toReturn + "}\n";
    }
}
