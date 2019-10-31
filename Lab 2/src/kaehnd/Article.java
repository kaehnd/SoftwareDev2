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
 * Class representing bibliographical reference of an article
 */
public class Article extends Reference {

    private int endingPage;
    private String journal;
    private int startingPage;

    /**
     * Constrctor for Article object
     * @param author author of article
     * @param title title of article
     * @param publicationYear year article was published
     * @param journal name of journal the article was published in
     * @param startingPage page of journal article begins on
     * @param endingPage page of journal article ends on
     */
    public Article(String author, String title, int publicationYear,
                   String journal, int startingPage, int endingPage){

        super(author, title, publicationYear);

        //Sets startingPage to default value of 1 if less than 1
        if (startingPage < 1){
            startingPage = 1;
        }
        //Sets endingPage to difference between it and startingPge if less than startingPage
        if (endingPage < startingPage){
            endingPage = (startingPage - endingPage) + startingPage;
        }

        this.endingPage = endingPage;
        this.journal = journal;
        this.startingPage = startingPage;
    }


    public int getEndingPage(){
        return this.endingPage;
    }

    public String getJournal(){
        return this.journal;
    }

    public int getStartingPage(){
        return this.startingPage;
    }

    /**
     * Prompts user and updates all Article elements
     * @param in Scanner used to prompt user
     */
    public void promptForUpdate(Scanner in){
        super.promptForUpdate(in);
        System.out.println("Enter the updated title of the journal.");
        this.setJournal(in.nextLine());

        System.out.println("Enter the updated first page of the article.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated first page of the article.");
        }
        this.setStartingPage(in.nextInt());

        System.out.println("Enter the updated last page of the article.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the updated last page of the article.");
        }
        this.setEndingPage(in.nextInt());
        in.nextLine();

    }


    public void setEndingPage(int endingPage) {
        this.endingPage = endingPage;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setStartingPage(int startingPage) {
        this.startingPage = startingPage;
    }


    /**
     * toString for article
     * @return BibTeX reference of an article
     */
    public String toString(){
        String toReturn = "@ARTICLE { "+ super.getMyUniqueID() + ",\n";
        toReturn += "author = \"" + super.getAuthor() + "\",\n";
        toReturn += "title = \"" + super.getTitle() + "\",\n";
        toReturn += "journal = \"" + this.getJournal() + "\",\n";
        toReturn += "pages = \"" + this.getStartingPage() + "-" + this.getEndingPage() + "\",\n";
        toReturn += "year = \"" + super.getPublicationYear() + "\"\n";
        return toReturn + "}\n";
    }

}
