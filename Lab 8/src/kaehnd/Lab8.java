/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 8: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/2/2019
 */
package kaehnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX Application "Image Manipulator" that reads in specific image types,
 * applies grayscale and negative image effects, and saves in those same specific types
 */
public class Lab8 extends Application {


    private final int width = 620;
    private final int height = 400;


    /**
     * Starts application and uses FXMLLoader to create objects
     * @param primaryStage main stage for GUI
     * @throws Exception exception thrown if FSMLLoader fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab8.fxml"));
        primaryStage.setTitle("Image Manipulator");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
