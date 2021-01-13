/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class ImageIO
 * Name: poptilec
 * Created 2/4/2020
 */
package poptilec;

import edu.msoe.cs1021.ImageUtil;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class handles loading, saving, and transforming images
 */
public class ImageIO {

    private static WritableImage image;
    /**
     * Logger to be used to document any transformations to the image
     * as well as an exceptions
     */
    public static Logger logger = Logger.getLogger("logger");
    private static final int ALPHA_VALUE = 24;
    private static final int RED_VALUE = 16;
    private static final int GREEN_VALUE = 8;
    private static final int HEX_VALUE = 0xff;
    private static final int COLOR_MAX = 255;
    private static final double RED_FACTOR = 0.2162;
    private static final double GREEN_FACTOR = 0.7152;
    private static final double BLUE_FACTOR = 0.0722;

    /**Method reads file to display image
     * @param path path to image file selected
     * @return image to be displayed
     * @throws IOException IO exception
     * @throws IllegalArgumentException exception if extension is unsupported
     */
    public static javafx.scene.image.Image read(Path path) throws
            IOException, IllegalArgumentException {
        if (path.getFileName().toString().endsWith(".msoe")){
            readMSOE(path);
            logger.log(Level.INFO, "a .msoe image was uploaded");
            return image;
        } else {
            image = (WritableImage) ImageUtil.readImage(path);
            logger.log(Level.INFO, "an image was uploaded");
            return image;
        }
    }

    /** Method saves a file of image
     * @param image image to be saved
     * @param path path to image chosen
     * @throws IOException IO exception thrown
     * @throws IllegalArgumentException exception thrown if unsupported extension is passed in
     */
    public static void write(Image image, Path path) throws IOException, IllegalArgumentException {
        if (path.getFileName().toString().endsWith(".msoe")) {
            writeMSOE(image, path);
            logger.log(Level.INFO, "a .msoe image was saved");
        } else {
            ImageUtil.writeImage(path, image);
            logger.log(Level.INFO, "image was saved");
        }
    }

    /** Method saves image as a .msoe file
     * @param path path for image to be saved to
     */
    private static void readMSOE(Path path){
        try (Scanner scan = new Scanner(path.toFile())) {
            List<String> fileLines = new ArrayList<>();
            while (scan.hasNextLine()) {
                fileLines.add(scan.nextLine());
            }
            String header = fileLines.get(0);
            if (header.equalsIgnoreCase("MSOE")) {
                String[] dimensions = fileLines.get(1).split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);

                WritableImage upload = new WritableImage(width, height);
                PixelWriter setImage = upload.getPixelWriter();

                for (int y = 2; y < fileLines.size(); y++) {
                    String[] pixels = fileLines.get(y).split("\\s+");
                    for (int x = 0; x < pixels.length; x++) {
                        Color hex = Color.web(pixels[x]);

                        setImage.setColor(x, y - 2, hex);

                    }
                }
                image = upload;
            }
        } catch (IOException e) {
            Alert ioException = new Alert(Alert.AlertType.ERROR, "An IO exception has occurred");
            ioException.showAndWait();
            logger.log(Level.WARNING, "io exception when reading file");
        } catch (NumberFormatException e) {
            Alert number = new Alert(Alert.AlertType.ERROR, "invalid dimensions");
            number.showAndWait();
            logger.log(Level.WARNING, "invalid dimensions passed into picture");
        }
    }

    /** Method saves image to specified path
     * @param image image to be saved
     * @param path location where image is saved
     */
    private static void writeMSOE(Image image, Path path){
        File file = new File(path.getFileName().toString());
        try (PrintWriter writer = new PrintWriter(path.toString());){
            PixelReader reader = image.getPixelReader();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            writer.write("MSOE\n");
            writer.write(width+" "+height+"\n");
            for (int y = 0; y < height; y++) {

                String line = "";
                for (int x = 0; x < width; x++) {
                    int pixel = reader.getArgb(x, y);
                    int red = ((pixel >> RED_VALUE) & HEX_VALUE);
                    int green = ((pixel >> GREEN_VALUE) & HEX_VALUE);
                    int blue = (pixel & HEX_VALUE);
                    String hex = String.format("#%02x%02x%02x", red, green, blue);
                    line += hex+" ";
                }
                writer.write(line+"\n");
            }
        } catch (IOException e){
            Alert ioException = new Alert(Alert.AlertType.ERROR, "An IO exception has occurred");
            ioException.showAndWait();
            logger.log(Level.WARNING, "io exception when saving file");
        }
    }

    /**Method transforms image to graysclae
     * @param image image to be transformed
     * @return image that is transformed into grayscale
     */
    public static javafx.scene.image.Image gray(Image image){
        PixelReader grayscale = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage grayImage = new WritableImage(width, height);
        PixelWriter setGray = grayImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = grayscale.getArgb(x, y);
                int red = ((pixel >> RED_VALUE) & HEX_VALUE);
                int green = ((pixel >> GREEN_VALUE) & HEX_VALUE);
                int blue = (pixel & HEX_VALUE);

                int grayLevel = (int) (((RED_FACTOR*(double)red)+
                        (GREEN_FACTOR * (double)green) + (BLUE_FACTOR * (double)blue)));
                grayLevel = COLOR_MAX- grayLevel;
                int gray = (grayLevel << RED_VALUE) + (grayLevel << GREEN_VALUE) + grayLevel;

                setGray.setArgb(x, y, -gray);

            }
        }
        logger.log(Level.INFO, "image was transformed to grayscale");
        return grayImage;
    }

    /** Method turns image into a negative image
     * @param image image to be transformed
     * @return negative image
     */
    public static javafx.scene.image.Image negative(Image image){
        PixelReader negative = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage negativeImage = new WritableImage(width, height);
        PixelWriter setNegative = negativeImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = negative.getArgb(x, y);

                int a = (pixel>>ALPHA_VALUE)&HEX_VALUE;
                int red = ((pixel >> RED_VALUE) & HEX_VALUE);
                int green = ((pixel >> GREEN_VALUE) & HEX_VALUE);
                int blue = (pixel & HEX_VALUE);

                red = COLOR_MAX - red;
                green = COLOR_MAX - green;
                blue = COLOR_MAX - blue;

                pixel = (a<<ALPHA_VALUE) | (red << RED_VALUE) + (green << GREEN_VALUE) | blue;

                setNegative.setArgb(x, y, pixel);

            }
        }
        logger.log(Level.INFO, "image was transformed to a negative");
        return negativeImage;
    }

}
