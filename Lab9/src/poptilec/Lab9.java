/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Lab9
 * Name: poptilec
 * Created 2/18/2020
 */
package poptilec;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class sets up GUI
 */
public class   Lab9 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader lab9Loader = new FXMLLoader();
        lab9Loader.setLocation(getClass().getResource("Lab9Controller.fxml"));
        Parent root = lab9Loader.load();
        primaryStage.setTitle("Image Manipulator 3000!");
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.setScene(new Scene(root));

        Stage kernel = new Stage();
        FXMLLoader kernelLoader = new FXMLLoader();
        kernelLoader.setLocation(getClass().getResource("KernelController.fxml"));
        Parent root2 = kernelLoader.load();
        kernel.setScene(new Scene(root2));

        Lab9Controller controller = lab9Loader.getController();
        controller.setSecondStage(kernel);
        controller.setStage(primaryStage);

        KernelController secondController = kernelLoader.getController();
        secondController.setController(controller);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
