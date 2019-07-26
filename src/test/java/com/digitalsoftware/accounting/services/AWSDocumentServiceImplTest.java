package com.digitalsoftware.accounting.services;

import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AWSDocumentServiceImplTest {

    @Autowired
    private DocumentService documentService;

    @Test
    @Ignore
    public void post() throws IOException {
        File file = new File("/Users/shuai/DigitalSoftware/backend/src/main/resources/public.txt");
        documentService.post(Files.asByteSource(file).openStream(), "public.txt", file.length());
    }

    @Test
    @Ignore
    public void get() throws IOException {
        Assert.assertNotNull(documentService.get("public.txt"));
    }
}