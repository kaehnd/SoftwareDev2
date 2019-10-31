/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 5: Game of Life
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
 * Loads the Lab5.fxml and creates GUI resources for a JavaFX application
 */
public class Lab5 extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 416;

    /**
     * Starts the application
     * @param primaryStage main stage for JavaFX application
     * @throws Exception exception event resulting from FXMLLoader.load()
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Lab5.fxml"));
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
