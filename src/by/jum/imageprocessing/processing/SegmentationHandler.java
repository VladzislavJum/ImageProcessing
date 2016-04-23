package by.jum.imageprocessing.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SegmentationHandler {
    private int value;
    private BufferedImage originalImage;
    private List<Integer> segmentationRGB;

    public SegmentationHandler(BufferedImage originalImage, int value) {
        this.originalImage = originalImage;
        this.value = value;
    }

    public BufferedImage getSegmentationImage() {
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        segmentationRGB = new ArrayList<>();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        segmentationRGB.add(originalImage.getRGB(0, 0));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, computeRGB(i, j));
            }
        }
        return image;
    }

    private int computeRGB(int x, int y) {
        int red, green, blue;
        int currentRGB = (originalImage.getRGB(x, y));
        Color standartColor = new Color(currentRGB);

        for (Integer rgb : segmentationRGB) {
            Color segmentColor = new Color(rgb);
            red = Math.abs(standartColor.getRed() - segmentColor.getRed());
            green = Math.abs(standartColor.getGreen() - segmentColor.getGreen());
            blue = Math.abs(standartColor.getBlue() - segmentColor.getBlue());
            if (red < value && green < value && blue < value) return rgb;
        }
        segmentationRGB.add(originalImage.getRGB(x, y));
        return currentRGB;
    }
}
