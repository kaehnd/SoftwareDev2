/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 5: Game of Life
 * Name: Daniel Kaehn
 * Created: 1/9/2019
 */
package kaehnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Lab5Controller for GUI generated from Lab5.FXML
 */
public class Lab5Controller implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private Label aliveLabel;

    @FXML
    private Label deadLabel;


    private LifeGrid lifeGrid;

    /**
     * Creates the LifeGrid, inserts it into the gamePane, and updates the labels
     * @param location a URL object
     * @param resources a ResourceBundle object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert gamePane != null :"fx:id=\"gamePane\" was not injected:" +
                "check your FXML file 'game.fxml'.";
        lifeGrid = new LifeGrid(gamePane);
        updateLabels();
    }

    @FXML
    private void onRandomButton(){
        lifeGrid.randomize();
        updateLabels();
    }

    @FXML
    private void onIterate(){
        lifeGrid.iterate();
        updateLabels();
    }

    @FXML
    private void onClick(MouseEvent e){
        int xCoord = (int)e.getX() / Cell.SCALE;
        int yCoord = (int)e.getY() / Cell.SCALE;
        lifeGrid.toggleDead(xCoord, yCoord);
        updateLabels();
    }

    private void updateLabels(){
        aliveLabel.setText("Cells alive: " + lifeGrid.getCellsAlive());
        deadLabel.setText("Cells dead: " + lifeGrid.getCellsDead());
    }
}
