/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 9: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/6/2019
 */
package kaehnd;

import edu.msoe.cs1021.ImageUtil;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Utility Class handling all image input and output
 */
public final class ImageIO {


    private static final double COLOR_SCALE = 255;
    private static final int HEX_RADIX = 16;
    private static final int HEX_MASK = 0x000000FF;
    private static final int GREEN_SHIFT = 8;
    private static final int RED_SHIFT = 16;
    private static final int ALPHA_SHIFT = 24;

    /**
     * Reads an Image from the selected Path
     *
     * @param path a specified file Path
     * @return an Image object if read was successful
     * @throws IOException            Thrown during read error
     * @throws NumberFormatException  May be thrown if MSOE file is incorrectly formatted
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

        } else if (extension.equals(".bmsoe")) {
            return readBMSOE(path);

        } else {
            throw new IOException("File extension must match \".gif\", " +
                    "\".jpg\", \".png\", \".tiff\", or \".msoe\"");
        }
    }


    /**
     * Writes a specified Image to the specified Path
     *
     * @param path  a specified file Path
     * @param image a specified Image
     * @throws IOException Thrown during write error
     */
    public static void write(Path path, Image image) throws IOException {
        String extension = path.toString().substring(path.toString().indexOf("."));
        if (extension.equals(".gif") | extension.equals(".jpg") |
                extension.equals(".png") | extension.equals(".tiff")) {
            ImageUtil.writeImage(path, image);
        } else if (extension.equals(".msoe")) {
            writeMSOE(path, image);
        } else if (extension.equals(".bmsoe")) {
            writeBMSOE(path, image);
        }
    }


    private static Image readMSOE(Path path) throws IOException,
            NumberFormatException, InputMismatchException {
        WritableImage image;

        try (Scanner in = new Scanner(new File(path.toString()))) {

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
        }
        return image;
    }

    private static void writeMSOE(Path path, Image image) throws IOException {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile()))) {
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
        }
    }

    private static Image readBMSOE(Path path) throws IOException {
        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream(path.toFile()))) {
            final int stringSize = 5;
            byte[] buffer = new byte[stringSize];
            inputStream.read(buffer);
            String header = new String(buffer);
            if (!header.equals("BMSOE")) {
                throw new IOException("Corrupt .bmose file");
            }


            int width = inputStream.readInt();
            int height = inputStream.readInt();

            WritableImage image = new WritableImage(width, height);
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    image.getPixelWriter().setColor(w, h, intToColor(inputStream.readInt()));
                }
            }
            return image;

        }
    }

    private static void writeBMSOE(Path path, Image image) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(
                new FileOutputStream(path.toFile()))) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            outputStream.write("BMSOE".getBytes());
            outputStream.writeInt(width);
            outputStream.writeInt(height);

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    outputStream.writeInt(colorToInt(image.getPixelReader().getColor(w, h)));
                }
            }

        }
    }

    /*------------------------------------------------------
        Helper methods for Color object conversion
     -----------------------------------------------------*/
    private static Color stringToColor(String s) throws NumberFormatException {

        final int redFirstIndex = 1;
        final int greenFirstIndex = 3;
        final int blueFirstIndex = 5;
        final int lastIndex = 7;
        double red = Integer.parseInt(s.substring(redFirstIndex, greenFirstIndex),
                HEX_RADIX) / COLOR_SCALE;
        double green = Integer.parseInt(s.substring(greenFirstIndex, blueFirstIndex),
                HEX_RADIX) / COLOR_SCALE;
        double blue = Integer.parseInt(s.substring(blueFirstIndex, lastIndex),
                HEX_RADIX) / COLOR_SCALE;
        return new Color(red, green, blue, 1);
    }

    private static String colorToString(Color c) {

        String s = "#";
        int red = (int) (c.getRed() * COLOR_SCALE);
        if (!(red > HEX_RADIX)) {
            s += "0";
        }
        s += Integer.toHexString(red);

        int green = (int) (c.getGreen() * COLOR_SCALE);
        if (!(green > HEX_RADIX)) {
            s += "0";
        }
        s += Integer.toHexString(green);

        int blue = (int) (c.getBlue() * COLOR_SCALE);
        if (!(blue > HEX_RADIX)) {
            s += "0";
        }
        s += Integer.toHexString(blue);
        s = s.toUpperCase();
        return s;
    }

    private static Color intToColor(int color) {
        double red = ((color >> RED_SHIFT) & HEX_MASK) / COLOR_SCALE;
        double green = ((color >> GREEN_SHIFT) & HEX_MASK) / COLOR_SCALE;
        double blue = (color & HEX_MASK) / COLOR_SCALE;
        double alpha = ((color >> ALPHA_SHIFT) & HEX_MASK) / COLOR_SCALE;
        return new Color(red, green, blue, alpha);
    }

    private static int colorToInt(Color color) {
        int red = ((int) (color.getRed() * COLOR_SCALE)) & HEX_MASK;
        int green = ((int) (color.getGreen() * COLOR_SCALE)) & HEX_MASK;
        int blue = ((int) (color.getBlue() * COLOR_SCALE)) & HEX_MASK;
        int alpha = ((int) (color.getOpacity() * COLOR_SCALE)) & HEX_MASK;
        return (alpha << ALPHA_SHIFT) + (red << RED_SHIFT) + (green << GREEN_SHIFT) + blue;
    }

    private static int callDrugs(String s){
        return doDrugs(str -> str.length(), s);
    }
    private static int doDrugs(Function<String, Integer> function, String s){
        return function.apply(s);
    }
}
