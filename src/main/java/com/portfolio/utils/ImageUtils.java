package com.portfolio.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtils {

    public static void saveImageWithThumbnail(File originalImageFile, File thumbnailFile) throws IOException {
        // Read the original image from file
        BufferedImage originalImage = ImageIO.read(originalImageFile);

        // Generate a thumbnail
        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .size(150, 150)
                .asBufferedImage();

        // Save the original image and thumbnail to file
        ImageIO.write(originalImage, "jpg", originalImageFile);
        ImageIO.write(thumbnail, "jpg", thumbnailFile);
    }

}

