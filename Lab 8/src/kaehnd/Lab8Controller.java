/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 8: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/2/2019
 */
package kaehnd;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import java.util.logging.Logger;

/**
 * Controller for Lab8 Main Window
 */
public class Lab8Controller implements Initializable {


    @FXML
    private ImageView imagePane;

    @FXML
    private VBox mainPane;


    @FXML
    private Button reloadButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button grayscaleButton;

    @FXML
    private Button negativeButton;


    private Image prevImage;
    private Image currentImage;

    private int width;
    private int height;

    private Logger logger;

    /**
     * Initialize method runs on startup and sets up logger
     * @param url not used
     * @param resourceBundle not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger = Logger.getLogger("exceptionLogger");
        logger.setUseParentHandlers(false);
        try{
            FileHandler handler = new FileHandler(System.getProperty("user.dir") +
                    File.separator + "Log.log", true);
            logger.addHandler(handler);
        } catch (IOException e){
            throwAlert("Log Error", "Log fail could not be created.");
        }
    }

    @FXML
    private void load(ActionEvent event){
        try {
            FileChooser fileChooser = new FileChooser();
            File imageFile = fileChooser.showOpenDialog(new Stage());
            currentImage = ImageIO.read(imageFile.toPath());
            prevImage = currentImage;
            imagePane.setImage(currentImage);
            imagePane.fitWidthProperty().bind(mainPane.widthProperty());
//            imagePane.fitHeightProperty().bind(mainPane.heightProperty());
            width = (int) currentImage.getWidth();
            height = (int) currentImage.getHeight();
            setDisable(false);
        } catch (FileNotFoundException e) {
            throwAlert("Load Error", "File not found");
        } catch (IOException e) {
            throwAlert("Load Error", e.getMessage());
        } catch (NumberFormatException | InputMismatchException e){
            throwAlert("MSOE File Load Error", "Corrupt .msoe file");
        } catch (NullPointerException e){
            throwAlert("Load Error", "No file selected");
        }

    }


    @FXML
    private void reload(ActionEvent event){
        imagePane.setImage(prevImage);
        currentImage = prevImage;
    }


    @FXML
    private void save(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("GIF Files", "*.gif"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("TIFF Files", "*.tiff"),
                new FileChooser.ExtensionFilter("MSOE Files", "*.msoe"));

        File saveFile = chooser.showSaveDialog(new Stage());
        try {
            ImageIO.write(saveFile.toPath(), currentImage);
        } catch (IOException e){
            throwAlert("Save Error", "File save failed");
        } catch (NullPointerException e){
            throwAlert("Save Error", "No file destination chosen");
        }
    }


    @FXML
    private void grayscale(ActionEvent event){
        final double redRatio = .02126;
        final double greenRatio = .07152;
        final double blueRatio = .0722;

        WritableImage writableImage = new WritableImage(currentImage.getPixelReader(),
                width, height);

        //Set each pixel to a weighted average RGB value
        for (int h = 0; h < height; h++){
            for (int w = 0; w < width; w++){
                Color prevColor = currentImage.getPixelReader().getColor(w, h);
                double value = redRatio * prevColor.getRed() + greenRatio *
                        prevColor.getGreen() + blueRatio * prevColor.getBlue();
                writableImage.getPixelWriter().setColor(w, h, new Color(value, value, value, 1));
            }
        }
        currentImage = writableImage;
        imagePane.setImage(currentImage);
    }

    @FXML
    private void negative(ActionEvent event){
        WritableImage writableImage = new WritableImage(currentImage.getPixelReader(),
                width, height);

        //Set each pixel to its negative color value
        for (int h = 0; h < height; h++){
            for (int w = 0; w < width; w++){
                Color prevColor = currentImage.getPixelReader().getColor(w, h);
                writableImage.getPixelWriter().setColor(w, h, new Color(1 - prevColor.getRed(),
                        1 - prevColor.getGreen(), 1 - prevColor.getBlue(), 1));
            }
        }
        currentImage = writableImage;
        imagePane.setImage(currentImage);
    }

    //Throws an alert with an inputted header and message
    //Create or update log file with log of exception
    private void throwAlert(String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setHeaderText(header);
        logger.warning(message);
        alert.showAndWait();
    }


    //Sets whether buttons dependent on having an image loaded are disabled
    private void setDisable(boolean b){
        grayscaleButton.setDisable(b);
        negativeButton.setDisable(b);
        saveButton.setDisable(b);
        reloadButton.setDisable(b);
    }
}