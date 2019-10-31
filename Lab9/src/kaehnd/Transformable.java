/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 9: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/6/2019
 */
package kaehnd;

import javafx.scene.paint.Color;

/**
 * Functional Interface to transform pixel color
 */
@FunctionalInterface
public interface Transformable {
    /**
     * Transforms pixel color
     * @param y y-coordinate of pixel
     * @param color color of pixel
     * @return transformed color
     */
    Color transform(int y, Color color);
}
