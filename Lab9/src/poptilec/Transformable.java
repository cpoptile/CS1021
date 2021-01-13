/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Transformable
 * Name: poptilec
 * Created 2/18/2020
 */
package poptilec;

import javafx.scene.paint.Color;

/**
 * Functional Interface handles transforming an image
 */
@FunctionalInterface
public interface Transformable {
    /**Abstract method allows to transformation to be an image
     * to be defined
     * @param y y coordinate of pixel
     * @param c color of pixel at coordinate
     * @return color after applied transformation
     */
    Color apply(int y, Color c);
}
