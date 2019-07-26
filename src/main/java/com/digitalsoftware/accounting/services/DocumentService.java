package com.digitalsoftware.accounting.services;

import java.io.IOException;
import java.io.InputStream;

public interface DocumentService {
    String post(InputStream inputStream, String folderKey, Long fileSize) throws IOException;

    byte[] get(String path) throws IOException;
}

