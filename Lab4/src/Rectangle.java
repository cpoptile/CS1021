/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Rectangle
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * Course CS1021-091
 * Winter 2019
 * Rectangle purpose: Creates a Rectangle shape
 *
 * @author poptilec
 * @version created on 1/7/2020 at 3:40 PM
 */
public class Rectangle extends Shape{
    protected final double height;
    protected final double width;

    /** Constructor creates a Rectangle object
     * @param x x coordinate of bottom left of rectangle
     * @param y y coordinate of bottom left of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color color of rectangle
     */
    public Rectangle(double x, double y, double width,
                     double height, Color color){
        super(x, y, color);
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw(WinPlotterFX plotter){
        setPenColor(plotter);
        plotter.moveTo(x, y);
        plotter.drawTo(x+width, y);
        plotter.drawTo(x+width, y+height);
        plotter.drawTo(x, y+height);
        plotter.drawTo(x,y);
    }

}
