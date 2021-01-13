/*
 * Course: CS1021-091
 * Winter 2019-20
 * Lab 6
 * Name: poptilec
 * Created: 1/24/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class creates a GUI to allow the user to input a url and timeout and displays
 * its file size, time it took to download the material, the port used and the name of the
 * host
 */
public class Lab6 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab6.fxml"));
        primaryStage.setTitle("Website Tester");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
