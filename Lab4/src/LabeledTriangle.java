/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class LabeledTriangle
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-091
 * Winter 2019
 * LabeledTriangle purpose: Creates a Triangle shape that is also labelled
 *
 * @author poptilec
 * @version created on 1/7/2020 at 3:39 PM
 */
public class LabeledTriangle extends Triangle {
    private final String name;

    /** Constructor creates a Labeled Triangle object
     * @param x x coordinate of bottom left of triangle
     * @param y y coordinate of bottom left of triangle
     * @param base base of triangle
     * @param height height of triangle
     * @param color color of triangle
     * @param name name to be assigned to triangle
     */
    public LabeledTriangle(double x, double y, double base, double height, Color color, String name){
        super(x, y, base,height, color);
        this.name = name;
    }

    @Override
    public void draw(WinPlotterFX plotter){
        super.draw(plotter);
        plotter.printAt(x,y, name);

    }

}
