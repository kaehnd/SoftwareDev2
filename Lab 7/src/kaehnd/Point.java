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
 * Class modeling a Point Shape to be plotted
 */
public class Point extends Shape {

    /**
     * Constructs a Point
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * @param color color the point is to be plotted in
     */
    public Point(double x, double y, Color color){
        super(x, y, color);
    }

    /**
     * Plots the Point
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.drawPoint(x, y);
    }
}
