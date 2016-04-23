package by.jum.imageprocessing.processing;

import java.awt.image.BufferedImage;

public class RotationHandler {

    public BufferedImage createRotationImage(BufferedImage originalImage, double angle) {
        int width = (int) (Math.sqrt(Math.pow(originalImage.getWidth(), 2) + Math.pow(originalImage.getHeight(), 2)));
        int height = width;
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int originalX, originalY;
        int newX, newY, x, y, pixelPositionX, pixelPositionY;

        angle = Math.toRadians(angle);
        originalX = originalImage.getWidth() / 2;
        originalY = originalImage.getHeight() / 2;
        newX = width / 2;
        newY = height / 2;
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                x = i - newX;
                y = j - newY;
                pixelPositionX = (int) Math.round(x * Math.cos(angle) + y * Math.sin(angle)) + originalX;
                pixelPositionY = (int) Math.round(y * Math.cos(angle) - x * Math.sin(angle)) + originalY;
                if (pixelPositionX > 0 && pixelPositionY > 0 && pixelPositionX < originalImage.getWidth() && pixelPositionY < originalImage.getHeight()) {
                    newImage.setRGB(i, j, originalImage.getRGB(pixelPositionX, pixelPositionY));
                }
            }
        }
        return newImage;
    }
}
