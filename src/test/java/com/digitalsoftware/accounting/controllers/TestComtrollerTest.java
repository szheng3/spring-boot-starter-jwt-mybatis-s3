//package com.digitalsoftware.accounting.controllers;
//
//import com.digitalsoftware.accounting.configuration.JwtCommonTest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//public class TestComtrollerTest extends JwtCommonTest {
//
//
//    @Test
//    public void gettest() throws Exception {
//
//        obtainAccessToken("testUser2", "password");
//
//        mockMvc.perform(getAuth("/api/test1")).andExpect(status().isOk());
//        mockMvc.perform(getAuth("/api/test2")).andExpect(status().isOk());
//    }
//}