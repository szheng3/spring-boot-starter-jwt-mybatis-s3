package com.digitalsoftware.accounting.util;


import net.coobird.thumbnailator.Thumbnails;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {

    public static ByteArrayOutputStream jpgScaleDown(BufferedImage sbi, int maxSize, float quality) throws IOException {
        if (sbi == null)
            return null;

        int dstWidth = sbi.getWidth();
        int dstHeight = sbi.getHeight();
        if (dstWidth > maxSize) {
            dstWidth = maxSize;
            dstHeight = sbi.getHeight() * maxSize / sbi.getWidth();
        }
        if (dstHeight > maxSize) {
            dstHeight = maxSize;
            dstWidth = sbi.getWidth() * maxSize / sbi.getHeight();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Thumbnails.of(sbi)
            .size(dstWidth, dstHeight)
            .addFilter(new ThumbnailsImgFilter())
            .imageType(BufferedImage.TYPE_INT_ARGB)
            .outputQuality(quality)
            .outputFormat("jpg")
            .toOutputStream(os);

        return os;
    }

    public static ByteArrayOutputStream svgToJpg(String svgURI) throws TranscoderException {

        JPEGTranscoder t = new JPEGTranscoder();

// Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 1f);

// Create the transcoder input.
        TranscoderInput input = new TranscoderInput(svgURI);

// Create the transcoder output.
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(ostream);

// Save the image.
        t.transcode(input, output);


        return ostream;
    }


}

