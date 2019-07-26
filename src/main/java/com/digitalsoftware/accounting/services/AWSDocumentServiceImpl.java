package com.digitalsoftware.accounting.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@Profile({"dev", "prod"})
public class AWSDocumentServiceImpl implements DocumentService {

    private final AmazonS3 s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

//    @Value("${my.domain}")
//    private String myDomain;


    @Autowired
    public AWSDocumentServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String post(InputStream inputStream, String folderKey, Long fileSize) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(fileSize);
        s3Client.putObject(new PutObjectRequest(bucketName, folderKey, inputStream, meta));
        return "/api/" + folderKey;
    }

    @Override
    public byte[] get(String path) throws IOException {
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, path));


        return IOUtils.toByteArray(object.getObjectContent());
    }

}
