package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.configuration.JwtCommonTest;
import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.emun.domain.GrantType;
import com.digitalsoftware.accounting.request.controllers.RegisterUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebFluxTest
public class UserControllerTest extends JwtCommonTest {


    @Autowired
    private TokenStore tokenStore;


    @Test
    public void getCurrentUser() throws Exception {


//        webClient.get().uri("").accept(MediaType.APPLICATION_JSON).h

        String contentAsString = mockMvc.perform(getAuth("/api/currentUser", null, OauthType.USER))
            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.status", equalTo(1)));
            .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void get401CurrentUser() throws Exception {


//        webClient.get().uri("").accept(MediaType.APPLICATION_JSON).h

        String contentAsString = mockMvc.perform(get("/api/currentUser"))
            .andExpect(status().is4xxClientError())
//            .andExpect(jsonPath("$.status", equalTo(1)));
            .andReturn().getResponse().getContentAsString();

    }



    @Test
    public void testToken() throws Exception {
        String accessToken = obtainAccessToken("testUser2", "password", GrantType.PASSWORD);
        OAuth2Authentication auth = tokenStore.readAuthentication(accessToken);
        Map<String, Object> details = (Map<String, Object>) auth.getDetails();
        assertTrue(details.containsKey("id"));

    }

    @Test
    public void registerUser() throws Exception {
//
        User user = new User(null, null, null, "12345", "1234", null);


        String authorization = mockMvc.perform(postAuth("/api/registerUser", new RegisterUser(user), OauthType.CLIENT))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", equalTo(0)))
            .andReturn().getResponse().getContentAsString();

        User user2 = new User(null, "2134", "12344", "12345", "1234", null);

        String contentAsString = mockMvc.perform(postAuth("/api/registerUser", new RegisterUser(user2), OauthType.CLIENT))
//            .andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo(1)))
            .andReturn().getResponse().getContentAsString();


    }

    @Test
    public void updateUser() throws Exception {

        User user = new User(null, null, null, null, "1234", null);


        String authorization = mockMvc.perform(postAuth("/api/updateUser", new RegisterUser(user), OauthType.CLIENT))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void getUser() throws Exception {

        String contentAsString = mockMvc.perform(getAuth("/api/admin/user/93", null, OauthType.ADMIN))
            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.status", equalTo(1)));
            .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getUserExpect401() throws Exception {

        String contentAsString = mockMvc.perform(getAuth("/api/admin/user/93", null, OauthType.USER))
            .andExpect(status().is4xxClientError())
//            .andExpect(jsonPath("$.status", equalTo(1)));
            .andReturn().getResponse().getContentAsString();
    }
}