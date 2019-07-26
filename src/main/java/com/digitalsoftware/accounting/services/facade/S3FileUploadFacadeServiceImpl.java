package com.digitalsoftware.accounting.services.facade;

import com.digitalsoftware.accounting.services.DocumentService;
import com.digitalsoftware.accounting.services.util.ImageService;
import com.fasterxml.uuid.Generators;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3FileUploadFacadeServiceImpl implements S3FileUploadFacadeService {

    private final DocumentService documentService;

    private final ImageService imageService;


    @Autowired
    public S3FileUploadFacadeServiceImpl(DocumentService documentService, ImageService imageService) {
        this.documentService = documentService;
        this.imageService = imageService;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

        switch (extension) {
            case "jpg":
            case "jpeg":
            case "png": {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                    .append("image/")
                    .append(Generators.timeBasedGenerator().generate().toString().replace("-", ""))
                    .append(".")
                    .append("jpg");
                InputStream input = imageService.convertImageToJpg(file.getInputStream());
                byte[] bytes = IOUtils.toByteArray(input);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                return documentService.post(inputStream, stringBuilder.toString(), (long) bytes.length);
            }
            default: {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                    .append("image/")
                    .append(Generators.timeBasedGenerator().generate().toString().replace("-", ""))
                    .append(".")
                    .append(FilenameUtils.getExtension(file.getOriginalFilename()));


                return documentService.post(file.getInputStream(), stringBuilder.toString(), file.getSize());
            }
        }



    }

    @Override
    public byte[] get(String id) throws IOException {
        return documentService.get("image/" + id);
    }
}
