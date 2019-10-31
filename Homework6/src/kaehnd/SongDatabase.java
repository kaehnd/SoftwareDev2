/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Homework 6: SongDatabase
 * Name: Daniel Kaehn
 * Created: 1/16/2019
 */
package kaehnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX application storing user-inputted song data to Database.txt file in src folder
 */
public class SongDatabase extends Application {

    private final int height = 480;
    private final int width = 345;

    /**
     * Starts the application and creates objects as described in FXML file
     * @param primaryStage stage for main Scene
     * @throws Exception thrown when FXML file is invalid
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SongDatabase.fxml"));
        primaryStage.setTitle("Song Database");
        primaryStage.setScene(new Scene(root, height, width));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
