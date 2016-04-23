package by.jum.imageprocessing.processing;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoaderSaver {
    private static final Logger LOGGER = Logger.getLogger(ImageLoaderSaver.class);

    private BufferedImage image = null;

    public boolean loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
            if (image == null) {
                LOGGER.error("Error! Image don't loaded");
                return false;
            }
            LOGGER.info("Image " + path + " loaded");
            return true;
        } catch (IOException e) {
            LOGGER.error("Error! Image don't loaded");
            return false;
        }
    }

    //TODO: add saveNewImage()

    public BufferedImage getImage() {
        return image;
    }
}
