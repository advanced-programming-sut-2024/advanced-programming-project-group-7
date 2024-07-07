package controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static void main(String[] args) {
        try {
            // Read the image file
            File inputFile = new File("/Images/icons/card_ability_scorch.png");
            BufferedImage inputImage = ImageIO.read(inputFile);

            // Create a new BufferedImage with 32bpp
            BufferedImage newImage = new BufferedImage(
                    inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Draw the original image on the new image
            newImage.getGraphics().drawImage(inputImage, 0, 0, null);

            // Write the new image to a file
            File outputFile = new File("/Images/icons/card_ability_scorch.png");
            ImageIO.write(newImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

