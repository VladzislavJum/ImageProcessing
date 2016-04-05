package by.jum.imageprocessing.processing;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageScanner {

    private static final Logger LOGGER = Logger.getLogger(ImageScanner.class);

    private BufferedImage originalImage;
    private BufferedImage image;
    private int height;
    private int width;

    public ImageScanner(BufferedImage originalImage) {
        this.originalImage = originalImage;
        height = originalImage.getHeight();
        width = originalImage.getWidth();
    }

    public BufferedImage anaglyphImage() {
        image = new BufferedImage(width / 2, height, BufferedImage.TYPE_INT_RGB);
        Color colorLeft, colorRight;
        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < height; j++) {
                colorLeft = new Color(originalImage.getRGB(i, j));
                colorRight = new Color(originalImage.getRGB(i + width / 2, j));
                image.setRGB(i, j, new Color(colorLeft.getRed(), colorRight.getGreen(), colorRight.getBlue()).getRGB());
            }
        }
        return image;
    }

    public BufferedImage filterImage(double filter[][], int indent) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, cumputeRGB(filter, i, j, indent));
            }
        }
        return image;
    }

    private int cumputeRGB(double filter[][], int x, int y, int indent) {
        int length = filter.length;
        int xBound, yBound, sum = 0;
        Color color;
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                xBound = x + i - length / 2;
                yBound = y + j - length / 2;
                if (xBound >= 0 && yBound >= 0 && xBound < width && yBound < height) {
                    color = new Color(originalImage.getRGB(xBound, yBound));
                    red += (int) (filter[i][j] * color.getRed());
                    green += (int) (filter[i][j] * color.getGreen());
                    blue += (int) (filter[i][j] * color.getBlue());
                }
            }
        }
        sum = new Color(checkBound(red / indent), checkBound(green / indent), checkBound(blue / indent)).getRGB();
        return sum;
    }

    private int checkBound(int chanel) {
        if (chanel > 255) {
            return 255;
        } else if (chanel < 0) {
            return 0;
        }
        return chanel;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage negativ() {
        Random random = new Random();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int red = 0, green = 0, blue = 0;
        Color color;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                color = new Color(originalImage.getRGB(i, j));
                red = 255 - (color.getRed());
                green = 255 - (color.getGreen());
                blue = 255 -  (color.getBlue());
                image.setRGB(i, j, new Color(red, green, blue).getRGB());
            }
        }
        return image;
    }


    public BufferedImage segmentationImage(int value){
        return new SegmentHandler(originalImage, value).getSegmentationImage();
    }
}
