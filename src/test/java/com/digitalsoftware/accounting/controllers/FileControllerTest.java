package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.configuration.JwtCommonTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FileControllerTest extends JwtCommonTest {


    @Test
    public void singleFileUpload() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.png").getFile());
        MockMultipartFile firstFile = new MockMultipartFile("file", new FileInputStream(file));

//        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());


        mockMvc.perform(postFile("/api/uploadImage", firstFile, OauthType.USER)
//            .param("some-random", "4")
        )
            .andExpect(status().isOk());

    }

    @Test
    public void getImage() throws Exception {
        ResultActions resultActions = mockMvc.perform(getAuth("/api/image/1b0dfffc913011e88a5a27c30e4b9835.txt", null, OauthType.USER)
//            .param("some-random", "4")

        )
            .andExpect(status().isOk());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
    }

    @Test
    public void getNoAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/image/1b0dfffc913011e88a5a27c30e4b9835.txt")
//            .param("some-random", "4")

        )
            .andExpect(status().isOk());
        MockHttpServletResponse
            response = resultActions.andReturn().getResponse();
    }
}