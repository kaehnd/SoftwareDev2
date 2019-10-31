/*
 * Course: CS1021 - 091
 * Winter 2018-2019
 * Lab 3 - Interfaces
 * Name: Daniel Kaehn
 * Created: 12/13/2018
 */

package kaehnd;

import java.util.ArrayList;

/**
 * Class representing a ShoppingCart of Amazon Go and storing sellable object references
 */
public class ShoppingCart {

    private ArrayList<Sellable> cart;

    /**
     * Constructs a ShoppingCart with an ArrayList of Sellable items.
     */
    public ShoppingCart(){
        cart = new ArrayList<>();
    }

    /**
     * Adds a new Sellable item to the ShoppingCard
     * @param item object implementing Sellable interface
     */
    public void add(Sellable item){
        cart.add(item);
    }

    /**
     * Calls price method of each object in the ShoppingCart and adds together their total cost
     * @return total cost of all items in the ShoppingCart
     */
    public double cost(){
        double cost = 0;
        for(Sellable item: cart){
            cost += item.price();
        }
        return cost;
    }

    /**
     * Calls tax method of each objects in the ShoppingCart and adds together total tax
     * @return total tax of all items in the ShoppingCart
     */
    public double taxDue(){
        double tax = 0;
        for(Sellable item: cart){
            tax += item.tax();
        }
        return tax;
    }
}
