/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Homework 5: Calculator 3000
 * Name: Daniel Kaehn
 * Created: 1/9/2019
 */
package kaehnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for Calculator application. Loads Calculator.fxml and creates GUI objects
 */
public class Calculator extends Application {

    private static final int WIDTH = 325;
    private static final int HEIGHT = 100;

    /**
     * Builds JavaFX GUI program
     * @param primaryStage main Stage object
     * @throws Exception throwable runtime exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
        primaryStage.setTitle("Welcome to Calculator 3000!");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
