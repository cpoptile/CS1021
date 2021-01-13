/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Triangle
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-091
 * Winter 2019
 * Triangle purpose: Creates a Triangle shape
 *
 * @author poptilec
 * @version created on 1/7/2020 at 3:37 PM
 */
public class Triangle extends Shape {
    protected final double base;
    protected final double height;

    /** Constructor creates a Triangle object
     * @param x x coordinate of bottom left of triangle
     * @param y y coordinate of bottom left of triangle
     * @param base base of triangle
     * @param height height of triangle
     * @param color color of triangle
     */
    public Triangle(double x, double y, double base, double height, Color color){
        super(x, y, color);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw (WinPlotterFX plotter){
        setPenColor(plotter);
        plotter.moveTo(x,y);
        plotter.drawTo(x+base,y);
        plotter.drawTo(x+base/2,y+height);
        plotter.drawTo(x, y);

    }

}
