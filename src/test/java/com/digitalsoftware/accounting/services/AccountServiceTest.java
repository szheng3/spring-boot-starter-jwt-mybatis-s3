package com.digitalsoftware.accounting.services;

import com.digitalsoftware.accounting.configuration.JwtCommonTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AccountServiceTest extends JwtCommonTest {


    @Autowired
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;

    @Test
    public void jwtTokenTest() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
//        params.add("client_id", "fooClientIdPassword");
        params.add("username", "testUser2");
        params.add("password", "password");


        ResultActions result
            = mockMvc.perform(post("/oauth/token")
            .params(params)
            .with(httpBasic(clientId, secret))
            .accept("application/json;charset=UTF-8"))
            .andExpect(status().isOk()).andDo(print());

//        String resultString = result.andReturn().getResponse().getContentAsString();
//
//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//         jsonParser.parseMap(resultString).get("access_token").toString();

    }

    @Test
    public void loadUserByUsername() {

        accountService.loadUserByUsername("testUser2");

    }

}