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
 * Class modeling a Triangle with a displayed name
 */
public class LabeledTriangle extends Triangle {

    private final String name;

    /**
     * Constructs a LabeledTriangle
     * @param x x-coordinate of bottom-left point
     * @param y y-coordinate of bottom-left point
     * @param base length of base side
     * @param height length between a line perpendicular to the base to the upper point
     * @param color color the LabeledTriangle is to be plotted in
     * @param name String to be displayed in the LabeledTriangle
     */
    public LabeledTriangle(double x, double y, double base,
                           double height, Color color, String name) {
        super(x, y, base, height, color);
        this.name = name;
    }

    /**
     * Plots the LabeledTriangle
     * @param plotter WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        final double textXOffsetRatio = .5;
        final double textYOffsetRatio = .35;
        super.draw(plotter);
        plotter.printAt(x + textXOffsetRatio * base - name.length() * 3,
                y + textYOffsetRatio * height, name);
    }
}
