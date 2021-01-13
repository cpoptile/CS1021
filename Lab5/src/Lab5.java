/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Lab5
 * Name: poptilec
 * Created 1/20/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class creates a GUI for the Game of Life program
 */
public class Lab5 extends Application {

    /**Method overrides javafx start method to create a stage to be set
     * and seen in the GUI
     * FXML is also loaded into the class for the GUI
     * @param primaryStage primary stage to be shown
     * @throws Exception unexpected event to occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Lab5.fxml"));
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
