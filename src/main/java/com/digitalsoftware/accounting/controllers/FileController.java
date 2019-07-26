package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.response.Controller.FileResponse;
import com.digitalsoftware.accounting.services.facade.S3FileUploadFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {

    private final S3FileUploadFacadeService s3FileUploadFacadeService;


    @Autowired
    public FileController(@Qualifier("s3FileUploadFacadeServiceImpl") S3FileUploadFacadeService s3FileUploadFacadeService) {
        this.s3FileUploadFacadeService = s3FileUploadFacadeService;
    }


    @PostMapping("/uploadImage")
    public FileResponse singleFileUpload(@RequestParam("file") @NotNull MultipartFile file) throws IOException {
        String upload = s3FileUploadFacadeService.upload(file);
        return FileResponse
            .Builder()
            .withPath(upload)
            .build().block();
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "public, max-age=31536000");
        response.setHeader("Pragma", "");

        return Mono.just(ResponseEntity.ok()
//            .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
            .body(s3FileUploadFacadeService.get(id))).block();

    }
}
