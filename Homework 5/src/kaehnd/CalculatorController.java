/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Homework 5: Calculator 3000
 * Name: Daniel Kaehn
 * Created: 1/9/2019
 */
package kaehnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Calculator Graphical User Interface through Calculator.fxml
 */
public class CalculatorController implements Initializable {

    @FXML
    private Button operatorButton;

    @FXML
    private Button equalsButton;

    @FXML
    private TextField opOneField;

    @FXML
    private TextField opTwoField;

    @FXML
    private TextField outputField;


    /**
     * Runs when the Controller is initialized, sets the equals button as the default button
     * @param url a url
     * @param resourceBundle a resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        equalsButton.setDefaultButton(true);
    }

    /**
     * Handler for equalsButton fired action,
     * \also fired through any press of enter outside of operatorButton
     */
    @FXML
    private void onEquals(){
        double opOne = Double.parseDouble(opOneField.getText());
        double opTwo = Double.parseDouble(opTwoField.getText());
        double result = 0;
        switch(operatorButton.getText()) {
            case "+":
                result = opOne + opTwo;
                break;
            case "-":
                result = opOne - opTwo;
                break;
            case "*":
                result = opOne * opTwo;
                break;
            case "/":
                result = opOne / opTwo;
                break;
            case "%":
                result = opOne % opTwo;
                break;
        }
        if (result < 0) {
            outputField.setStyle("-fx-background-color: red;");
        } else {
            outputField.setStyle("");
        }
        if (Double.isNaN(result) || Double.isInfinite(result)){
            outputField.setStyle("-fx-background-color: red;");
            outputField.setText("ERROR!!!");
        } else {
            outputField.setText(Double.toString(result));
        }
    }

    @FXML
    private void onOperatorSwitch(){
        switch(operatorButton.getText()) {
            case "+":
                operatorButton.setText("-");
                break;
            case "-":
                operatorButton.setText("*");
                break;
            case "*":
                operatorButton.setText("/");
                break;
            case "/":
                operatorButton.setText("%");
                break;
            case "%":
                operatorButton.setText("+");
                break;
        }
    }
}
