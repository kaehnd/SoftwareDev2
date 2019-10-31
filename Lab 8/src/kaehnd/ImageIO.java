/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 8: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/2/2019
 */
package kaehnd;

import edu.msoe.cs1021.ImageUtil;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility Class handling all image input and output
 */
public final class ImageIO {

    /**
     * Reads an Image from the selected Path
     * @param path a specified file Path
     * @return an Image object if read was successful
     * @throws IOException Thrown during read error
     * @throws NumberFormatException May be thrown if MSOE file is incorrectly formatted
     * @throws InputMismatchException May be thrown if MSOE file is incorrectly formatted
     */
    public static Image read(Path path) throws IOException,
            NumberFormatException, InputMismatchException {
        String extension = path.toString().substring(path.toString().lastIndexOf("."));
        if (extension.equals(".gif") | extension.equals(".jpg") |
                extension.equals(".png") | extension.equals(".tiff")) {
            return ImageUtil.readImage(path);

        } else if (extension.equals(".msoe")) {
            return readMSOE(path);

        } else {
            throw new IOException("File extension must match \".gif\", " +
                    "\".jpg\", \".png\", \".tiff\", or \".msoe\"");
        }
    }

    /**
     * Writes a specified Image to the specified Path
     * @param path a specified file Path
     * @param image a specified Image
     * @throws IOException Thrown during write error
     */
    public static void write(Path path, Image image) throws IOException{
        String extension = path.toString().substring(path.toString().indexOf("."));
        if (extension.equals(".gif") | extension.equals(".jpg") |
                extension.equals(".png") | extension.equals(".tiff")) {
            ImageUtil.writeImage(path, image);
        } else if (extension.equals(".msoe")) {
            writeMSOE(path, image);
        }
    }

    private static Image readMSOE(Path path) throws IOException,
            NumberFormatException, InputMismatchException{
        Scanner in = null;
        WritableImage image;
        try {
            in = new Scanner(new File(path.toString()));
            if (!in.nextLine().equals("MSOE")) {
                throw new IOException("Not an MSOE file");
            }
            int width = in.nextInt();
            int height = in.nextInt();
            in.nextLine();

            image = new WritableImage(width, height);

            for (int h = 0; h < height; h++) {
                String line = in.nextLine();
                Scanner lineReader = new Scanner(line);
                for (int w = 0; w < width; w++) {
                    image.getPixelWriter().setColor(w, h, stringToColor(lineReader.next()));
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return image;
    }

    private static void writeMSOE(Path path, Image image) throws IOException{
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(path.toFile()));
            writer.println("MSOE");
            writer.println(width + " " + height);

            for (int h = 0; h < height; h++) {
                String line = "";
                for (int w = 0; w < width; w++) {
                    line += colorToString(image.getPixelReader().getColor(w, h));
                    line += " ";
                }
                writer.println(line);
            }
        } finally{
            if (writer != null) {
                writer.close();
            }
        }
    }



    private static Color stringToColor(String s) throws NumberFormatException{
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

    private static String colorToString(Color c){
        final int scale = 255;
        final int hexRadix = 16;
        String s = "#";
        int red = (int) (c.getRed() * scale);
        if (!(red > hexRadix)){
            s += "0";
        }
        s += Integer.toHexString(red);

        int green = (int) (c.getGreen() * scale);
        if (!(green > hexRadix)){
            s += "0";
        }
        s += Integer.toHexString(green);

        int blue = (int) (c.getBlue() * scale);
        if (!(blue > hexRadix)){
            s += "0";
        }
        s += Integer.toHexString(blue);
        s = s.toUpperCase();
        return s;
    }
}
