/*
 * Course: CS1021-091
 * Winter 2019-20
 * Lab 6
 * Name: poptilec
 * Created: 1/24/2020
 */

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Controller class for lab 6 that allows the user to monitor material on the web
 */
public class Lab6Controller {
    @FXML
    private TextField url;
    @FXML
    private TextField size;
    @FXML
    private TextField time;
    @FXML
    private TextField port;
    @FXML
    private TextField host;
    @FXML
    private TextField timeout;
    @FXML
    private TextArea webpage;

    private WebsiteTester tester = new WebsiteTester();

    @FXML
    private void analyze(){
        try{
            tester.openURL(url.getText());
            tester.openConnection();
            tester.downloadText();
        } catch (MalformedURLException e) {
            Alert urlAlert = new Alert(Alert.AlertType.ERROR, "The URL" +
                    " entered in the text box is invalid");
            urlAlert.setHeaderText("URL Error");
            urlAlert.setTitle("Error Dialog");
            urlAlert.showAndWait();
            url.clear();
        } catch (UnknownHostException e){
            Alert hostException = new Alert(Alert.AlertType.ERROR, "Error: Unable to reach " +
                    "the host " + url.getText());
            hostException.setHeaderText("Host Error");
            hostException.setTitle("Error Dialog");
            hostException.showAndWait();
        } catch (SocketTimeoutException e){
            Alert socketException = new Alert(Alert.AlertType.CONFIRMATION, "There has been a " +
                    "timeout reaching the site. Click OK to extend the timeout period.");
            socketException.setHeaderText("Wait longer?");
            socketException.setTitle("Timeout Dialog");
            socketException.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK){
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setHeaderText("Set extended timeout");
                    dialog.showAndWait();
                    timeout.setText(dialog.getResult());
                    setTimeout();
                    analyze();
                }
            });
        } catch (FileNotFoundException e){
            Alert fileException = new Alert(Alert.AlertType.ERROR, "Error: File " +
                    "not found on the server,\n" + url.getText());
            fileException.setHeaderText("File Error");
            fileException.setTitle("Error Dialog");
            fileException.showAndWait();
        } catch (IOException e){
            Alert ioException = new Alert(Alert.AlertType.ERROR, "Error reading from given file");
            ioException.setHeaderText("IO Error");
            ioException.setTitle("Error Dialog");
            ioException.showAndWait();
        }

        size.setText(Integer.toString(tester.getSize()));
        time.setText(Long.toString(tester.getDownloadTime()));
        host.setText(tester.getHostname());
        port.setText(Integer.toString(tester.getPort()));
        webpage.setText(tester.getContent());
    }

    @FXML
    private void setTimeout() {
        try {
            tester.setTimeout(timeout.getText());
        } catch (NumberFormatException e) {
            Alert invalidTimeout = new Alert(Alert.AlertType.ERROR, "Timeout" +
                    " must be greater than or equal to 0");
            invalidTimeout.setHeaderText("Invalid Timeout Error");
            invalidTimeout.setTitle("Error Dialog");
            invalidTimeout.showAndWait();
            timeout.setText(tester.getTimeout());
        }
    }

}
