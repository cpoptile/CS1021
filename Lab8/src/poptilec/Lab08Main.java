/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class
 * Name: poptilec
 * Created 2/04/2020
 */
package poptilec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class to run lab08.fxml GUI
 */
public class Lab08Main extends Application {

    /**Method overrides Application's start method to display GUI
     * @param primaryStage stage to be displayed
     * @throws Exception exceptions thrown during execution
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab08.fxml"));
        primaryStage.setTitle("Image Manipulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
