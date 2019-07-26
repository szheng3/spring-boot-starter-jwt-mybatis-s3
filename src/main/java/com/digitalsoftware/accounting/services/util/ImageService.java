package com.digitalsoftware.accounting.services.util;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    public InputStream convertImageToJpg(InputStream inputStream) throws IOException;
}
