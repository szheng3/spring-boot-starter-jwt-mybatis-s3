package com.digitalsoftware.accounting.integ;

import com.digitalsoftware.accounting.AccountingApplication;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AccountingApplication.class)
@ActiveProfiles("integrationTest")
@TestPropertySource(properties = "service.url = http://localhost:8069")
@AutoConfigureWireMock(port = 8069)
public class BaseIntegrationTest {
    @ClassRule
    public final static SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private Environment environment;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() {
        String port = environment.getProperty("local.server.port");
        RestAssured.baseURI = "http://localhost:" + port;
    }

    //TODO configure your mock response here
    protected void initializeWireMock() {
        WireMock.stubFor(WireMock.get(WireMock.anyUrl()).willReturn(WireMock.aResponse().withStatus(200)
                .withBody("")));
    }
}
