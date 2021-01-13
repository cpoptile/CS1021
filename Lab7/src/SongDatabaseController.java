/*
 * Course: CS1021-091
 * Winter 2019-20
 * Homework 7
 * Name: poptilec
 * Created: 1/31/2020
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SongDatabaseController {
    @FXML
    private TextField title;
    @FXML
    private TextField artist;
    @FXML
    private TextField year;
    @FXML
    private Button ok;
    @FXML
    private TextArea database;

    /**
     * Method clears the text in the TextFields
     */
    @FXML
    public void clear(){
        title.clear();
        artist.clear();
        year.clear();
    }

    /**
     * Method adds a completed entry to the database
     */
    @FXML
    public void ok(){
        String current = System.getProperty("user.dir");
        Path path = Paths.get(current, "src");

        File file = new File(path.toString() +
                System.getProperty("file.separator") + "database.txt");

        try(PrintWriter out = new PrintWriter(new FileWriter(file, true));
            Scanner input = new Scanner(file)) {
            out.println("\"" + title.getText() + "\", " + artist.getText() + ", " + year.getText());
            while(input.hasNext()){
                database.setText(database.getText() + input.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            Alert files = new Alert(Alert.AlertType.ERROR, "File not found");
            files.setHeaderText("File Was Found :(");
            files.setTitle("Exception");
        } catch (IOException e) {
            Alert location = new Alert(Alert.AlertType.ERROR,
                    "Invalid Location/ Location for File Not Found");
            location.setTitle("Exception");
            location.setHeaderText("Location not Found :(");
        }
        title.setText("");
        artist.setText("");
        year.setText("");
    }

    @FXML
    public void visible(){
        if (title.getText().isEmpty() || artist.getText().isEmpty() || year.getText().isEmpty()){
            ok.setDisable(true);
        }
    }


}
