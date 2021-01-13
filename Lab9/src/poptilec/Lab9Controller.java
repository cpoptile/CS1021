/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Lab9Controller
 * Name: poptilec
 * Created 2/18/2020
 */
package poptilec;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

/**
 * Controller class for the lab9.fxml
 */
public class Lab9Controller {
    /**
     * ImageView node contains image as well as image
     * when transformed
     */
    @FXML
    public ImageView imageView;
    @FXML
    private Button filter;

    private Image original;
    /**
     * image that is displayed and transformed
     */
    public Image image;

    private Stage stage;
    private Stage stage2;

    /**
     * Method loads image into imageview
     */
    @FXML
    public void load(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("image chooser");
        File file = chooser.showOpenDialog(null);
        original = null;
        if (file != null) {
            try {
                original = ImageIO.read(Paths.get(file.getPath()));
                image = original;
                imageView.setImage(original);
            } catch (IOException e){
                Alert ioException = new Alert(Alert.AlertType.ERROR,
                        "an IO exception has occurred");
                ioException.showAndWait();
                ImageIO.logger.log(Level.WARNING, "picture was unable to be opened");
            } catch (IllegalArgumentException e) {
                Alert extension = new Alert(Alert.AlertType.ERROR, "file type not supported");
                extension.showAndWait();
                ImageIO.logger.log(Level.WARNING, "picture was unable to be opened");
            }
        }
    }

    /**
     * Method reloads image to original
     */
    @FXML
    public void reload(){
        imageView.setImage(original);
        image = original;
    }

    /**
     * Method saves image to selected path
     */
    @FXML
    public void save(){
        FileChooser saver = new FileChooser();
        saver.setTitle("file chooser");
        saver.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.msoe", "*.png", "*.jpg"));
        File file = saver.showSaveDialog(null);
        try {
            Path path = Paths.get(file.getPath());
            ImageIO.write(image, path);
        } catch (IOException e){
            Alert ioException = new Alert(Alert.AlertType.ERROR, "an IO exception has occurred");
            ioException.showAndWait();
            ImageIO.logger.log(Level.WARNING, "picture was unable to be saved");
        } catch(IllegalArgumentException e){
            Alert extension = new Alert(Alert.AlertType.ERROR, "file type not supported");
            extension.showAndWait();
            ImageIO.logger.log(Level.WARNING, "picture was unable to be saved");
        }

    }

    /**
     * Method transforms image to a grayscale version
     */
    @FXML
    public void grayscale() {
        Image grey = ImageIO.gray(image);
        imageView.setImage(grey);
        image = grey;
    }

    /**
     * Method transform image to a negative version
     */
    @FXML
    public void negative() {
        Image negative = ImageIO.negative(image);
        imageView.setImage(negative);
        image = negative;
    }

    /**
     * Method transform image to a negative version
     */
    @FXML
    public void red() {
        Image red = ImageIO.red(image);
        imageView.setImage(red);
        image = red;
    }

    /**
     * Method transform image to a negative version
     */
    @FXML
    public void redGray() {
        Image redGray = ImageIO.redGray(image);
        imageView.setImage(redGray);
        image = redGray;
    }

    /**
     * method handles changing the text of the hide or show
     * filter button
     */
    @FXML
    public void hideShow(){
        if (filter.getText().equals("Show Filter")){
            filter.setText("Hide Filter");
            stage2.show();
        } else{
            filter.setText("Show Filter");
            stage2.close();
        }
    }

    /**Method sets stage to lab9.fxml
     * @param stage stage to be set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**Method sets stage of second window to the kernel controller .fxml
     * @param stage stage from kernel controller
     */
    public void setSecondStage(Stage stage) {
        stage2 = stage;
    }


}

