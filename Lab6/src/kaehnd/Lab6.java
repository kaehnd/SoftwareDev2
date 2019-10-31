/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab6: Web Tester
 * Name: Daniel Kaehn
 * Created: 1/17/2019
 */

package kaehnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX Application using the WebsiteTester class to test timeout of a website
 */
public class Lab6 extends Application {

    private final int width = 600;
    private final int height = 600;


    /**
     * Starts application and uses FXMLLoader to create objects
     * @param primaryStage main stage for GUI
     * @throws Exception exception thrown if FSMLLoader fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab6.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
