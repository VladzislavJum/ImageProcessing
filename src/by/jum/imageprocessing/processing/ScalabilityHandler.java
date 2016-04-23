package by.jum.imageprocessing.processing;

import java.awt.image.BufferedImage;

public class ScalabilityHandler {
    public BufferedImage createScalabilityImage(BufferedImage originalImage, double scalability) {
        int width = (int) (originalImage.getWidth() * scalability);
        int height = (int) (originalImage.getHeight() * scalability);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int x, y;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                x = (int) (i / scalability);
                y = (int) (j / scalability);
                newImage.setRGB(i, j, originalImage.getRGB(x, y));
            }
        }
        return newImage;
    }
}
