/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2
 * Name: Daniel Kaehn
 * Created: 12/6/2018
 */
package kaehnd;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing references to all Reference objects
 */
public class ReferenceHolder {

    private ArrayList<Reference> references;

    /**
     * Default ReferenceHolder Contstructor
     */
    public ReferenceHolder(){
        references = new ArrayList<>();
    }

    /**
     * Adds Article Reference
     * @param article Article object reference
     */
    public void addReference(Article article){
        references.add(article);
    }

    /**
     * Adds Book Reference
     * @param book Book object reference
     */
    public void addReference(Book book){
        references.add(book);
    }

    /**
     * toString for ReferenceHolder
     * @return String containing toString results of every Refernce
     */
    public String toString() {
        String toReturn = "";
        for (Reference reference: references){
            toReturn += reference.toString();
        }
        return toReturn;
    }

    /**
     * Finds specified Reference object and calls update method in that object
     * Ensures inputted usiqueId exists before calling update method
     * @param uniqueId String REF(n) inputted by user to refer to a Reference object
     * @param in Scanner used to validate and then pass into
     */
    public void updateReference(String uniqueId, Scanner in){
        boolean valid = false;
        Reference refernceToUpdate = null;
        while (!valid) {
            for (Reference reference : references) {
                valid = (uniqueId.equals(reference.getMyUniqueID()));
                if (valid){
                    refernceToUpdate = reference;
                }
            }
            if (!valid){
                System.out.println("Refernce not found\nEnter the ID of the " +
                        "reference you want to update");
                uniqueId = in.nextLine();
            }
        }
        refernceToUpdate.promptForUpdate(in);
    }
}
