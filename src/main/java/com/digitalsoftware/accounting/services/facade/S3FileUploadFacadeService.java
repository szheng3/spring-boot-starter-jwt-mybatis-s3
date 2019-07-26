package com.digitalsoftware.accounting.services.facade;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3FileUploadFacadeService {
    String upload(MultipartFile file) throws IOException;

    byte[] get(String id) throws IOException;
}
