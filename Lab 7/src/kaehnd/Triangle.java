/*
 * Course: CS1021 - 091
 * Term: Fall 2018
 * AssignmentL: Lab 4 - A Christmas Wish...
 * Name: Daniel Kaehn
 * Created: 12/18/2018
 */
package kaehnd;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Class modeling a Triangle Shape to be plotted
 */
public class Triangle extends Shape {

    protected final double base;
    protected final double height;

    /**
     * Constructs an isosceles Triangle
     * @throws IllegalArgumentException thrown for invalid dimensions
     * @param x x-coordinate of bottom-left point
     * @param y y-coordinate of bottom-left point
     * @param base length of base side
     * @param height length between a line perpendicular to the base to the upper point
     * @param color color the Triangle is to be plotted in
     */
    public Triangle(double x, double y, double base, double height, Color color)
            throws IllegalArgumentException{

        super(x, y, color);
        if (base <= 0 || height <= 0){
            throw new IllegalArgumentException("Shape dimensions must be integers greater than 0");
        }
        this.base = base;
        this.height = height;
    }

    /**
     * Plots the Triangle
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        final double oneHalf = 0.5;
        setPenColor(plotter);
        plotter.moveTo(x, y);
        plotter.drawTo(x + base, y);
        plotter.drawTo(x + oneHalf * base, y + height);
        plotter.drawTo(x, y);
    }
}
