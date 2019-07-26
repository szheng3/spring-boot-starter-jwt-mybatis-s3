package com.digitalsoftware.accounting.controllers;

import com.digitalsoftware.accounting.integ.BaseIntegrationTest;
import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.Test;

public class MealsControllerTest extends BaseIntegrationTest {






    @Test
    @Ignore
    public void home() {
        initializeWireMock();
        RestAssured.given().when().get("/").then().statusCode(200);
    }

//
//    public void testEligibility(CSVRecord csvRecord) {
//        String mealsEligibilityRequests = generateJson(buildObjectFromCSVRecord(csvRecord));
//        String expectedResult = csvRecord.get(HeaderEnum.ExpectedResult);
//
//        String actual = RestAssured
//                .given()
//                .queryParam("mealsEligibilityRequests", mealsEligibilityRequests)
//                .when()
//                .header("ClientTransactionId", "test")
//                .get("/getEligibility")
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON).extract().response()
//                .jsonPath().getString("data");//                .body("data",equalTo(expectedResult))
////                .body("data", describedAs("fails case: "+csvRecord.get(HeaderEnum.Name),equalTo(expectedResult)))
//        String name = csvRecord.get(HeaderEnum.Name);
//        assertEquals("test case fails: "+ name,expectedResult,actual);
//
//    }

}
