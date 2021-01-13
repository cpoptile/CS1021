/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class FaceMaker
 * Name: poptilec
 * Created 1/7/2020
 */

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * This class draws a face out of shapes
 */
public class FaceMaker extends Application {
    public static final int WINDOW_SIZE = 800;
    public static final int GRID_INCREMENT = WINDOW_SIZE/10;
    public static final int HEAD_SIZE = 700;
    public String shape;

    /**
     * Launches the JavaFX application
     * @param args ignored
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Use the Shape class and its descendants to draw a face.
     * @param stage Default stage given to a JavaFX program. Unused.
     */
    @Override
    public void start(Stage stage) {
        WinPlotterFX plotter = new WinPlotterFX();
        plotter.setWindowTitle("Face Maker");
        plotter.setWindowSize(WINDOW_SIZE, WINDOW_SIZE);
        plotter.setBackgroundColor(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue());
        final boolean showGrid = true;
        plotter.setGrid(showGrid, GRID_INCREMENT, GRID_INCREMENT, Color.GRAY);
        shapeOption();
        Shape head = createHead();
        Shape leftEye = createLeftEye();
        Shape rightEye = createRightEye();
        Shape nose = createNose();
        Shape mouth = createMouth();

        head.draw(plotter);
        leftEye.draw(plotter);
        rightEye.draw(plotter);
        nose.draw(plotter);
        mouth.draw(plotter);

        plotter.showPlotter();
    }

    /**
     * Creates and returns a shape representing the head
     * @return shape representing the head
     */
    private Shape createHead() {
        final int xLeft = (WINDOW_SIZE-HEAD_SIZE)/2;
        final int yBottom = (WINDOW_SIZE-HEAD_SIZE)/2;
        final String name = "Head";
        if (shape.equals("Rectangle")){
            return new Rectangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
        }
        else if (shape.equals("Circle")){
            return new Circle(xLeft+HEAD_SIZE/2.0, yBottom+HEAD_SIZE/2.0, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
        }
        else if (shape.equals("Triangle")){
            return new Triangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE);
        }

        else if (shape.equals("Labeled Triangle")){
            return new LabeledTriangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE, name);
        }
        else{
            return new LabeledRectangle(xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE, name);
        }
    }

    /**
     * Creates and returns a shape representing the right eye
     * @return shape representing the right eye
     */
    private Shape createRightEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor*HEAD_SIZE;
        final double yBottom = WINDOW_SIZE/2 + size*3/2;
        final double xLeft = WINDOW_SIZE/2 + size;
        final String name = "Right Eye";
        if (shape.equals("Rectangle")){
            return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }
        else if (shape.equals("Circle")){
            return new Circle(xLeft+size/2, yBottom+size/2, size, size, Color.WHITE);
        }
        else if (shape.equals("Triangle")){
            return new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }

        else if (shape.equals("Labeled Triangle")){
            return new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
        else{
            return new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
    }

    /**
     * Creates and returns a shape representing the left eye
     * @return shape representing the left eye
     */
    private Shape createLeftEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor*HEAD_SIZE;
        final double yBottom = WINDOW_SIZE/2 + size*3/2;
        final double xLeft = WINDOW_SIZE/2 - size*2;
        final String name = "Left Eye";
        if (shape.equals("Rectangle")){
            return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }
        else if (shape.equals("Circle")){
            return new Circle(xLeft+(size/2), yBottom+(size/2), size, size, Color.WHITE);
        }
        else if (shape.equals("Triangle")){
            return new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }

        else if (shape.equals("Labeled Triangle")){
            return new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
        else{
            return new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
    }

    /**
     * Creates and returns a shape representing the nose
     * @return shape representing the nose
     */
    private Shape createNose() {
        final double scaleFactor = 0.2;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE/2 - size/2;
        final double yBottom = (WINDOW_SIZE)/2;
        final String name = "Nose";
        if (shape.equals("Rectangle")){
            return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }
        else if (shape.equals("Circle")){
            return new Circle(xLeft+(size/2), yBottom+(size/2), size, size, Color.WHITE);
        }
        else if (shape.equals("Triangle")){
            return new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }

        else if (shape.equals("Labeled Triangle")){
            return new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
        else{
            return new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
    }

    /**
     * Creates and returns a shape representing the mouth
     * @return shape representing the mouth
     */
    private Shape createMouth() {
        final double scaleFactor = 0.3;
        final double size = scaleFactor*HEAD_SIZE;
        final double xLeft = WINDOW_SIZE/2 - size/2;
        final double yBottom = (WINDOW_SIZE)/2 - size*3/2;
        final String name = "Mouth";
        if (shape.equals("Rectangle")){
            return new Rectangle(xLeft, yBottom, size, size, Color.WHITE);
        }
        else if (shape.equals("Circle")){
            return new Circle(xLeft+(size/2), yBottom+(size/2), size, size, Color.WHITE);
        }
        else if (shape.equals("Triangle")){
            return new Triangle(xLeft, yBottom, size, size, Color.WHITE);
        }

        else if (shape.equals("Labeled Triangle")){
            return new LabeledTriangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
        else{
            return new LabeledRectangle(xLeft, yBottom, size, size, Color.WHITE, name);
        }
    }

    /**
     * Method establishes the shape chosen by the user
     * to be used to create the face
     */
    private void shapeOption(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the FaceMaker\nYou have 6 options of shapes to choose from" +
                " to generate a face\n1.) Rectangle\n2.) Circle\n3.) Triangle\n4.) Labeled Rectangle" +
                "\n5.) Labeled Triangle\n6.) Random\nIf any of the above options are not chosen (case-sensitive)" +
                " a random shape will be generated.");
        String option = scan.nextLine();
        if (!(option.equals("Rectangle") || option.equals("Circle") || option.equals("Triangle") ||
                option.equals("Labeled Rectangle") || option.equals("Labeled Triangle"))){
            int random = (int)(Math.random()*5) + 1;
            if (random == 1){
                shape = "Rectangle";
            } else if (random == 2){
                shape = "Circle";
            } else if (random == 3){
                shape = "Triangle";
            } else if (random == 4){
                shape = "Labeled Triangle";
            } else if (random == 5){
                shape = "Labeled Rectangle";
            }
        } else{
            shape = option;
        }
    }

}
