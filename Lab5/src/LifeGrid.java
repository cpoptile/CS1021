/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class LifeGrid
 * Name: poptilec
 * Created 1/20/2020
 */

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the grid of cells used to model Conway's Game of Life.
 */
public class LifeGrid {

    private static final double CHANCE_OF_BEING_ALIVE = 0.5;
    /**
     * This instance variable stores the grid of Cells
     */
    private List<List<Cell>> cells;

    /**
     * This instance variable stores the width of the cell grid
     */
    private final int numberOfCellsWide;

    /**
     * This instance variable stores the height of the cell grid
     */
    private final int numberOfCellsHigh;

    /**
     * This constructor builds a LifeGrid using the size of the Pane
     * passed and the scale of the cells
     * @param gamePane viewing pane
     */
    public LifeGrid(Pane gamePane) {
        this(gamePane, (int) gamePane.getPrefWidth(), (int) gamePane.getPrefHeight());
    }

    /**
     * This constructor builds a LifeGrid using the size of the Pane passed
     * and the scale of the cells
     * @param gamePane viewing pane
     * @param width the preferred width of the viewing pane
     * @param height the preferred width of the viewing pane
     */
    public LifeGrid(Pane gamePane, int width, int height) {
        this.numberOfCellsWide = width/Cell.SCALE;
        this.numberOfCellsHigh = height/Cell.SCALE;
        cells = new ArrayList<>();

        //initialize the two dimensional ArrayList
        for(int i = 0; i < numberOfCellsHigh; i++) {
            cells.add(new ArrayList<>());
        }

        //create cells
        for(int i = 0; i<numberOfCellsHigh; i++) {     // yPosition
            for(int j = 0; j<numberOfCellsWide; j++) { // xPosition
                cells.get(i).add(new Cell(j, i));
            }
        }

        //set neighbors for all cells
        for(int i = 0; i<numberOfCellsHigh; i++) {     // yPosition
            for(int j = 0; j<numberOfCellsWide; j++) { // xPosition
                if(i>0) {
                    if(j>0) {
                        cells.get(i).get(j).setNeighborAboveLeft(cells.get(i-1).get(j-1));
                    }
                    cells.get(i).get(j).setNeighborAboveCenter(cells.get(i-1).get(j));
                    if(j<numberOfCellsWide-1) {
                        cells.get(i).get(j).setNeighborAboveRight(cells.get(i-1).get(j+1));
                    }
                }
                if(j>0) {
                    cells.get(i).get(j).setNeighborMiddleLeft(cells.get(i).get(j-1));
                }
                if(j<numberOfCellsWide-1) {
                    cells.get(i).get(j).setNeighborMiddleRight(cells.get(i).get(j+1));
                }
                if(i<numberOfCellsHigh-1) { // bottom boarder elements
                    if(j>0) {
                        cells.get(i).get(j).setNeighborBelowLeft(cells.get(i+1).get(j-1));
                    }
                    cells.get(i).get(j).setNeighborBelowCenter(cells.get(i+1).get(j));
                    if(j<numberOfCellsWide-1) {
                        cells.get(i).get(j).setNeighborBelowRight(cells.get(i+1).get(j+1));
                    }
                }
            }
        }
        initialize(gamePane);
    }

    /**
     * This method randomizes the life and death attributes of all cells in the cells.
     * Cells have a 50% chance of being alive or dead.
     */
    public void randomize() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.setAlive(Math.random()<CHANCE_OF_BEING_ALIVE);
                cell.updateColors();
            }
        }
    }

    /**
     * This method triggers one iteration (tick) of the game of life rules for the entire grid.
     */
    public void iterate() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.determineNextTick();
            }
        }
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.updateTick();
            }
        }
    }

    /**
     * This method adds all the cell rectangles to the Pane
     * @param gamePane the Pane in which the LifeGrid is displayed
     */
    private void initialize(Pane gamePane) {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                gamePane.getChildren().add(cell);
            }
        }
    }

    /** Method counts the number of cells alive
     * @return the number of cells alive
     */
    public int getCellsAlive(){
        int cellsAlive = 0;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if (cell.isAlive()){
                    cellsAlive++;
                }
            }
        }
        return cellsAlive;
    }

    /** Method counts the number of cells dead
     * @return the number of cells dead
     */
    public int getCellsDead(){
        return (numberOfCellsHigh*numberOfCellsWide) - getCellsAlive();
    }

    /** Method returns list of cells to be accessed by the
     * Lab5Controller class
     * @return List of cells
     */
    public List<List<Cell>> getCells(){
        return cells;
    }
    
}
