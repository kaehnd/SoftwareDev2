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
 * Class modeling a Rectangle Shape to be plotted
 */
public class Rectangle extends Shape {

    protected final double height;
    protected final double width;

    /**
     * Constructs a Rectangle
     * @throws IllegalArgumentException if dimensions less than 0
     * @param x x-coordinate of bottom-left point
     * @param y y-coordinate of bottom-left point
     * @param width length of horizontal sides
     * @param height length of vertical sides
     * @param color color Rectangle is to be plotted in
     */
    public Rectangle(double x, double y, double width, double height, Color color)
            throws IllegalArgumentException{
        super(x, y, color);
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Shape dimensions must be integers greater than 0");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Plots the Rectangle
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.moveTo(x, y);
        plotter.drawTo(x + width, y);
        plotter.drawTo(x + width, y + height);
        plotter.drawTo(x, y + height);
        plotter.drawTo(x, y);

    }
}
