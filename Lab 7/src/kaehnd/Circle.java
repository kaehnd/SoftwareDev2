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
 * Class modeling a Circle Shape to be plotted
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * Constructs a circle
     * @throws IllegalArgumentException when radius is not an int greater than 0
     * @param x x-coordinate of center
     * @param y y-coordinate of center
     * @param radius radius of circle
     * @param color color to be plotted in
     */
    public Circle(double x, double y, double radius, Color color)
            throws IllegalArgumentException{
        super(x, y, color);
        if (radius <= 0){
            throw new IllegalArgumentException("Shape dimensions must be integers greater than 0");
        }
        this.radius = radius;
    }

    /**
     * Plots the Circle
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        double rSquared = Math.pow(radius, 2);
        plotter.moveTo(x - radius, y);
        for (double p = x - radius; p <= x + radius; p++){
            double f = (Math.sqrt(rSquared - Math.pow(p - x, 2)) + y);
            plotter.drawTo(p, f);
        }
        for (double p = x + radius; p >= x - radius; p--){
            double f = (-Math.sqrt(rSquared - Math.pow(p - x, 2)) + y);
            plotter.drawTo(p, f);
        }
    }
}
