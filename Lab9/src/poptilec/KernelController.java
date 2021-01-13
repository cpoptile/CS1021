/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class KernelController
 * Name: poptilec
 * Created 2/18/2020
 */
package poptilec;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * Controller class for KernelController.fxml
 */
public class KernelController {
    private Lab9Controller controller;
    @FXML
    private TextField r1c1;
    @FXML
    private TextField r1c2;
    @FXML
    private TextField r1c3;
    @FXML
    private TextField r2c1;
    @FXML
    private TextField r2c2;
    @FXML
    private TextField r2c3;
    @FXML
    private TextField r3c1;
    @FXML
    private TextField r3c2;
    @FXML
    private TextField r3c3;

    /**Sets controller of this window to same as Lab9Controller
     * @param controller Lab9Controller controller
     */
    public void setController(Lab9Controller controller) {
        this.controller = controller;
    }

    /**
     * Method sets values of blur kernel in GUI
     */
    @FXML
    private void blur(){
        r1c1.setText("0");
        r1c2.setText("1");
        r1c3.setText("0");
        r2c1.setText("1");
        r2c2.setText("5");
        r2c3.setText("1");
        r3c1.setText("0");
        r3c2.setText("1");
        r3c3.setText("0");
    }

    /**
     * Method sets values of sharpen kernel in GUI
     */
    @FXML
    private void sharpen(){
        r1c1.setText("0");
        r1c2.setText("-1");
        r1c3.setText("0");
        r2c1.setText("-1");
        r2c2.setText("5");
        r2c3.setText("-1");
        r3c1.setText("0");
        r3c2.setText("-1");
        r3c3.setText("0");
    }

    /**
     * Method checks to see if current kernel is valid and then applies it
     * if it is
     */
    @FXML
    public void apply(){
        double total = Double.parseDouble(r1c1.getText()) + Double.parseDouble(r1c2.getText()) +
                Double.parseDouble(r1c3.getText()) + Double.parseDouble(r2c1.getText()) +
                Double.parseDouble(r2c2.getText()) + Double.parseDouble(r2c3.getText()) +
                Double.parseDouble(r3c1.getText()) + Double.parseDouble(r3c2.getText()) +
                Double.parseDouble(r3c3.getText());
        if (total>0){
            double[] kernel = {(Double.parseDouble(r1c1.getText())/total),
                    (Double.parseDouble(r1c2.getText())/total),
                    (Double.parseDouble(r1c3.getText())/total),
                    (Double.parseDouble(r2c1.getText())/total),
                    (Double.parseDouble(r2c2.getText())/total),
                    (Double.parseDouble(r2c3.getText())/total),
                    (Double.parseDouble(r3c1.getText())/total),
                    (Double.parseDouble(r3c2.getText())/total),
                    (Double.parseDouble(r3c3.getText())/total)};
            Image filteredImage = ImageIO.filterImage(kernel);
            controller.imageView.setImage(filteredImage);
            controller.image = filteredImage;
        }

    }


}
