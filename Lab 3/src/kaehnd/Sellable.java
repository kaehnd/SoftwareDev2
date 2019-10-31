/*
 * Course: CS1021 - 091
 * Winter 2018-2019
 * Lab 3 - Interfaces
 * Name: Daniel Kaehn
 * Created: 12/13/2018
 */

package kaehnd;

/**
 * Interface for all Sellable items that can be added to a ShoppingCart
 */
public interface Sellable {

    /**
     * Current tax rate for Milwaukee County
     */
    double MKE_COUNTY_TAX_RATE = 0.005;

    /**
     * Current Wisconsin State tax rate
     */
    double WI_STATE_TAX_RATE = 0.05;

    /**
     * Calculates price of item
     * @return price of item
     */
    double price();

    /**
     * Calculates sales tax on item
     * @return sales tax on item
     */
    double tax();
}
