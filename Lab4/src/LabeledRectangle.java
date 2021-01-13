/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class LabeledRectangle
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-091
 * Winter 2019
 * LabeledRectangle purpose: creates a rectangle shape that is also labelled
 *
 * @author poptilec
 * @version created on 1/7/2020 at 3:42 PM
 */
public class LabeledRectangle extends Rectangle{
    private final String name;

    /** Constructor creates a Labeled Rectangle object
     * @param x x coordinate of bottom left of rectangle
     * @param y y coordinate of bottom left of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color color of rectangle
     * @param name name assigned to rectangle
     */
    public LabeledRectangle(double x, double y, double width, double height, Color color, String name){
        super(x, y, width, height, color);
        this.name = name;
    }

    @Override
    public void draw(WinPlotterFX plotter){
        super.draw(plotter);
        plotter.printAt(x,y, name);
    }

}
