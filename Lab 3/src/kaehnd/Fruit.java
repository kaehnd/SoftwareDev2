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
 * Class represenging all Sellable Fruit Produce items
 * @author kaehnd
 * @version 1.0
 */
public class Fruit extends Produce {
    private static final int MINIMUM_DAYS_TO_SELL = 20;
    private enum FruitType { UNKNOWN, FLESHY, STONE, AGGREGATE }
    private final FruitType fruitType;
    private int quantity;

    //Price per pound for aggregate fruits
    private static final double AGGREGATE_PRICE_PER_POUND = 2.30;

    //Price per fruit for stone and fleshy fruits
    private static final double PRICE_PER_STONE_FRUIT = 0.50;
    private static final double PRICE_PER_FLESHY_FRUIT = 0.80;

    //price per fruit for other fruits--random number between $0.10 and $2.0
    private final double otherFruitRandomPrice = Math.random() * 1.90 + 0.10;

    /**
     * Constructor
     * @param name Name of the fruit
     * @param weight Total weight of the fruit
     * @param harvestDate Date on which the fruit was harvested
     * @param quantity quantity of fruit
     */
    public Fruit(String name, double weight, LocalDate harvestDate, int quantity) {
        super(name, weight, harvestDate);
        fruitType = setType(name);
        this.quantity = quantity;
    }

    /**
     * Returns the last date on which this product can be sold
     * @return the last date on which this product can be sold
     */
    @Override
    public LocalDate getSellByDate() {
        final double onePointFive = 1.5;
        int daysToSell = fruitType==FruitType.STONE
                ? (int)(onePointFive*MINIMUM_DAYS_TO_SELL)
                : MINIMUM_DAYS_TO_SELL;
        return getHarvestDate().plus(daysToSell, ChronoUnit.DAYS);
    }

    /**
     * Determines the type of fruit based on the name of the fruit
     * @param name the name of the fruit
     * @return the type of fruit based on the name of the fruit
     */
    private FruitType setType(String name) {
        FruitType type = FruitType.UNKNOWN;
        switch (name.toLowerCase()) {
            case "apple":
            case "pear":
            case "orange":
            case "banana":
            case "grape":
                type = FruitType.FLESHY;
                break;
            case "peach":
            case "plum":
            case "mango":
                type = FruitType.STONE;
                break;
            case "strawberries":
            case "blackberries":
            case "grapes":
                type = FruitType.AGGREGATE;
                break;
        }
        return type;
    }

    /**
     * String representation of the fruit
     * @return String representation of the fruit
     */
    @Override
    public String toString() {
        return "Quantity: " + quantity + " of " + super.toString();
    }

    /**
     * Calculates price of item
     * @return price of Fruit
     */
    @Override
    public double price() {
        double weight = this.getWeightInKg();
        switch (fruitType) {
            case AGGREGATE:
                return weight * AGGREGATE_PRICE_PER_POUND;
            case STONE:
                return PRICE_PER_STONE_FRUIT * quantity;
            case FLESHY:
                return PRICE_PER_FLESHY_FRUIT * quantity;
            default:
                return otherFruitRandomPrice * quantity;
        }
    }


}

