/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Shape
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;


/**
 * Abstract class creates Shape objects that will be used to draw a face using its
 * various subclasses of different shapes
 */
public abstract class Shape extends FaceMaker {
    private Color color;
    protected final double x;
    protected final double y;

    /** Creates a shape object
     * @param x bottom left x coordinate
     * @param y bottom left y coordinate
     * @param color color of the shape
     */
    public Shape(double x, double y, Color color){
        this.x = x;
        this.y = y;
        setColor(color);
    }

    /** Abstract method is used within each subclass to draw its respective shape
     * @param plotter instance of WinPlotterFX object that was given
     */
    public abstract void draw(WinPlotterFX plotter);

    /** Sets the pen color of the plotter to the color that is assigned to the instance variable
     * @param plotter instance of a WinPlotterFX object that was given
     */
    public void setPenColor(WinPlotterFX plotter){
        plotter.setPenColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    /** Sets instance variable of color to the color that was inputted
     * @param color color of pen
     */
    public void setColor(Color color){
        this.color = color;
    }
}
