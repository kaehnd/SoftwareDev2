/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 5: Game of Life
 * Name: Daniel Kaehn
 * Created: 1/9/2019
 */
package kaehnd;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the grid of cells used to model Conway's Game of Life.
 */
public class LifeGrid {

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
     * This constructor builds a LifeGrid using the size of the
     * Pane passed and the scale of the cells
     * @param gamePane viewing pane
     */
    public LifeGrid(Pane gamePane) {
        this(gamePane, (int) gamePane.getPrefWidth(), (int) gamePane.getPrefHeight());
    }

    /**
     * This constructor builds a LifeGrid using the size of the
     * Pane passed and the scale of the cells
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
        final double fiftyPercentProbability = 0.5;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.setAlive(Math.random() < fiftyPercentProbability);
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
     * This Method toggles the life of a cell specified by its grid coordinates
     * @param x x-coordinate of column in the grid
     * @param y y-coordinate of row in the grid
     */
    public void toggleDead(int x, int y){
        Cell cellToChange = cells.get(y).get(x);
        cellToChange.setAlive(!cellToChange.isAlive());
        cellToChange.updateColors();
    }

    /**
     * Counts and determines how many cells are alive
     * @return integer number of cells alive
     */
    public int getCellsAlive(){
        int numberAlive = 0;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if(cell.isAlive()) {
                    numberAlive++;
                }
            }
        }
        return numberAlive;
    }

    /**
     * Determines the number of cells alive through subtracting the living cells from total cells
     * @return total number of dead cells
     */
    public int getCellsDead(){
        int totalCells = numberOfCellsHigh * numberOfCellsWide;
        return totalCells - getCellsAlive();
    }

    /**
     * This method adds all the cell rectangles to the Pane
     */
    private void initialize(Pane gamePane) {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                gamePane.getChildren().add(cell);
            }
        }
    }
}
