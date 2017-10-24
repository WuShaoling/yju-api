package com.guanshan.phoenix.Util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {

    public static ImageInfo getImageInfo(String location) {
        ImageInfo imageInfo = new ImageInfo();

        File image = new File(location);
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(image));
            imageInfo.setName(image.getName());
            imageInfo.setWidth(bufferedImage.getWidth());
            imageInfo.setHeight(bufferedImage.getHeight());
            return imageInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static class ImageInfo {
        private String name;
        private int width;
        private int height;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }


}
