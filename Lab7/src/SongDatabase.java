/*
 * Course: CS1021-091
 * Winter 2019-20
 * Homework 7
 * Name: poptilec
 * Created: 1/31/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SongDatabase extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SongDatabase.fxml"));
        primaryStage.setTitle("Song Database");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
