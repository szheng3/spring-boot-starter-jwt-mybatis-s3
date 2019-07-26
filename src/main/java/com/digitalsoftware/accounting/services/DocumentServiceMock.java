package com.digitalsoftware.accounting.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@Profile("test")
public class DocumentServiceMock implements DocumentService {
    @Override
    public String post(InputStream inputStream, String folderKey, Long fileSize) throws IOException {
        return null;
    }

    @Override
    public byte[] get(String path) throws IOException {
        return new byte[0];
    }
}
