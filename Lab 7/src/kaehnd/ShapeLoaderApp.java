/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 7: Shapes Revisited
 * Name: Daniel Kaehn
 * Created: 1/24/2019
 */

package kaehnd;

import edu.msoe.winplotterfx.WinPlotterFX;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Loads Shapes from file and plots them in JavaFX Stage using WinPlotterFX
 */
public class ShapeLoaderApp extends Application {

    private ArrayList<Shape> shapes;

    public static void main(String[] args) {
        launch();
    }

    /**
     * Builds and initializes the WinPlotterFX Application
     * @param stage stage object for displaying FileChooser
     */
    @Override
    public void start(Stage stage) {
        //Instantiate objects
        WinPlotterFX plotter = new WinPlotterFX();
        FileChooser fileChooser = new FileChooser();
        shapes = new ArrayList<>();

        //Get file path
        File file = fileChooser.showOpenDialog(stage);

        try(Scanner in = new Scanner(file)) {

            plotter.setWindowTitle(in.nextLine());
            plotter.setWindowSize(in.nextInt(), in.nextInt());

            in.nextLine(); //Takes newline character left by nextInt()

            Color color = stringToColor(in.nextLine());
            plotter.setBackgroundColor(color.getRed(), color.getGreen(), color.getBlue());

            readShapes(in);

            drawShapes(plotter);

            plotter.showPlotter();

        } catch (FileNotFoundException e){
            throwAlert("File Error", "File not found.");
        } catch (NullPointerException e){
            throwAlert("Null File Error", "No file chosen.");
        } catch (InputMismatchException e){
            throwAlert("File Error", "Error in stage declaration (first three lines of file).");
        }
    }

    //Reads in Shapes from file line by line, adds them to an ArrayList, and
    // catches exceptions resulting from invalid Shapes
    private void readShapes(Scanner in) {
        while(in.hasNextLine()) {
            try {
                shapes.add(parseShape(in.nextLine()));
            } catch (NumberFormatException e) {
                throwAlert("Shape Error", "Invalid shape argument.");
            } catch (IllegalArgumentException e) {
                throwAlert("Shape Error", e.getMessage());
            } catch (NoSuchElementException e) {
                throwAlert("Shape Declaration Error", "Shape data format error.");
            } catch (StringIndexOutOfBoundsException e){
                throwAlert("Shape Declaration Error",
                        "No colon after shape type in shape declaration.");
            }
        }
    }

    //Draws all valid Shapes in ArrayList
    private void drawShapes(WinPlotterFX plotter){
        for(Shape shape: shapes){
            shape.draw(plotter);
        }
    }

    //Creates a Shape object from line declaration in file
    private static Shape parseShape(String s)
            throws IllegalArgumentException, NoSuchElementException {

        if (s.isEmpty()) {
            throw new IllegalArgumentException("Empty line in file");
        }

        //Store declared Shape type
        String type = s.substring(0, s.indexOf(":"));

        //Shorten String
        s = s.substring(s.indexOf(":") + 1);

        //Create a Scanner to read data from String s, skipping whitespace
        Scanner in = new Scanner(s);

        int xCoord = in.nextInt();
        int yCoord = in.nextInt();

        Color color = stringToColor(in.next());

        //ints declared for use in case statement
        int int1 = 0;
        int int2 = 0;

        //Stores values for base and height or width and height or radius and zero for below shapes
        if (in.hasNextInt()){
            int1 = in.nextInt();
        }
        if (in.hasNextInt()){
            int2 = in.nextInt();
        }

        //Determine which shape is declared, and instantiate it
        switch(type) {
            case "P":
                return new Point(xCoord, yCoord, color);
            case "C":
                return new Circle(xCoord, yCoord, int1, color);
            case "T":
                return new Triangle(xCoord, yCoord, int1, int2, color);
            case "R":
                return new Rectangle(xCoord, yCoord, int1, int2, color);
            case "LT":
                s = s.substring(1 + s.lastIndexOf(String.valueOf(int2)) +
                        String.valueOf(int2).length());
                return new LabeledTriangle(xCoord, yCoord, int1, int2, color, s);
            case "LR":
                s = s.substring(1 + s.lastIndexOf(String.valueOf(int2)) +
                        String.valueOf(int2).length());
                return new LabeledRectangle(xCoord, yCoord, int1, int2, color, s);
            default:
                throw new IllegalArgumentException("Invalid shape type declaration in file");
        }
    }

    //Takes a hex string such as"#FF0F89" and returns a Color object of brightness 1
    // with appropriate RGB values
    private static Color stringToColor(String s){
        final double scale = 255;
        final int hexRadix = 16;
        final int redFirstIndex = 1;
        final int greenFirstIndex = 3;
        final int blueFirstIndex = 5;
        final int lastIndex = 7;
        double red = Integer.parseInt(s.substring(redFirstIndex, greenFirstIndex),
                hexRadix) / scale;
        double green = Integer.parseInt(s.substring(greenFirstIndex, blueFirstIndex),
                hexRadix) / scale;
        double blue = Integer.parseInt(s.substring(blueFirstIndex, lastIndex),
                hexRadix) / scale;
        return new Color(red, green, blue, 1);
    }

    //Throws an alert with an inputted header and message
    private void throwAlert(String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
