/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 9: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/6/2019
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
public class Lab9 extends Application {


    private final int width = 750;
    private final int height = 450;

    private final int kernelWindowWidth = 350;
    private final int kernelWindowHeight = 190;




    /**
     * Starts application and uses FXMLLoader to create objects
     * @param primaryStage main stage for GUI
     * @throws Exception exception thrown if FSMLLoader fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Lab9.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("Image Manipulator");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

        FXMLLoader subLoader = new FXMLLoader(getClass().getResource("KernelUI.fxml"));
        Parent kernel = subLoader.load();
        Stage kernelStage = new Stage();
        kernelStage.setTitle("Kernel Filter");
        kernelStage.setScene(new Scene(kernel, kernelWindowWidth, kernelWindowHeight));
        kernelStage.setY(primaryStage.getY() + primaryStage.getHeight());
        kernelStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2);

        Lab9Controller lab9Controller = mainLoader.getController();
        lab9Controller.setKernelStage(kernelStage);
        ((KernelController)subLoader.getController()).setLab9Controller(lab9Controller);

        kernelStage.setOnCloseRequest(e -> lab9Controller.handleKernelClose());
        primaryStage.setOnCloseRequest(e -> System.exit(0));



    }


    public static void main(String[] args) {
        launch(args);
    }


}
