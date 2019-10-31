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
 * Class modeling a Rectangle with a displayed name
 */
public class LabeledRectangle extends Rectangle {

    private final String name;

    /**
     * Constructs a LabeledRectangle
     * @param x x-coordinate of bottom-left point
     * @param y y-coordinate of bottom-left point
     * @param width length of horizontal sides
     * @param height length of vertical sides
     * @param color color the LabeledRectangle is to be plotted in
     * @param name String to be displayed in the LabeledRectangle
     */
    public LabeledRectangle(double x, double y, double width, double height,
                            Color color, String name) {
        super(x, y, width, height, color);
        this.name = name;
    }

    /**
     * Plots the LabeledRectangle
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        final double textOffsetRatio = 0.5;
        final double textOffsetYRatio = .65;
        final double charLength = 3.2;
        super.draw(plotter);
        plotter.printAt(x + textOffsetRatio * width - name.length() * charLength,
                y + textOffsetYRatio * height, name);
    }
}
