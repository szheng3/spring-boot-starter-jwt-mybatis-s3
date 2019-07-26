package com.digitalsoftware.accounting.util;

import net.coobird.thumbnailator.filters.ImageFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ThumbnailsImgFilter implements ImageFilter {
    @Override
    public BufferedImage apply(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = newImage.createGraphics();
        graphic.setColor(Color.white);//white
        graphic.fillRect(0, 0, w, h);
        graphic.drawRenderedImage(img, null);
        graphic.dispose();
        return newImage;
    }
}

