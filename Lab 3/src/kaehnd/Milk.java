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
 * Class representing all Sellable Milk Beverages
 * @author kaehnd
 * @version 1.0
 */
public class Milk extends Beverage {
    private enum FatContent { SKIM, ONE_PERCENT, TWO_PERCENT, WHOLE, HALF_AND_HALF, CREAM }
    private final FatContent fatContent;

    private static final double PRICE_FOR_SKIM = 2.50;
    private static final double PRICE_FOR_ONE_PERCENT = PRICE_FOR_SKIM;
    private static final double PRICE_FOR_TWO_PERCENT = PRICE_FOR_SKIM;
    private static final double PRICE_FOR_WHOLE = PRICE_FOR_SKIM;
    private static final double PRICE_FOR_HALF_AND_HALF = PRICE_FOR_SKIM * 2;
    private static final double PRICE_FOR_CREAM = PRICE_FOR_SKIM * 3;

    /**
     * Constructor
     * @param volume Total number of fluid ounces
     * @param sellByDate Last date on which this product can be sold
     * @param fatContent A string representing the amount of fat content
     */
    public Milk(double volume, LocalDate sellByDate, String fatContent) {
        super(volume, "milk", sellByDate);
        switch (fatContent.toLowerCase()) {
            case "cream":
                this.fatContent = FatContent.CREAM;
                break;
            case "half and half":
                this.fatContent = FatContent.HALF_AND_HALF;
                break;
            case "2%":
                this.fatContent = FatContent.TWO_PERCENT;
                break;
            case "1%":
                this.fatContent = FatContent.ONE_PERCENT;
                break;
            default:
                this.fatContent = FatContent.SKIM;
        }
    }

    /**
     * String representation of the fruit
     * @return String representation of the fruit
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns a description of the brand name including the fat content
     * @return a description of the brand name including the fat content
     */
    @Override
    public String getBrand() {
        String descriptor = super.getBrand();
        switch (fatContent) {
            case CREAM:
                descriptor = "cream";
                break;
            case HALF_AND_HALF:
                descriptor = "half and half";
                break;
            case WHOLE:
                descriptor = "whole " + descriptor;
                break;
            case TWO_PERCENT:
                descriptor = "2% " + descriptor;
                break;
            case ONE_PERCENT:
                descriptor = "1% " + descriptor;
                break;
            default:
                descriptor = "fat free " + descriptor;
        }
        return descriptor;
    }

    /**
     * Calculates price of item
     * @return price of Milk
     */
    @Override
    public double price() {
        final int ouncesPerGallon = 128;
        double volumeInGallons = getVolumeInFlOz() / ouncesPerGallon;
        switch (fatContent) {
            case CREAM:
                return PRICE_FOR_CREAM * volumeInGallons;
            case HALF_AND_HALF:
                return PRICE_FOR_HALF_AND_HALF * volumeInGallons;
            case WHOLE:
                return PRICE_FOR_WHOLE * volumeInGallons;
            case TWO_PERCENT:
                return PRICE_FOR_TWO_PERCENT * volumeInGallons;
            case ONE_PERCENT:
                return PRICE_FOR_ONE_PERCENT * volumeInGallons;
            default:
                return PRICE_FOR_SKIM * volumeInGallons;
        }
    }

    /**
     * Calculates sales tax on item
     * @return 0
     */
    @Override
    public double tax() {
        return 0;
    }
}
