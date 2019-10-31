    /*
     * Course: CS1021 - 091
     * Term: Winter 2018
     * Lab 9: Image Manipulator
     * Name: Daniel Kaehn
     * Created: 2/6/2019
     */
package kaehnd;


import edu.msoe.cs1021.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import java.util.logging.Logger;

/**
 * Controller for Lab9 Main Window
 */
public class Lab9Controller implements Initializable {


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
    @FXML
    private Button redButton;
    @FXML
    private Button redGrayButton;
    @FXML
    private Button showFilterButton;

    private Image prevImage;
    private Image currentImage;

    private Logger logger;

    private final double redRatio = .02126;
    private final double greenRatio = .07152;
    private final double blueRatio = .0722;


    private Stage kernelStage;



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
    /**
     * Sets showFilterButton text to show filter if kernel stage is manually closed
     */
    public void handleKernelClose(){
        showFilterButton.setText("Show Filter");
    }

    /**
     * Used to apply convolve effect from KernelController
     * @param kernelFields ArrayList of TextFields used to create kernel
     */
    public void convolveFromFields(ArrayList<TextField> kernelFields){
        try {
            double[] kernel = new double[kernelFields.size()];
            double sum = kernelFields.stream()
                    .mapToDouble(field -> Double.parseDouble(field.getText()))
                    .sum();
            for (int i = 0; i < kernelFields.size(); i++) {
                kernel[i] = Double.parseDouble(kernelFields.get(i).getText()) / sum;
            }
            changeImage(ImageUtil.convolve(currentImage, kernel));
        } catch (IllegalArgumentException e){
            throwAlert("Kernel Error", "Invalid kernel argument");
        }

    }

    public void setKernelStage(Stage stage){
        kernelStage = stage;
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
                new FileChooser.ExtensionFilter("MSOE Files", "*.msoe"),
                new FileChooser.ExtensionFilter("BMSOE Files", "*.bmsoe"));

        File saveFile = chooser.showSaveDialog(new Stage());
        try {
            ImageIO.write(saveFile.toPath(), currentImage);
        } catch (IOException e){
            throwAlert("Save Error", "File save failed");
        } catch (NullPointerException e){
            throwAlert("Save Error", "No file destination chosen");
        }
    }

    private void changeImage(Image newImage) {
        this.currentImage = newImage;
        imagePane.setImage(currentImage);
    }


    @FXML
    private void grayscale(ActionEvent event){

        changeImage(transformImage(currentImage, (y, color) ->{
            double value = redRatio * color.getRed() + greenRatio *
                    color.getGreen() + blueRatio * color.getBlue();
            return new Color(value, value, value, 1);
        }));
    }

    @FXML
    private void negative(ActionEvent event){
        changeImage(transformImage(currentImage, (y, color) -> new Color(
               1 - color.getRed(), 1 - color.getGreen(), 1 - color.getBlue(), 1)));
    }

    @FXML
    private void red(ActionEvent event){
        changeImage(transformImage(currentImage,
                (y, color) -> new Color(color.getRed(), 0, 0, 1)));
    }

    @FXML
    private void redGray(ActionEvent event){
        changeImage(transformImage(currentImage, (y, color) ->{
            if (y % 2 == 1){
                double value = redRatio * color.getRed() + greenRatio *
                        color.getGreen() + blueRatio * color.getBlue();
                return new Color(value, value, value, 1);
            } else{
                return new Color(color.getRed(), 0, 0, 1);
            }
        }));
    }

    @FXML
    private void showKernel(ActionEvent e){
        if (kernelStage.isShowing()) {
            kernelStage.hide();
            showFilterButton.setText("Show Filter");
        } else {
            kernelStage.show();
            showFilterButton.setText("Hide Filter");
        }
    }

    private static Image transformImage(Image image, Transformable transformable){
        WritableImage writableImage = new WritableImage(image.getPixelReader(),
                (int)image.getWidth(), (int)image.getHeight());

        //Set each pixel to the specified color
        for (int h = 0; h < image.getHeight(); h++){
            for (int w = 0; w < image.getWidth(); w++){
                Color newColor = transformable.transform(h, image.getPixelReader().getColor(w, h));
                writableImage.getPixelWriter().setColor(w, h, newColor);
            }
        }
        return writableImage;
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
        redButton.setDisable(b);
        redGrayButton.setDisable(b);
        showFilterButton.setDisable(b);
    }
}