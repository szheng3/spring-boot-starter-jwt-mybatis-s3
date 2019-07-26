package com.digitalsoftware.accounting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ActiveProfiles("test")
public class MyTokenFactory {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;


    public enum MyToken {
        USER
    }


    @Bean
    public Map<MyToken, String> MyTokenStore() throws Exception {


        return new HashMap<MyToken, String>() {
            {


            }
        };
    }


}
