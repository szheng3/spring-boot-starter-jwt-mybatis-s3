package com.digitalsoftware.accounting.services.util;

import com.digitalsoftware.accounting.util.ImageUtil;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public InputStream convertImageToJpg(InputStream inputStream) throws IOException {


        BufferedImage imBuff = ImageIO.read(inputStream);
//        BufferedImage bufferedImage = ImageUtil.convertToJpg(imBuff);
//        byte[] compress = ImageUtil.compressJpg(bufferedImage, 0.8f);

        return new ByteArrayInputStream(ImageUtil.jpgScaleDown(imBuff, 1080, 1f).toByteArray());
    }
}
