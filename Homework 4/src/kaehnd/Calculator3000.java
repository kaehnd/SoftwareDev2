/*
 * Course: CS1021 - 091
 * Spring 2018
 * Lab 4 - A Christmas Wish...
 * Name: Sean Jones
 * Created: 2/2/2018
 */

package kaehnd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/**
 * Calculator application built with JavaFX
 */
public class Calculator3000 extends Application {
    private DecimalFormat formatter = new DecimalFormat("#.##");

    private Button equals = new Button("=");
    private Button plus = new Button("+");
    private TextField opOne = new TextField();
    private TextField opTwo = new TextField();
    private TextField results = new TextField();

    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;

    private static final int SPACING_CONSTANT = 10;
    private static final int TEXT_FIELD_HEIGHT = 10;
    private static final int TEXT_FIELD_WIDTH = 73;
    private static final int RESULTS_WIDTH = 19;


    public static void main(String[] args) {
        launch();
    }

    /**
     * Start method as declared in Application which builds and starts the FX Program
     * @param mainStage the stage created in the launch() method
     */
    public void start(Stage mainStage){

        mainStage.setTitle("Calculator 3000");
        VBox mainPane = new VBox();
        HBox upperPane = new HBox();
        HBox lowerPane = new HBox();
        opOne.setMaxSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        opTwo.setMaxSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        upperPane.setAlignment(Pos.CENTER);
        upperPane.setSpacing(SPACING_CONSTANT);
        upperPane.setPadding(new Insets(SPACING_CONSTANT, 0, SPACING_CONSTANT, 0));
        upperPane.getChildren().addAll(opOne, plus, opTwo, equals);

        results.setEditable(false);
        results.setPrefColumnCount(RESULTS_WIDTH);
        lowerPane.getChildren().addAll(results);
        lowerPane.setAlignment(Pos.CENTER);

        mainPane.setPadding(new Insets(0, SPACING_CONSTANT, 0, SPACING_CONSTANT));
        mainPane.getChildren().addAll(upperPane, lowerPane);
        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage.setScene(scene);
        mainStage.show();

        equals.setOnAction(this::add);
        opOne.setOnAction(this::add);
        opTwo.setOnAction(this::add);

        opTwo.setAlignment(Pos.CENTER);
        opOne.setAlignment(Pos.CENTER);
        results.setAlignment(Pos.CENTER);
        results.setStyle("-fx-background-color: lightgrey;");
    }

    private void add(ActionEvent e){
        if (isADouble(opOne.getText()) && isADouble(opTwo.getText())) {
            double sum = Double.parseDouble(opOne.getText()) + Double.parseDouble(opTwo.getText());
            results.setText(formatter.format(sum));
            if (sum < 0) {
                results.setStyle("-fx-background-color: red;");
            } else if (results.getStyle() != null) {
                results.setStyle("-fx-background-color: lightgrey;");
            }
        }
    }

    private boolean isADouble(String s) {
        boolean valid = true;
        if (!s.isEmpty() && s.charAt(0) == '-') {
            isADouble(s.substring(1));
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    valid = false;
                }
            }
        }
        if (s.isEmpty()){
            valid = false;
        }
        return valid;
    }
}
