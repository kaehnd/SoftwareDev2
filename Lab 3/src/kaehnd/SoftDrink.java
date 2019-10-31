/*
 * Course: CS1021 - 091
 * Winter 2018-2019
 * Lab 3 - Interfaces
 * Name: Daniel Kaehn
 * Created: 12/13/2018
 */
package kaehnd;

import java.time.LocalDate;

/**
 * Class representing all Sellable SoftDrink Beverages
 * @author kaehnd
 * @version 1.0
 */
public class SoftDrink extends Beverage {
    /**
     * Package types for soft drinks
     */
    public enum PackageType { SINGLE, SIX_PACK, TWELVE_PACK, TWENTYFOUR_PACK }

    private final PackageType packaging;

    private static final double BASE_PRICE = .08;

    /**
     * Constructor
     * @param volume Total number of fluid ounces
     * @param brand The brand name of the beverage
     * @param sellByDate Last date on which this product can be sold
     * @param packaging how many drinks in packaged together for retail
     */
    public SoftDrink(double volume, String brand, LocalDate sellByDate,
                     PackageType packaging) {
        super(volume, brand, sellByDate);
        this.packaging = packaging;
    }

    /**
     * Returns the number of containers that are part of this soft drink object
     * @return the number of containers that are part of this soft drink object
     */
    @Override
    public int getNumberOfContainers() {
        final int six = 6;
        final int twelve = 12;
        final int twentyFour = 24;

        int quantity = 1;
        switch (packaging) {
            case SIX_PACK:
                quantity = six;
                break;
            case TWELVE_PACK:
                quantity = twelve;
                break;
            case TWENTYFOUR_PACK:
                quantity = twentyFour;
                break;
        }
        return quantity;
    }

    /**
     * Calculates price of item
     * @return price of SoftDrink
     */
    @Override
    public double price() {
        final double twelve = 12;
        final double ninetyNineHundredths = .99;
        double basePrice = BASE_PRICE * this.getVolumeInFlOz();
        double containerSizeFactor = twelve * getNumberOfContainers() / this.getVolumeInFlOz();
        double quantityFactor = Math.pow(ninetyNineHundredths, getNumberOfContainers());
        return basePrice * containerSizeFactor * quantityFactor;
    }

    /**
     * Calculates sales tax on item
     * @return sales tax on SoftDrink
     */
    @Override
    public double tax() {
        return price() * MKE_COUNTY_TAX_RATE + price() * WI_STATE_TAX_RATE;
    }
}
