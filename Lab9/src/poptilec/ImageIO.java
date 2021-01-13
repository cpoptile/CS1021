/*
 * Course: CS1021-091
 * Winter 2019
 * File header contains class ImageIO
 * Name: poptilec
 * Created 2/18/2020
 */
package poptilec;

import edu.msoe.cs1021.ImageUtil;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    /**
     * transformed image to be transferred to the imageView
     * in Lab9Controller
     */
    public static WritableImage updatingImage;

    /**
     * Logger to be used to document any transformations to the image
     * as well as an exceptions
     */
    public static Logger logger = Logger.getLogger("logger");
    private static final int RED_VALUE = 16;
    private static final int GREEN_VALUE = 8;
    private static final int HEX_VALUE = 0xff;

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
            return updatingImage;
        } else if (path.getFileName().toString().endsWith(".bmsoe")){
            readBMSOE(path);
            logger.log(Level.INFO, "a .bmsoe image was uploaded");
            return updatingImage;
        } else {
            updatingImage = (WritableImage) ImageUtil.readImage(path);
            logger.log(Level.INFO, "an image was uploaded");
            return updatingImage;
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
        } else if (path.getFileName().toString().endsWith(".bmsoe")){
            writeBMSOE(image, path);
            logger.log(Level.INFO, "a .bmsoe image was saved");
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
                updatingImage = upload;
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

    /** Method loads in image from a .bmsoe file
     * @param path path of file to be read
     */
    private static void readBMSOE(Path path) {
        try(DataInputStream is = new DataInputStream(new BufferedInputStream(
                new FileInputStream(path.toFile())))) {
            char b = (char)is.readByte();
            char m = (char)is.readByte();
            char s = (char)is.readByte();
            char o = (char)is.readByte();
            char e = (char)is.readByte();
            if (b=='B' && m=='M' && s=='S' && o=='O' && e=='E'){
                int width = is.readInt();
                int height = is.readInt();
                WritableImage upload = new WritableImage(width, height);
                PixelWriter writer = upload.getPixelWriter();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int pixel = is.readInt();
                        writer.setArgb(x, y, pixel);
                    }
                }
                updatingImage = upload;
            } else {
                Alert wrongFile = new Alert(Alert.AlertType.ERROR, "File type not supported");
                wrongFile.showAndWait();
                logger.log(Level.WARNING, "file is not a .bmsoe file");
            }
        } catch (IOException e) {
            Alert ioException = new Alert(Alert.AlertType.ERROR, "An IO exception has occurred");
            ioException.showAndWait();
            logger.log(Level.WARNING, "io exception when loading file");
        }
    }

    /** Method saves an image as a .bmsoe file
     * @param image image to be saved
     * @param path path to file image is saved to
     */
    private static void writeBMSOE(Image image, Path path){
        try(DataOutputStream os = new DataOutputStream(new FileOutputStream(path.toFile()))) {
            PixelReader reader = image.getPixelReader();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            os.writeBytes("B");
            os.writeBytes("M");
            os.writeBytes("S");
            os.writeBytes("O");
            os.writeBytes("E");
            os.writeInt(width);
            os.writeInt(height);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = reader.getArgb(x, y);
                    os.writeInt(pixel);
                }
            }
        } catch (IOException e) {
            Alert ioException = new Alert(Alert.AlertType.ERROR, "An IO exception has occurred");
            ioException.showAndWait();
            logger.log(Level.WARNING, "io exception when saving file");
        }
    }

    /** Method saves image to specified path
     * @param image image to be saved
     * @param path location where image is saved
     */
    private static void writeMSOE(Image image, Path path){
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
    public static Image gray(Image image){
        Transformable grayscale = (y, c) -> c.grayscale();
        Image greyImage = transformImage(image, grayscale);
        return greyImage;
    }

    /** Method turns image into a negative image
     * @param image image to be transformed
     * @return negative image
     */
    public static javafx.scene.image.Image negative(Image image){
        Transformable negative = (y, c) -> c.invert();
        Image negativeImage = transformImage(image, negative);
        return negativeImage;
    }

    /** Method puts a red filter on the current image
     * @param image image to be transformed
     * @return image with red filter
     */
    public static javafx.scene.image.Image red(Image image){
        Transformable red = (y, c) -> {
            double redValue = c.getRed();
            return new Color(redValue, 0, 0, 1);
        };
        Image redImage = transformImage(image, red);
        return redImage;
    }

    /** Method puts a red-grey filter on the current image
     * @param image image to be transformed
     * @return image with red-grey filter
     */
    public static javafx.scene.image.Image redGray(Image image){
        Transformable redGrey = (y, c) -> {
            if (y%2 == 0){
                return c.grayscale();
            } else {
                double redValue = c.getRed();
                return new Color(redValue, 0, 0, 1);
            }
        };
        Image redGreyImage = transformImage(image, redGrey);
        return redGreyImage;
    }

    /**Method applies a transformation to an image
     * @param image image to be transformed
     * @param transform transformation to be applied
     * @return image with applied transformation
     */
    private static Image transformImage(Image image, Transformable transform) {
        PixelReader pixelReader = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage newImage = new WritableImage(width, height);
        PixelWriter pixelWriter = newImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, transform.apply(y, pixelColor));
            }
        }
        updatingImage = newImage;
        return newImage;
    }

    /**Method applies a kernel to current image
     * @param kernel kernel to be applied to image
     * @return image with kernel applied
     */
    public static Image filterImage(double[] kernel){
        Image filtered = ImageUtil.convolve(updatingImage, kernel);

        PixelReader reader = filtered.getPixelReader();
        int width = (int) filtered.getWidth();
        int height = (int) filtered.getHeight();

        WritableImage filteredImage = new WritableImage(width, height);
        PixelWriter setImage = filteredImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = reader.getArgb(x, y);
                setImage.setArgb(x, y, pixel);
            }
        }
        updatingImage = filteredImage;
        return filtered;
    }


}

