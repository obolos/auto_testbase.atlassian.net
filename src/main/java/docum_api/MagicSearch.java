package docum_api;

import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

import static constants.Constants.Actions.TEST_BASE_MAGIC_SEARCH;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MagicSearch extends TestConfig {

    @Step("Test input parameters")
    public void searchPeopleWithText(String input, PartyType partyType, int maxCount, Boolean fullSimilarity, Status taskStatus) {
        given().log().uri().
                param("query", input).
                param("partyType", partyType).
                param("maxCount", maxCount).
                param("fullSimilarity", fullSimilarity).
                param("taskStatus", taskStatus). // ALL ACTUAL COMPLETE FAIL
                when().
                get(TEST_BASE_MAGIC_SEARCH).
                then().log().body();
    }

    @Step("Test status codes")
    public void searchPeopleWithText(String input, int statusCode) {
        given().log().uri().
                param("query", input).
                when().
                get(TEST_BASE_MAGIC_SEARCH).
                then().log().body().statusCode(statusCode).log().status();
    }

    @Step("Get Headers from response")
    public void getHeadersFromResponse(String input) {
        Response response =
                given().log().uri().
                        param("query", input).
                        when().get(TEST_BASE_MAGIC_SEARCH).
                        then().log().headers().extract().response();

        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.size() > 0);

        String contentType = response.getContentType();
        Assert.assertEquals(contentType, "application/json");

        Map<String, String> allCookie = response.getCookies();
        Assert.assertNotEquals(allCookie, null);

    }

    @Step("Validate response by schema")
    public void validateJsonSchema(String text) {
        given().log().uri()
                .param("query",text)
                .when().get(TEST_BASE_MAGIC_SEARCH)
                .then().body(matchesJsonSchemaInClasspath("magicSearchSchema.json")).log().body();

    }
}
