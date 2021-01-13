/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Circle
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;
import java.lang.Math;

/**
 * Course CS1021-091
 * Winter 2019
 * Circle purpose: Creates a circle shape
 *
 * @author poptilec
 * @version created on 1/7/2020 at 3:26 PM
 */
public class Circle extends Shape {
    private final double radius;
    private static final int DEGREES_OF_CIRCLE = 360;

    /** Constructor creates a Circle object
     * @param x x coordinate of center of circle
     * @param y y coordinate of center of circle
     * @param radius radius of the circle
     * @param color color of the circle
     */
    public Circle(double x, double y, double radius, Color color){
        super(x, y, color);
        this.radius = radius;
    }

    /** Constructor creates a Circle object
     * @param x x coordinate of center of circle
     * @param y y coordinate of center of circle
     * @param width width of the circle
     * @param height height of the circle
     * @param color color of the circle
     */
    public Circle(double x, double y, double width, double height, Color color){
        super(x, y, color);
        this.radius = width/2;
    }

    @Override
    public void draw(WinPlotterFX plotter){
        setPenColor(plotter);
        plotter.moveTo(x+radius,y);
        for (int degree = 0; degree<DEGREES_OF_CIRCLE; degree++){
            plotter.drawTo((Math.cos(Math.toRadians(degree))*radius)+x,
                    (Math.sin(Math.toRadians(degree))*radius) + y);
        }
    }
}
