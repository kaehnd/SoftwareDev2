/*
 * Course: CS1021 - 091
 * Winter 2018-2019
 * Lab 3 - Interfaces
 * Name: Daniel Kaehn
 * Created: 12/13/2018
 */
package kaehnd;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Class representing all beverage items at Amazon Go
 * @author kaehnd
 * @version 1.0
 */
public abstract class Beverage implements Sellable {
    private final double volumeInFlOz;
    private final String brand;
    private final LocalDate sellByDate;
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###.#");

    public double getVolumeInFlOz() {
        return volumeInFlOz;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getSellByDate() {
        return sellByDate;
    }

    /**
     * Returns the number of containers that are part of the beverage object
     * @return the number of containers that are part of the beverage object
     */
    protected int getNumberOfContainers() {
        return 1;
    }

    /**
     * Constructor
     * @param volume Total number of fluid ounces
     * @param brand The brand name of the beverage
     * @param sellByDate Last date on which this product can be sold
     */
    public Beverage(double volume, String brand, LocalDate sellByDate) {
        volumeInFlOz = volume>0 ? volume : 0;
        this.brand = brand;
        this.sellByDate = sellByDate;
    }

    /**
     * String representation of the beverage
     * @return String representation of the beverage
     */
    @Override
    public String toString() {
        String packagingDescription = getNumberOfContainers()==1
                ? FORMATTER.format(volumeInFlOz) + " oz of "
                : getNumberOfContainers() + " " +
                FORMATTER.format(volumeInFlOz/getNumberOfContainers())
                + " oz containers of ";
        return packagingDescription + getBrand()
                + " with a sell date of: " + sellByDate;
    }
}
