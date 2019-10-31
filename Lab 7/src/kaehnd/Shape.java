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
 * Abstract class modeling all objects that can be drawn by a WinPlotterFX objects
 */
public abstract class Shape {

    private Color color;
    protected double x;
    protected double y;

    /**
     *  Constructs a Shape
     * @throws IllegalArgumentException if coordinates less than zero
     * @param x x-coordinate of bottom-left point
     * @param y y-coordinate of bottom-left point
     * @param color color the shape is to be drawn in
     */
    public Shape(double x, double y, Color color) throws IllegalArgumentException{
        this.color = color;
        if (x <= 0 || y <= 0){
            throw new IllegalArgumentException("Coordinate values must be greater than zero");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Plots the shape object
     * @param plotter WinPlotterFX object
     */
    public abstract void draw(WinPlotterFX plotter);

    /**
     * Calls the setPenColor method on the passed in
     * WinPlotterFX object with the shape's stored color
     * @param plotter WinPlotterFX object
     */
    public void setPenColor(WinPlotterFX plotter){
        plotter.setPenColor(color.getRed(), color.getGreen(), color.getBlue());
    }


    public void setColor(Color color){
        this.color = color;
    }

}
