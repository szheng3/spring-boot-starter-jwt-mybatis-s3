package com.digitalsoftware.accounting.configuration;

import com.digitalsoftware.accounting.configuration.tokenrequest.JwtTokenRequest;
import com.digitalsoftware.accounting.emun.domain.GrantType;
import com.digitalsoftware.accounting.request.Request;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
//@Sql(scripts = {"classpath:db/testCaseSQL.sql"}, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
@AutoConfigureMockMvc
@Rollback
@Transactional
public class JwtCommonTest {


    public static Map<OauthType, String> token = new HashMap<>();

    public enum OauthType {
        USER,
        ADMIN,
        CLIENT
    }

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        if (!token.containsKey(OauthType.USER)) {

            token.put(OauthType.USER, obtainAccessToken("testUser2", "password", GrantType.PASSWORD));

        }
        if (!token.containsKey(OauthType.ADMIN)) {

            token.put(OauthType.ADMIN, obtainAccessToken("testAdmin", "password", GrantType.PASSWORD));

        }
        if (!token.containsKey(OauthType.CLIENT)) {

            token.put(OauthType.CLIENT, obtainAccessToken(null, null, GrantType.CLIENT_CREDENTIALS));

        }


    }


    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;

    @Autowired
    private Map<MyTokenFactory.MyToken, String> MyTokenStore;


//    @Autowired
//    private WebTestClient webClient;

    protected String obtainAccessToken(String username, String password, GrantType grantType) throws Exception {


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType.getCode());
//        params.add("client_id", "fooClientIdPassword");
        if (grantType.equals(GrantType.PASSWORD)) {
            if (username != null)
                params.add("username", username);
            if (password != null)
                params.add("password", password);
        }

        ResultActions result
            = mockMvc.perform(post("/oauth/token")
            .params(params)
            .contentType(MediaType.APPLICATION_JSON)
//            .content(json(newObjectInstance))
            .with(httpBasic(clientId, secret))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }


    public static String json(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected RequestBuilder getAuth(String url, Request request, OauthType oauthType) throws Exception {


        return get(url).header("Authorization", "Bearer " + token.get(oauthType)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json(request));
    }

    protected RequestBuilder postFile(String url, MockMultipartFile file, OauthType oauthType) throws Exception {


        return multipart(url).file(file).header("Authorization", "Bearer " + token.get(oauthType));
    }

    protected RequestBuilder getWebFluxAuth(String url, Request request, JwtTokenRequest jwtTokenRequest) throws Exception {

        return get(url).header("Authorization", "Bearer " + obtainAccessToken(jwtTokenRequest.getUsername(), jwtTokenRequest.getPassword(), jwtTokenRequest.getGrantType())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json(request));
    }

    protected RequestBuilder postAuth(String url, Request request, OauthType oauthType) throws Exception {

        return post(url).header("Authorization", "Bearer " + token.get(oauthType)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json(request));
    }


}
