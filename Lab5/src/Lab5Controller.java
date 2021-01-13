/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class Lab5Controller
 * Name: poptilec
 * Created 1/20/2020
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class controls and handles GUI components created in the Lab5 class
 */
public class Lab5Controller implements Initializable {
    @FXML
    private TextField aliveCells;
    @FXML
    private TextField deadCells;
    @FXML
    private Pane gamePane;
    private LifeGrid lifeGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert gamePane != null :"fx:id=\"gamePane\" was not injected: check your FXML file 'game.fxml'.";
        lifeGrid = new LifeGrid(gamePane);
    }

    /**
     * Method utilizes the LifeGrid class to randomize cells when the Random
     * button is clicked
     */
    @FXML
    public void randomize(){
        lifeGrid.randomize();
        setAliveCells();
        setDeadCells();
    }

    /**
     * Method utilizes the LifeGrid class to go through one iteration
     * of the algorithm for the Game of Life
     */
    @FXML
    public void iterateOnce(){
        lifeGrid.iterate();
        setAliveCells();
        setDeadCells();
    }

    /**
     * Method utilizes the LifeGrid class to display the number
     * of cells that are alive
     */
    private void setAliveCells(){
        aliveCells.setText(Integer.toString(lifeGrid.getCellsAlive()));
    }

    /**
     * Method utilizes the LifeGrid class to display the number
     * of cells that are alive
     */
    private void setDeadCells(){
        deadCells.setText(Integer.toString(lifeGrid.getCellsDead()));
    }

    /**
     * Method sets a cell to the opposite of its current state
     * of alive or dead when clicked on
     */
    @FXML
    public void isClicked(){
        for (List<Cell> row : lifeGrid.getCells()) {
            for (Cell cell : row) {
                cell.setOnMouseClicked(event -> {
                    cell.setAlive(!cell.isAlive());
                    cell.updateColors();
                });
            }
        }
        setAliveCells();
        setDeadCells();
    }

}
