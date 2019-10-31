/*
 * Course: CS1021 - 091
 * Winter 2018-2019
 * Lab 3 - Interfaces
 * Name: Daniel Kaehn
 * Created: 12/13/2018
 */
package kaehnd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class representing all Sellable Vegetable Produce items
 * @author kaehnd
 * @version 1.0
 */
public class Vegetable extends Produce {
    private static final int MINIMUM_DAYS_TO_SELL = 5;
    private enum VeggieType { UNKNOWN, LEAFY_VEGETABLE, PODDED_VEGETABLE,
        STEM_VEGETABLE, ROOT_VEGETABLE, SEA_VEGETABLE };

    //Price per pound of each vegetable type
    private static final double SEA_VEGETABLE_PRICE = 20.00;
    private static final double PODDED_VEGETABLE_PRICE = 2.00;
    private static final double STEM_VEGETABLE_PRICE = 1.50;
    private static final double ROOT_VEGETABLE_PRICE = 0.30;
    private static final double OTHER_VEGETABLE_PRICE = 1.00;


    private VeggieType veggieType;

    /**
     * Constructor
     * @param name Name of the vegetable
     * @param weight Total weight of the vegetable
     * @param harvestDate Date on which the vegetable was harvested
     */
    public Vegetable(String name, double weight, LocalDate harvestDate) {
        super(name, weight, harvestDate);
        veggieType = setType(name);
    }

    /**
     * Returns the last date on which this product can be sold
     * @return the last date on which this product can be sold
     */
    @Override
    public LocalDate getSellByDate() {
        final double onePointTwo = 1.2;
        final double onePointFive = 1.5;
        double sellByFactor = 1.0;
        switch (veggieType) {
            case SEA_VEGETABLE:
                sellByFactor = onePointTwo;
                break;
            case PODDED_VEGETABLE:
            case STEM_VEGETABLE:
                sellByFactor = onePointFive;
                break;
            case ROOT_VEGETABLE:
                sellByFactor = 4.0;
                break;
        }
        return getHarvestDate().plus((long)(MINIMUM_DAYS_TO_SELL*sellByFactor),
                ChronoUnit.DAYS);
    }

    /**
     * Determines the type of vegetable based on the name of the vegetable
     * @param name the name of the vegetable
     * @return the type of vegetable based on the name of the vegetable
     */
    private VeggieType setType(String name) {
        VeggieType type = VeggieType.UNKNOWN;
        switch (name.toLowerCase()) {
            case "lettuce":
            case "spinach":
            case "mustard greens":
            case "collard greens":
                type = VeggieType.LEAFY_VEGETABLE;
                break;
            case "peas":
            case "green beans":
            case "snow peas":
                type = VeggieType.PODDED_VEGETABLE;
                break;
            case "asparagus":
            case "broccoli":
            case "celery":
                type = VeggieType.STEM_VEGETABLE;
                break;
            case "sweet potato":
            case "beet":
            case "yam":
                type = VeggieType.ROOT_VEGETABLE;
                break;
            case "kelp":
            case "kombu":
            case "nori":
                type = VeggieType.SEA_VEGETABLE;
                break;
        }
        return type;
    }


    /**
     * Calculates price of item
     * @return price of Vegetable
     */
    @Override
    public double price() {
        double weight = this.getWeightInKg();

        switch (veggieType) {
            case SEA_VEGETABLE:
                return weight * SEA_VEGETABLE_PRICE;
            case PODDED_VEGETABLE:
                return weight * PODDED_VEGETABLE_PRICE;
            case STEM_VEGETABLE:
                return weight * STEM_VEGETABLE_PRICE;
            case ROOT_VEGETABLE:
                return weight * ROOT_VEGETABLE_PRICE;
            default:
                return weight * OTHER_VEGETABLE_PRICE;
        }
    }
}
